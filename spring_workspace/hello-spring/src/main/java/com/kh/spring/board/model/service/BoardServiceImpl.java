package com.kh.spring.board.model.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.dto.Attachment;
import com.kh.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Board> selectBoardList(int cPage, int numPerPage) {
		int offset = (cPage -1) * numPerPage;
		int limit = numPerPage;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return boardDao.selectBoardList(rowBounds);
	}
	
	@Override
	public int selectTotalContent() {
		return boardDao.selectTotalContent();
	}
	
	/**
	 * @Transaction은 Runtime 예외가 발생시에만 rollback처리
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBoard(Board board) {
		// 1. board insert
		int result = boardDao.insertBoard(board);
		log.debug("board#no = {}", board.getNo());
		// 2. attachments insert
		List<Attachment> attachments = board.getAttachments();
		if(!attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setBoardNo(board.getNo());
				result = boardDao.insertAttachment(attach);
			}
		}
		return result;
	}
	
	@Override
	public Board selectOneBoard(int no) {
		Board board = boardDao.selectOneBoard(no);
		List<Attachment> attachments = boardDao.selectAttchmentListByBoardNo(no);
		board.setAttachments(attachments);
		return board;
	}
	
	@Override
	public Board selectOneBoardCollection(int no) {
		return boardDao.selectOneBoardCollection(no);
	}
	
	@Override
	public Attachment selectOneAttachment(int attachNo) {
		return boardDao.selectOneAttachment(attachNo);
	}
	
	@Override
	public int deleteAttachment(int attachNo) {
		return boardDao.deleteAttachment(attachNo);
	}
	
	@Override
	public int updateBoard(Board board) {
		// a. board 수정
		int result = boardDao.updateBoard(board);
		
		// b. attachment테이블의 등록
		List<Attachment> attachments = board.getAttachments();
		if(!attachments.isEmpty()) {
			for(Attachment attach : attachments)
				result = boardDao.insertAttachment(attach);
		}
		return result;
	}
}
