package community.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import community.model.dao.QnaBoardDao;
import community.model.dto.Attachment;
import community.model.dto.QnaBoard;
import community.model.dto.QnaBoardComment;
import community.model.dto.QnaBoardExt;
import community.model.dto.QnaCommentLike;
import member.model.dto.Member;

public class QnaBoardService {

	private QnaBoardDao bd = new QnaBoardDao();
	
	public List<QnaBoardExt> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<QnaBoardExt> list = bd.findAll(conn, param);
		close(conn);
		return list;
	}
	
	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = bd.getTotalContents(conn);
		close(conn);
		return totalContents;

	}
	
	public int insertBoard(QnaBoard board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. board에 등록
			result = bd.insertBoard(conn, board); // pk no값 결정 - seq_board_no.nextval

			// 2. board pk 가져오기
			int no = bd.findCurrentBoardNo(conn); // seq_board_no.currval
			board.setNo(no);
			System.out.println("방금 등록된 board.no = " + no);
			
			// 3. attachment에 등록
			List<Attachment> attachments = ((QnaBoardExt) board).getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setBoardNo(no);
					result = bd.insertAttachment(conn, attach);
				}
			}
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public QnaBoardExt findByNo(int no) {
		Connection conn = getConnection();
		QnaBoardExt board = bd.findByNo(conn, no); // board테이블 조회
		List<Attachment> attachments = bd.findAttachmentByBoardNo(conn, no); // attachment 테이블 조회
		List<QnaBoardComment> comments = bd.findBoardCommentByBoardNo(conn, no);
		board.setAttachments(attachments);
		board.setBoardComments(comments);
		close(conn);
		return board;
	}

	public int updateReadCount(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = bd.updateReadCount(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Attachment findAttachmentByNo(int no) {
		Connection conn = getConnection();
		Attachment attach = bd.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}
	
	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = bd.deleteBoard(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int updateBoard(QnaBoardExt board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. board 수정
			result = bd.updateBoard(conn, board);
			
			// 2. attachment에 등록
			List<Attachment> attachments = ((QnaBoardExt) board).getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = bd.insertAttachment(conn, attach);
				}
			}
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteAttachment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = bd.deleteAttachment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertBoardComment(QnaBoardComment bc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = bd.insertBoardComment(conn, bc);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	
	
	public int deleteBoardComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = bd.deleteBoardComment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public List<QnaBoard> findBy(Map<String, String> param) {
		Connection conn = getConnection();
		List<QnaBoard> list = bd.findBy(conn, param);
		close(conn);
		return list;
	}

	public List<QnaBoard> noComment() {
		Connection conn = getConnection();
		List<QnaBoard> list = bd.noComment(conn);
		close(conn);

		return list;
	}
	
	public int commentCount(int no) { 
		Connection conn = getConnection(); 
		int result = bd.commentCount(conn,no); 
		close(conn); 
		return result; 
		}	
	
	
	public List<QnaBoardExt> sortRead(Map<String, Object> param) {
		Connection conn = getConnection();
		List<QnaBoardExt> list = bd.sortRead(conn, param);
		close(conn);
		return list;
	}
	
	
	
	public QnaCommentLike selectLikeOne(int no, String memberId) {
		Connection conn = getConnection();
		QnaCommentLike cl= bd.selectLikeOne(conn, no, memberId);
		close(conn);
		return cl;
	}

	public int deleteLike(QnaCommentLike cl) {
		Connection conn = getConnection();
		int result =bd.deleteLike(conn, cl);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertLike(int no) {
		Connection conn = getConnection();
		int result = bd.insertLike(conn, no);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<QnaBoardComment> selectCommentList(int no) {
		Connection conn = getConnection();
		List<QnaBoardComment> list = bd.selectCommentList(conn,no);
		close(conn);
		return list;
	}

	



}
