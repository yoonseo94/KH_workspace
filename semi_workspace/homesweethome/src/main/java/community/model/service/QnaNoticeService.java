package community.model.service;
import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import community.model.dao.QnaNoticeDao;
import community.model.dto.Attachment;
import community.model.dto.LikeDTO;
import community.model.dto.QnaNotice;
import community.model.dto.QnaNoticeComment;
import community.model.dto.QnaNoticeExt;



public class QnaNoticeService {
	QnaNoticeDao nd = new QnaNoticeDao();

	public List<QnaNoticeExt> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<QnaNoticeExt> list = nd.findAll(conn, param);
		close(conn);
		return list;
	}
	
	public int getTotalContents() {
		Connection conn = getConnection();
		int totalContents = nd.getTotalContents(conn);
		close(conn);
		return totalContents;
	}
	
	
	public int insertNotice(QnaNotice board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. board에 등록
			result = nd.insertNotice(conn, board); // pk no값 결정 - seq_board_no.nextval

			// 2. board pk 가져오기
			int no = nd.findCurrentNoticeNo(conn); // seq_board_no.currval
			board.setNo(no);
			System.out.println("방금 등록된 notice.no = " + no);
			
			// 3. attachment에 등록
			List<Attachment> attachments = ((QnaNoticeExt) board).getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					attach.setBoardNo(no);
					result = nd.insertAttachment(conn, attach);
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
	

	public QnaNoticeExt findByNo(int no) {
		Connection conn = getConnection();
		QnaNoticeExt board = nd.findByNo(conn, no); // board테이블 조회
		List<Attachment> attachments = nd.findAttachmentByNoticeNo(conn, no); // attachment 테이블 조회
		List<QnaNoticeComment> comments = nd.findNoticeCommentByNoticeNo(conn, no);
		board.setAttachments(attachments);
		board.setComments(comments);
		close(conn);
		return board;
	}
	
	public int updateReadCount(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = nd.updateReadCount(conn, no);
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
		Attachment attach = nd.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}
	
	public int deleteNotice(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = nd.deleteNotice(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	
	
	public int updateNotice(QnaNoticeExt board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			
			// 1. board 수정
			result = nd.updateNotice(conn, board);
			
			// 2. attachment에 등록
			List<Attachment> attachments = ((QnaNoticeExt) board).getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = nd.insertAttachment(conn, attach);
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
			result = nd.deleteAttachment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int insertNoticeComment (QnaNoticeComment nc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = nd.insertNoticeComment(conn,nc);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int deleteNoticeComment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = nd.deleteNoticeComment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}
	
	
	
	/*******************************/

	public LikeDTO selectLikeOne(String memberId, int no) {
		Connection conn = getConnection();
		LikeDTO bl= nd.selectLikeOne(conn, memberId,no);
		close(conn);
		return bl;
	}

	public int insertLike(LikeDTO like) {
		Connection conn = getConnection();
		//dao단에 요청
		int result = nd.insertLike(conn, like);
		
		//트랜잭션 처리
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		//자원반납
		close(conn);
		return result;
	}

	public int deleteLike(LikeDTO bl) {
		Connection conn = getConnection();
		int result = nd.deleteLike(conn, bl);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
}
