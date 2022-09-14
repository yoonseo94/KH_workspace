package community.model.dao;

import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import community.model.dto.Attachment;
import community.model.dto.EventAppAtt;
import community.model.dto.EventAppExt;
import community.model.dto.EventApplicants;
import community.model.dto.EventAttachment;
import community.model.dto.EventExt;
import community.model.exception.EventException;



public class EventAppDao {
	private Properties prop =  new Properties();

	public EventAppDao() {
		String fileName = EventAppDao.class.getResource("/sql/event-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//목록조회
	public List<EventAppExt> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<EventAppExt> list = new ArrayList<>();
		String sql = prop.getProperty("efindAll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				EventAppExt event = handleBoardResultSet(rset);
				event.setAttachCount(rset.getInt("attach_cnt"));
				list.add(event);
			}

		} catch (Exception e) {
			throw new EventException("글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

		private EventAppExt handleBoardResultSet(ResultSet rset) throws SQLException{
			EventAppExt event = new EventAppExt();
			event.setEventapplyCode(rset.getString("event_apply_code"));
			event.setNo(rset.getInt("no"));
			event.setMemberId(rset.getString("member_id"));
			event.setNickName(rset.getString("nickname"));
			event.setContent(rset.getString("content"));
			event.setEventNo(rset.getInt("event_no"));
			return event;
		}
	
		public int getTotalContents(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int totalContents = 0;
			String sql = prop.getProperty("egetTotalContents");
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					totalContents = rset.getInt(1);
				}
			} catch (Exception e) {
				throw new EventException("총 게시물수 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return totalContents;
		}

		public int insertEvent(Connection conn, EventApplicants event) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("einsertEvent");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getEventapplyCode());
				pstmt.setString(2, event.getMemberId());
				pstmt.setString(3,event.getNickName());
				pstmt.setString(4, event.getContent());
				pstmt.setInt(5, event.getEventNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new EventException("이벤트 참가 등록 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		public int findCurrentEventNo(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int no = 0;
			String sql = prop.getProperty("efindCurrentEventNo");
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				while(rset.next())
					no = rset.getInt(1);
			} catch (SQLException e) {
				throw new EventException("이벤트 참가글 번호 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return no;
		}
		
		//첨부파일 등록
		public int insertAttachment(Connection conn, EventAppAtt attach) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("einsertAttachment");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, attach.getOriginalFilename());
				pstmt.setString(2, attach.getRenamedFilename());				
				pstmt.setInt(3, attach.getNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new EventException("첨부파일 등록 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		}
		
		
		public EventAppExt findByNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			EventAppExt board = null;
			String sql = prop.getProperty("efindByNo");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					board = handleBoardResultSet(rset);
				}
			} catch (SQLException e) {
				throw new EventException("이벤트 참여작 한건 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return board;
		}
		
		public List<EventAppAtt> findAttachmentByEventAppNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<EventAppAtt> attachments = new ArrayList<>();
			String sql = prop.getProperty("findAttachmentByEventAppNo");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					EventAppAtt attach = handleAttachmentResultSet(rset);
					attachments.add(attach);
				}
			} catch (SQLException e) {
				throw new EventException("게시글 번호에 의한 첨부파일조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return attachments;
		}

		private EventAppAtt handleAttachmentResultSet(ResultSet rset) throws SQLException{
			EventAppAtt event = new EventAppAtt();
			event.setNo(rset.getInt("no"));
			event.setOriginalFilename(rset.getString("original_filename"));
			event.setRenamedFilename(rset.getString("renamed_filename"));
			event.setEventNo(rset.getInt("event_no"));
			return event;
		}
		
		public EventAppAtt findAttachmentByNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			EventAppAtt attach = null;
			String sql = prop.getProperty("efindAttachmentByNo");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				if(rset.next()) 
					attach = handleAttachmentResultSet(rset);			
			} catch (SQLException e) {
				throw new EventException("첨부파일 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return attach;
		}

		public int findCurrentBoardNo(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int no = 0;
			String sql = prop.getProperty("efindCurrentBoardNo");

			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				while (rset.next())
					no = rset.getInt(1);

			} catch (SQLException e) {
				throw new EventException("이벤트 게시글 번호 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return no;
		}

		public List<EventAppAtt> findAttachmentByBoardNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<EventAppAtt> att = new ArrayList<>();
			String sql = prop.getProperty("efindAttachmentByBoardNo");

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					EventAppAtt attach = handleAttachmentResultSet(rset);
					att.add(attach);
				}
			} catch (SQLException e) {
				throw new EventException("게시글 번호에 의한 첨부파일조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}

			return att;
		}

		public int updateBoard(Connection conn, EventAppExt event) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("eupdateEvent");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getEventapplyCode());
				pstmt.setString(2, event.getContent());
				pstmt.setInt(3, event.getNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new EventException("게시글 수정 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		}

		public int deleteAttachment(Connection conn, int no) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("edeleteAttachment");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new EventException("첨부파일 삭제 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		}

		public int deleteBoard(Connection conn, int no) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = prop.getProperty("edeleteBoard");
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new EventException("게시글 삭제 오류", e);
			} finally {
				close(pstmt);
			}

			return result;
		}


		
}
