package community.model.dto;

public class LikeDTO{
	private String memberId;
	private int boardNo;
	private String likeIt;
	public LikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeDTO(String memberId, int boardNo, String likeIt) {
		super();
		this.memberId = memberId;
		this.boardNo = boardNo;
		this.likeIt = likeIt;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getLikeIt() {
		return likeIt;
	}
	public void setLikeIt(String likeIt) {
		this.likeIt = likeIt;
	}
	

	
}
