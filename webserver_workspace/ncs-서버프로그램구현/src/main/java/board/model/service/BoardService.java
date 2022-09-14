package board.model.service;

import board.model.dao.BoardDao;

public class BoardService {
	private BoardDao boardDao = new BoardDao();

	public int insertBoard(Board b) {
		Connection con = getConnection();
		int result = 0;
		try {
			result = new BoardDao().insertBoard(con, b);
			commit(con);
		} catch (Exception e) {
			rollback(con);
			throw e; // 예외를 controller에 통보
		} finally {
			close(con);			
		}
		return result;
	}
	
	public int updateBoard(Board b) {
		Connection con = getConnection();
		int result = 0;
		try {
			result = new BoardDao().updateBoard(con, b);
			commit(con);
		} catch (Exception e) {
			rollback(con);
			throw e; // 예외를 controller에 통보
		} finally {
			close(con);			
		}
		return result;
	}
}
