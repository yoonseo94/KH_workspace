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
import community.model.dto.LikeDTO;
import community.model.dto.QnaNotice;
import community.model.dto.QnaNoticeComment;
import community.model.dto.QnaNoticeExt;
import community.model.exception.QnaNoticeException;


public class QnaNoticeDao {
	private Properties prop =  new Properties();

	public QnaNoticeDao() {
		String fileName = QnaNoticeDao.class.getResource("/sql/qna-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	//목록조회
	public List<QnaNoticeExt> findAll(Connection conn,Map<String,Object> param){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QnaNoticeExt> list = new ArrayList<>();
		String sql = prop.getProperty("nfindAll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaNoticeExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_cnt"));
				board.setCommentCount(rset.getInt("comment_cnt"));
				list.add(board);
			}
			
		} catch (Exception e) {
			throw new QnaNoticeException("글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	private QnaNoticeExt handleBoardResultSet(ResultSet rset) throws SQLException {
		QnaNoticeExt board = new QnaNoticeExt();
		board.setNo(rset.getInt("notice_no"));
		board.setMemberId(rset.getString("member_id"));
		board.setNickName(rset.getString("nickname"));
		board.setTitle(rset.getString("notice_title"));
		board.setContent(rset.getString("content"));
		board.setReadCnt(rset.getInt("read_count"));
		board.setRegDate(rset.getDate("reg_date"));
		
		return board;
	}
	
	
	//총 공지수 조회 오류
	public int getTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("ngetTotalContents");
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (Exception e) {
			throw new QnaNoticeException("총 게시물수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}
	
	
	//공지작성
	public int insertNotice(Connection conn, QnaNotice board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getMemberId());
			pstmt.setString(2, board.getNickName());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaNoticeException("공지사항 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//게시글번호찾기
	
	public int findCurrentNoticeNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int no = 0;
		String sql = prop.getProperty("findCurrentNoticeNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next())
				no = rset.getInt(1);
			
		} catch (SQLException e) {
			throw new QnaNoticeException("게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}
	
	//첨부파일 등록
	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("ninsertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaNoticeException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//게시글 한 건 조회
	public QnaNoticeExt findByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QnaNoticeExt board = null;
		String sql = prop.getProperty("nfindByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				board = handleBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new QnaNoticeException("게시글 한건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}
	
	public List<Attachment> findAttachmentByNoticeNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByNoticeNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment attach = handleAttachmentResultSet(rset);
				attachments.add(attach);
			}
		} catch (SQLException e) {
			throw new QnaNoticeException("게시글 번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return attachments;
	}


	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		attach.setNo(rset.getInt("no"));
		attach.setBoardNo(rset.getInt("notice_no"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		
		return attach;
	}

	
	//조회수
	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("nupdateReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaNoticeException("조회수 증가처리 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//첨부파일 조회
	public Attachment findAttachmentByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment attach = null;
		String sql = prop.getProperty("nfindAttachmentByNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) 
				attach = handleAttachmentResultSet(rset);
				
		} catch (SQLException e) {
			throw new QnaNoticeException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}
	
	
	//게시글 삭제
	public int deleteNotice(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteNotice"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaNoticeException("공지사항 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int updateNotice(Connection conn, QnaNoticeExt board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaNoticeException("공지사항 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//첨부파일 삭제
	public int deleteAttachment(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("ndeleteAttachment"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaNoticeException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public int insertNoticeComment(Connection conn, QnaNoticeComment nc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertNoticeComment"); 
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, nc.getMemberId());
			pstmt.setString(2, nc.getNickName());
			pstmt.setInt(3,nc.getNoticeNo());
			pstmt.setString(4, nc.getContent());
			pstmt.setInt(5, nc.getCommentLevel());
			pstmt.setObject(6, nc.getCommentRef() == 0 ? null : nc.getCommentRef());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaNoticeException("공지사항 댓글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<QnaNoticeComment> findNoticeCommentByNoticeNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QnaNoticeComment> comments = new ArrayList<>();
		String sql = prop.getProperty("findNoticeCommentByNoticeNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				QnaNoticeComment nc = new QnaNoticeComment();
				nc.setCommentNo(rset.getInt("comment_no"));
				nc.setCommentLevel(rset.getInt("comment_level"));
				nc.setNoticeNo(rset.getInt("notice_no"));
				nc.setMemberId(rset.getString("member_id"));
				nc.setNickName(rset.getString("nickname"));
				nc.setContent(rset.getString("content"));
				nc.setCommentRef(rset.getInt("comment_ref"));
				nc.setLikeCnt(rset.getInt("like_count"));
				nc.setRegDate(rset.getDate("reg_date"));
				comments.add(nc);
			}
			
		} catch (SQLException e) {
			throw new QnaNoticeException("공지사항 댓글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comments;
	}

	//댓글삭제
	public int deleteNoticeComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteNoticeComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaNoticeException("공지사항 댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**********/
	public LikeDTO selectLikeOne(Connection conn, String memberId, int no) {
		LikeDTO ld = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("nselectLikeOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ld = new LikeDTO();
				ld.setMemberId(rset.getString("member_id"));
				ld.setBoardNo(rset.getInt("board_no"));
				ld.setLikeIt(rset.getString("likeit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return ld;
	}

	public int insertLike(Connection conn, LikeDTO like) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("ninsertLike");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, like.getMemberId());
			pstmt.setInt(2, like.getBoardNo());
			pstmt.setString(3, like.getLikeIt());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteLike(Connection conn, LikeDTO bl) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("ndeleteLike"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bl.getMemberId());
			pstmt.setInt(2, bl.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	

	
}
