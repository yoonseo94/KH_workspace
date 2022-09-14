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

import common.JdbcTemplate;
import community.model.dto.Attachment;
import community.model.dto.Event;
import community.model.dto.EventAttachment;
import community.model.dto.EventExt;
import community.model.exception.EventException;


public class EventDao {
	private Properties prop = new Properties();

	public EventDao() {
		String filename = EventDao.class.getResource("/sql/event-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 목록조회
		public List<EventExt> findAll(Connection conn, Map<String, Object> param) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<EventExt> list = new ArrayList<>();
			String sql = prop.getProperty("findAll");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, (int) param.get("start"));
				pstmt.setInt(2, (int) param.get("end"));
				rset = pstmt.executeQuery();
				while (rset.next()) {
					EventExt event = handleBoardResultSet(rset);
					event.setAttachCount(rset.getInt("attach_cnt"));
					list.add(event);
				}
			} catch (Exception e) {
				throw new EventException("이벤트 목록 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}

		private EventExt handleBoardResultSet(ResultSet rset) throws SQLException{
			EventExt event = new EventExt();			
			event.setEventId(rset.getString("event_id"));
			event.setEventTitle(rset.getString("event_title"));
			event.setEventContent(rset.getString("event_content"));
			event.setsDate(rset.getDate("event_start_date"));
			event.seteDate(rset.getDate("event_end_date"));
			event.setRegDate(rset.getDate("reg_date"));
			event.setTitlefileName(rset.getString("title_filename"));
			event.setNo(rset.getInt("no"));

			return event;
		}
	
		public int getTotalContents(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int totalContents = 0;
			String sql = prop.getProperty("getTotalContents");
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				while (rset.next()) {
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
		
		public int insertBoard(Connection conn, Event event) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertBoard");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getEventId());
				pstmt.setString(2,event.getEventTitle());
				pstmt.setString(3, event.getEventContent());
				pstmt.setDate(4,event.getsDate());
				pstmt.setDate(5,event.geteDate());
				pstmt.setString(6, event.getTitlefileName());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new EventException("게시글 등록 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		}
		

	 	public int findCurrentBoardNo(Connection conn) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			int no = 0;
			String sql = prop.getProperty("findCurrentBoardNo");

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

		public int insertAttachment(Connection conn, EventAttachment attach) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("insertAttachment");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, attach.getOriginal_filename());
				pstmt.setString(2, attach.getRenamed_filename());
				pstmt.setInt(3, attach.getEventNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new EventException("첨부파일 등록 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		} 

		//한 건 조회
		public EventExt findByNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			EventExt board = null;
			String sql = prop.getProperty("findByNo");

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					board = handleBoardResultSet(rset);
				}
			} catch (SQLException e) {
				throw new EventException("이벤트 한 건 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return board;
		}


		public List<EventAttachment> findAttachmentByBoardNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<EventAttachment> att = new ArrayList<>();
			String sql = prop.getProperty("findAttachmentByBoardNo");

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				while (rset.next()) {
					EventAttachment attach = handleAttachmentResultSet(rset);
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


		private EventAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
			EventAttachment att = new EventAttachment();
			att.setNo(rset.getInt("att_no"));
			att.setOriginal_filename(rset.getString("original_filename"));
			att.setRenamed_filename(rset.getString("renamed_filename"));
			att.setEventNo(rset.getInt("event_no"));
			return att;
		}

		public int updateBoard(Connection conn, EventExt event) {
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = prop.getProperty("updateBoard");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getEventTitle());
				pstmt.setString(2, event.getEventContent());
				pstmt.setInt(3, event.getNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				throw new EventException("게시글 수정 오류", e);
			} finally {
				close(pstmt);
			}
			return result;
		}


		public EventAttachment findAttachmentByNo(Connection conn, int no) {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			EventAttachment attach = null;
			String sql = prop.getProperty("findAttachmentByNo");

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rset = pstmt.executeQuery();
				if (rset.next())
					attach = handleAttachmentResultSet(rset);

			} catch (SQLException e) {
				throw new EventException("첨부파일 조회 오류", e);
			} finally {
				close(rset);
				close(pstmt);
			}
			return attach;
		}
		
		public int deleteAttachment(Connection conn, int no) {
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteAttachment");

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
			String query = prop.getProperty("deleteBoard");

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