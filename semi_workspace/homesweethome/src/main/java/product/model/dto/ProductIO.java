package product.model.dto;

import java.sql.Timestamp;

public class ProductIO {
	private int no;
	private String productId;
	private int count;
	private Status status;
	private Timestamp ioDateTime;

	public ProductIO() {
		super();
	}

	public ProductIO(int no, String productId, int count, Status status, Timestamp ioDateTime) {
		super();
		this.no = no;
		this.productId = productId;
		this.count = count;
		this.status = status;
		this.ioDateTime = ioDateTime;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timestamp getIoDateTime() {
		return ioDateTime;
	}

	public void setIoDateTime(Timestamp ioDateTime) {
		this.ioDateTime = ioDateTime;
	}

	@Override
	public String toString() {
		return "ProductIO [no=" + no + ", productId=" + productId + ", count=" + count + ", status=" + status
				+ ", ioDateTime=" + ioDateTime + "]";
	}
}