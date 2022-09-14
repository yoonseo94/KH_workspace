package community.model.dao;

import static common.JdbcTemplate.close;

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
import community.model.dto.QnaBoard;
import community.model.dto.QnaBoardComment;
import community.model.dto.QnaBoardExt;
import community.model.dto.QnaCommentLike;
import community.model.exception.QnaBoardException;



public class QnaBoardDao {
	private Properties prop = new Properties();
	public static QnaBoardDao getInstance() {
		return getInstance();
	}

	public QnaBoardDao() {
		String fileName = QnaBoardDao.class.getResource("/sql/qna-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 목록조회
	public List<QnaBoardExt> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QnaBoardExt> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				QnaBoardExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_cnt"));
				board.setCommentCount(rset.getInt("comment_cnt"));
				list.add(board);
			}

		} catch (Exception e) {
			throw new QnaBoardException("글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	private QnaBoardExt handleBoardResultSet(ResultSet rset) throws SQLException {
		QnaBoardExt board = new QnaBoardExt();
		board.setNo(rset.getInt("board_no"));
		board.setMemberId(rset.getString("member_id"));
		board.setNickName(rset.getString("nickname"));
		board.setTitle(rset.getString("board_title"));
		board.setContent(rset.getString("content"));
		board.setReadCount(rset.getInt("read_count"));
		board.setRegDate(rset.getDate("reg_date"));
		return board;
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
			throw new QnaBoardException("총 게시물수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int insertBoard(Connection conn, QnaBoard board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getMemberId());
			pstmt.setString(2, board.getNickName());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getContent());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaBoardException("게시글 등록 오류", e);
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
			throw new QnaBoardException("게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaBoardException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public QnaBoardExt findByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QnaBoardExt board = null;
		String sql = prop.getProperty("findByNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				board = handleBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new QnaBoardException("게시글 한건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return board;
	}

	public List<Attachment> findAttachmentByBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Attachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByBoardNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Attachment attach = handleAttachmentResultSet(rset);
				attachments.add(attach);
			}
		} catch (SQLException e) {
			throw new QnaBoardException("게시글 번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return attachments;
	}

	private Attachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		Attachment attach = new Attachment();
		attach.setNo(rset.getInt("no"));
		attach.setBoardNo(rset.getInt("board_no"));
		attach.setOriginalFilename(rset.getString("original_filename"));
		attach.setRenamedFilename(rset.getString("renamed_filename"));
		attach.setRegDate(rset.getDate("reg_date"));
		return attach;
	}

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaBoardException("조회수 증가처리 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Attachment findAttachmentByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment attach = null;
		String sql = prop.getProperty("findAttachmentByNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if (rset.next())
				attach = handleAttachmentResultSet(rset);

		} catch (SQLException e) {
			throw new QnaBoardException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attach;
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
			throw new QnaBoardException("게시글 삭제 오류", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateBoard(Connection conn, QnaBoardExt board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new QnaBoardException("게시글 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAttachment(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAttachment");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaBoardException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoardComment(Connection conn, QnaBoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoardComment");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bc.getBoardNo());
			pstmt.setString(2, bc.getMemberId());
			pstmt.setString(3, bc.getNickName());
			pstmt.setString(4, bc.getContent());
			pstmt.setInt(5, bc.getCommentLevel());
			pstmt.setObject(6, bc.getCommentRef() == 0 ? null : bc.getCommentRef());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaBoardException("댓글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<QnaBoardComment> findBoardCommentByBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QnaBoardComment> comments = new ArrayList<>();
		String sql = prop.getProperty("findBoardCommentByBoardNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				QnaBoardComment bc = new QnaBoardComment();
				bc.setNo(rset.getInt("comment_no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setMemberId(rset.getString("member_id"));
				bc.setNickName(rset.getString("nickname"));
				bc.setContent(rset.getString("content"));
				bc.setCommentRef(rset.getInt("comment_ref"));
				bc.setLikeCnt(rset.getInt("like_count"));
				bc.setRegDate(rset.getDate("reg_date"));
				comments.add(bc);
			}

		} catch (SQLException e) {
			throw new QnaBoardException("댓글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return comments;
	}

	public int deleteBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBoardComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QnaBoardException("댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<QnaBoard> findBy(Connection conn, Map<String, String> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QnaBoard> list = new ArrayList<>();
		String sql = prop.getProperty("findBy");
		sql = sql.replace("#", param.get("searchType"));
		System.out.println("sql = " + sql);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + param.get("searchKeyword") + "%");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				QnaBoard QnaBoard = handleBoardResultSet(rset);
				list.add(QnaBoard);
			}
		} catch (Exception e) {
			throw new QnaBoardException("질문게시글 검색 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	// 댓글0인 게시글
	public List<QnaBoard> noComment(Connection conn) {
		List<QnaBoard> list = new ArrayList<QnaBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("noComment");

		// 채워야할 ? 없음
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				QnaBoard qb = new QnaBoard();
				qb.setNo(rset.getInt("board_no"));
				qb.setMemberId(rset.getString("member_id"));
				qb.setNickName(rset.getString("nickname"));
				qb.setTitle(rset.getString("board_title"));
				qb.setContent(rset.getString("content"));
				qb.setReadCount(rset.getInt("read_count"));
				qb.setRegDate(rset.getDate("reg_date"));
				list.add(qb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

	// 댓글 수
	public int commentCount(Connection conn, int no) {
		List<QnaBoardComment> list = new ArrayList<>();
		QnaBoardComment bc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("commentCount");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				bc = new QnaBoardComment();

				bc.setNo(rset.getInt("comment_no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setMemberId(rset.getString("member_id"));
				bc.setNickName(rset.getString("nickname"));
				bc.setContent(rset.getString("content"));
				bc.setCommentRef(rset.getInt("comment_ref"));
				bc.setLikeCnt(rset.getInt("like_count"));
				bc.setRegDate(rset.getDate("reg_date"));

				list.add(bc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.size();
	}


//정렬
	public List<QnaBoardExt> sortRead(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<QnaBoardExt> list = new ArrayList<>();
		
		String sql = prop.getProperty("sortRead");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				QnaBoardExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_cnt"));
				board.setCommentCount(rset.getInt("comment_cnt"));
				list.add(board);
			}

		} catch (Exception e) {
			throw new QnaBoardException("조회순 정렬 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	//좋아요
	/*
	 * public void bGood(int no) { Connection conn = JdbcTemplate.getConnection();
	 * PreparedStatement pstmt = null; String sql = prop.getProperty("likeComment");
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, no);
	 * pstmt.executeUpdate();
	 * 
	 * }catch(Exception e) { throw new QnaBoardException("좋아요 오류", e); }finally {
	 * JdbcTemplate.close(pstmt); } }
	 */
	
	public int insertLike(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new QnaBoardException("로그인 후 이용해주세요",e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public QnaCommentLike selectLikeOne(Connection conn, int no, String memberId) {
		QnaCommentLike cl = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLikeOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, no);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cl = new QnaCommentLike();
				cl.setMemberId(rset.getString("member_id"));
				cl.setCommentNo(rset.getInt("comment_no"));
				cl.setLike(rset.getString("likeit"));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new QnaBoardException("로그인 후 이용해주세요",e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return cl;
	}

	public int deleteLike(Connection conn, QnaCommentLike cl) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteLike"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, cl.getMemberId());
			pstmt.setInt(2, cl.getCommentNo());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new QnaBoardException("로그인 후 이용해주세요",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<QnaBoardComment> selectCommentList(Connection conn, int no) {
		List<QnaBoardComment> list = new ArrayList<>();
		QnaBoardComment cbr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cbr = new QnaBoardComment();

				cbr.setNo(rset.getInt("comment_no"));
				cbr.setBoardNo(rset.getInt("board_no"));
				cbr.setMemberId(rset.getString("member_id"));
				cbr.setNickName(rset.getString("nickname"));
				cbr.setContent(rset.getString("content"));
				cbr.setLikeCnt(rset.getInt("like_count"));
				cbr.setRegDate(rset.getDate("reg_date"));
				cbr.setCommentLevel(rset.getInt("comment_level"));
				cbr.setCommentRef(rset.getInt("comment_ref"));
				
				list.add(cbr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	

	
	
	
}

