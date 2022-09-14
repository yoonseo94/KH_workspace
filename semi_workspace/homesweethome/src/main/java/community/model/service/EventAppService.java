package community.model.service;
import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import community.model.dao.EventAppDao;
import community.model.dto.Attachment;
import community.model.dto.EventAppAtt;
import community.model.dto.EventAppExt;
import community.model.dto.EventApplicants;
import community.model.dto.EventAttachment;
import community.model.dto.EventExt;

public class EventAppService {
	EventAppDao ed = new EventAppDao();

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = ed.getTotalContents(conn);
		close(conn);
		return totalContent;
	}

	public List<EventAppExt> findAll(Map<String, Object> param) {
		Connection conn = getConnection();
		List<EventAppExt> list = ed.findAll(conn, param);
		close(conn);
		return list;
	}
	
	public int insertBoard(EventApplicants event) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = ed.insertEvent(conn, event);
			int no = ed.findCurrentBoardNo(conn);
			event.setNo(no);
			System.out.println("방금 등록된 이벤트.no = " + no);
			
			List<EventAppAtt> attach = ((EventAppExt) event).getAttachments();
			if(attach != null && !attach.isEmpty()) {
				for(EventAppAtt att : attach) {
					att.setNo(no);
					result = ed.insertAttachment(conn, att);
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

	public EventAppExt findByNo(int no) {
		Connection conn = getConnection();
		EventAppExt event = ed.findByNo(conn, no);
		List<EventAppAtt> attachments = ed.findAttachmentByBoardNo(conn, no);
		event.setAttachments(attachments);
		close(conn);
		return event;
	}

	public int updateBoard(EventAppExt event) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = ed.updateBoard(conn, event);
			

			List<EventAppAtt> attachments = ((EventAppExt) event).getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(EventAppAtt attach : attachments) {
					result = ed.insertAttachment(conn, attach);
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

	public EventAppAtt findAttachmentByNo(int no) {
		Connection conn = getConnection();
		EventAppAtt attach = ed.findAttachmentByNo(conn, no);
		close(conn);
		return attach;
	}

	public int deleteAttachment(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ed.deleteAttachment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteBoard(int no) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = ed.deleteBoard(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; 
		} finally {
			close(conn);
		}
		return result;
	}

	
}
