package common;

import java.sql.Timestamp;

public class Trolley {
     private int trolleyId;
     private int userId;
     private int productId;
     private int quantity;
     private String checked;
 	private Timestamp createTime;
 	private Timestamp updateTime;
     
 
	
	public Trolley() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Trolley(int trolleyId, int userId, int productId, int quantity, String checked, Timestamp createTime,
			Timestamp updateTime) {
		super();
		this.trolleyId = trolleyId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.checked = checked;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Trolley [trolleyId=" + trolleyId + ", userId=" + userId + ", productId=" + productId + ", quantity="
				+ quantity + ", checked=" + checked + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	public int getTrolleyId() {
		return trolleyId;
	}
	public void setTrolleyId(int trolleyId) {
		this.trolleyId = trolleyId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
	
     
}
