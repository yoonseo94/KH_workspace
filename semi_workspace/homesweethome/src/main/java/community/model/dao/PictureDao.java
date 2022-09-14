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
import community.model.dto.KnowhowExt;
import community.model.dto.LikeDTO;
import community.model.dto.Picture;
import community.model.dto.PictureAttachment;
import community.model.dto.PictureExt;
import community.model.exception.KnowhowException;
import community.model.exception.PictureException;

public class PictureDao {
	private Properties prop = new Properties();
	Connection con;

	public PictureDao() {
		String fileName = KnowhowDao.class.getResource("/sql/picture.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<PictureExt> findAll(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PictureExt> list = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				PictureExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_cnt"));
				list.add(board);
			}

		} catch (Exception e) {
			throw new PictureException("글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	private PictureExt handleBoardResultSet(ResultSet rset) throws SQLException {
		PictureExt img = new PictureExt();
		img.setImgNo(rset.getInt("community_img_no"));
		img.setMemberId(rset.getString("member_id"));
		img.setNickName(rset.getString("nickname"));
		img.setTitle(rset.getString("title"));
		img.setContent(rset.getString("content"));
		img.setCategoryShape(rset.getInt("img_theme_shape"));
		img.setCoverPhoto(rset.getString("cover_photo"));
		img.setReadCount(rset.getInt("read_count"));
		img.setLikeCount(rset.getInt("like_count"));
		img.setRegDate(rset.getDate("reg_date"));

		return img;
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
			throw new PictureException("총 게시물수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	public int insertBoard(Connection conn, Picture img) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, img.getMemberId());
			pstmt.setString(2, img.getNickName());
			pstmt.setString(3, img.getTitle());
			pstmt.setString(4, img.getContent());
			pstmt.setInt(5, img.getCategoryShape());
			pstmt.setString(6, img.getCoverPhoto());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new PictureException("사진(게시판) 등록 오류", e);
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
			throw new PictureException("게시글 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return no;
	}

	// 첨부파일 등록
	public int insertAttachment(Connection conn, PictureAttachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getImgNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new PictureException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public PictureExt findByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PictureExt board = null;
		String sql = prop.getProperty("findByNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				board = handleBoardResultSet(rset);
			}
		} catch (SQLException e) {
			throw new PictureException("게시글 한건 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}

	public List<PictureAttachment> findAttachmentByBoardNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PictureAttachment> attachments = new ArrayList<>();
		String sql = prop.getProperty("findAttachmentByBoardNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				PictureAttachment attach = handleAttachmentResultSet(rset);
				attachments.add(attach);
			}
		} catch (SQLException e) {
			throw new PictureException("게시글 번호에 의한 첨부파일조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return attachments;
	}

	private PictureAttachment handleAttachmentResultSet(ResultSet rset) throws SQLException {
		PictureAttachment att = new PictureAttachment();
		att.setNo(rset.getInt("attach_no"));
		att.setImgNo(rset.getInt("community_img_no"));
		att.setOriginalFilename(rset.getString("original_filename"));
		att.setRenamedFilename(rset.getString("renamed_filename"));
		att.setRegDate(rset.getDate("reg_date"));
		return att;
	}

	// 조회수
	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new PictureException("조회수 증가처리 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 첨부파일 조회
	public PictureAttachment findAttachmentByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PictureAttachment attach = null;
		String sql = prop.getProperty("findAttachmentByNo");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			if (rset.next())
				attach = handleAttachmentResultSet(rset);

		} catch (SQLException e) {
			throw new PictureException("첨부파일 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public int deleteBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new PictureException("게시글 삭제 오류", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int updateBoard(Connection conn, PictureExt board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getImgNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			throw new PictureException("게시글 수정 오류", e);
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
			throw new PictureException("첨부파일 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public int getProductCount(Connection conn, int shape) {
		int result = 0;
		String sql = prop.getProperty("countAll");
		String sql2 = prop.getProperty("countCate");
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if(shape == 0) {
				ps = conn.prepareStatement(sql);
			}else {
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, shape);
			}
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			//-1
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}
		return result;
	}
	
	public ArrayList<PictureExt> productList(Connection conn, int start , int end, int catenum) {
		ArrayList<PictureExt> list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = prop.getProperty("viewByTheme");
		try {
			if(catenum != 0) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, catenum);
				ps.setInt(2, start);
				ps.setInt(3, end);
			}
			rs = ps.executeQuery();
			
			list = new ArrayList<PictureExt>();
			while(rs.next()) {
				PictureExt pic = new PictureExt();

				pic.setImgNo(rs.getInt("community_img_no"));
				pic.setCoverPhoto(rs.getString("cover_photo"));
				pic.setNickName(rs.getString("nickname"));
				pic.setReadCount(rs.getInt("read_count"));
				pic.setTitle(rs.getString("title"));
			
				list.add(pic);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new KnowhowException("카테고리별 조회 오류", e);
			
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(ps);
		}
		return list;
	}


	

	/*
	 * public int insertLike(Connection conn, LikeDTO likey) { int result = 0;
	 * PreparedStatement pstmt = null; String sql = prop.getProperty("insertLike");
	 * 
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, likey.getNo());
	 * result = pstmt.executeUpdate(); } catch (Exception e) { throw new
	 * PictureException(" 좋아요 추가 오류", e); } finally { close(pstmt); } return result;
	 * }
	 * 
	 * public int deleteLike(Connection conn, LikeDTO likey) { int result = 0;
	 * PreparedStatement pstmt = null; String sql = prop.getProperty("deleteLike");
	 * 
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setInt(1, likey.getNo());
	 * result = pstmt.executeUpdate(); } catch (Exception e) { throw new
	 * PictureException(" 좋아요 취소 오류", e); } finally { close(pstmt); } return result;
	 * }
	 * 
	 * public List<LikeDTO> LikeByMemberId(Connection conn, String memberId) {
	 * PreparedStatement pstmt = null; ResultSet rset = null; List<LikeDTO> list =
	 * new ArrayList<>(); String sql = prop.getProperty("LikeByMemberId");
	 * 
	 * try { pstmt = conn.prepareStatement(sql); pstmt.setString(1, memberId); rset
	 * = pstmt.executeQuery(); while(rset.next()) { LikeDTO likey = new LikeDTO();
	 * likey.setMemberId(rset.getString("member_id"));
	 * likey.setNo(rset.getInt("no")); list.add(likey); }
	 * 
	 * } catch (Exception e) { throw new PictureException(" 좋아요 아이디 오류", e); }
	 * finally { close(rset); close(pstmt); } return list; }
	 */
	public List<PictureExt> sortRead(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PictureExt> list = new ArrayList<>();
		String sql = prop.getProperty("sortRead");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				PictureExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_cnt"));
				list.add(board);
			}

		} catch (Exception e) {
			throw new PictureException("글 목록 조회 오류(조회순정렬)", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<PictureExt> sortLike(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<PictureExt> list = new ArrayList<>();
		String sql = prop.getProperty("sortLike");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			rset = pstmt.executeQuery();
			while (rset.next()) {
				PictureExt board = handleBoardResultSet(rset);
				board.setAttachCount(rset.getInt("attach_cnt"));
				list.add(board);
			}

		} catch (Exception e) {
			throw new PictureException("글 목록 조회 오류(좋아요순 조회오류)", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	

	/** 이거는 다른거 **/

	public LikeDTO selectLikeOne(Connection conn, String memberId, int no) {
		LikeDTO ld = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLikeOne");
		System.out.println("dao@memberId = " + memberId + "     " + "no = " + no);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, no);
			rset = pstmt.executeQuery();

			if (rset.next()) {
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
		String sql = prop.getProperty("insertLike");

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
		String sql = prop.getProperty("deleteLike");

		try {
			pstmt = conn.prepareStatement(sql);
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

	public int like_count(Connection conn, int no){
		int result = 0;
		PreparedStatement ps = null;
		String sql = prop.getProperty("likecnt");

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			
			result = ps.executeUpdate();
			/*
			 * ResultSet rs = ps.executeQuery();
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps);
		}

		return result;
	}

	//
	public int commentCount(Connection conn, int no) {
		List<LikeDTO> list = new ArrayList<>();
		LikeDTO bc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("likecnt");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				bc = new LikeDTO();

				bc.setMemberId(rset.getString("member_id"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setLikeIt(rset.getString("likeit"));

				list.add(bc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list.size();
	}
}
