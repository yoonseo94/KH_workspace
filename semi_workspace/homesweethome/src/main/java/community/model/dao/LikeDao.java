/*
 * package community.model.dao;
 * 
 * import java.io.FileReader; import java.io.IOException; import
 * java.sql.Connection; import java.sql.PreparedStatement; import
 * java.sql.ResultSet; import java.util.ArrayList; import java.util.List; import
 * java.util.Properties;
 * 
 * import common.JdbcTemplate; import community.model.dto.LikeDTO;
 * 
 * public class LikeDao {
 * 
 * private Properties prop = new Properties();
 * 
 * public LikeDao() { String fileName =
 * LikeDao.class.getResource("/sql/picture.properties").getPath(); try {
 * prop.load(new FileReader(fileName)); } catch (IOException e) {
 * e.printStackTrace(); } }
 * 
 * // 좋아요 추가하기 public void insertLike(LikeDTO like) throws Exception{ Connection
 * con = JdbcTemplate.getConnection();
 * 
 * 
 * String sql = "insert into like_img values(?,?)" ; PreparedStatement ps =
 * con.prepareStatement(sql); ps.setString(1,like.getMemberId()); ps.setInt(2,
 * like.getBoardNo());
 * 
 * ps.execute();
 * 
 * con.close(); }
 * 
 * // 좋아요 삭제 public void deleteLike(LikeDTO like) throws Exception{ Connection
 * con=JdbcTemplate.getConnection();
 * 
 * String sql = "delete like_img where member_id = ? and board_no = ?";
 * PreparedStatement ps = con.prepareStatement(sql);
 * ps.setString(1,like.getMemberId()); ps.setInt(2,like.getBoardNo());
 * ps.execute(); con.close(); }
 * 
 * public boolean like_search(String memberId, int no) throws Exception {
 * Connection con = JdbcTemplate.getConnection();
 * 
 * String sql = "select * from like_img where member_id=? and board_no = ?";
 * PreparedStatement ps = con.prepareStatement(sql); ps.setString(1, memberId);
 * ps.setInt(2, no);
 * 
 * ResultSet rs = ps.executeQuery();
 * 
 * boolean result = false; if(rs.next()) { result = true; } con.close();
 * 
 * return result; }
 * 
 * public int likecount (int no) throws Exception { Connection con
 * =JdbcTemplate.getConnection();
 * 
 * String sql =
 * "select board_no, count(*) like_count from like_img where board_no=? group by board_no"
 * ; PreparedStatement ps = con.prepareStatement(sql); ps.setInt(1, no);
 * 
 * ResultSet rs = ps.executeQuery();
 * 
 * int like_count = 0; if(rs.next()) { like_count = rs.getInt("no"); }
 * con.close();
 * 
 * return like_count; }
 * 
 * }
 */