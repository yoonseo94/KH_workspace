package board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BoardDao {
	
	public int insertBoard(Connection con, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO board(boardnum, boardwriter, boardtitle, boardcontent) VALUES((SELECT DECODE(MAX(boardnum), NULL, 0, MAX(boardnum)) + 1 FROM board), ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBoardWriter());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("게시글 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;

	}
	
	public int updateBoard(Connection con, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET BTITLE=?, BWRITER=?, BCONTENT=? WHERE BID=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, board.getbTitle());
			pstmt.setString(2, board.getbWriter());
			pstmt.setString(3, board.getbContent());
			pstmt.setInt(4, board.getbId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BoardException("게시글 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
		
	}
}
