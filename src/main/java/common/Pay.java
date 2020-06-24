package common;

import java.sql.Timestamp;

public class Pay {
	private int pryId;
	private int userId;
	private String orderNo;
	private String payPlatform;
	private String platformNumber;
	private String platformStatus;
	private Timestamp updateTime;
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pay(int pryId, int userId, String orderNo, String payPlatform, String platformNumber, String platformStatus,
			Timestamp updateTime) {
		super();
		this.pryId = pryId;
		this.userId = userId;
		this.orderNo = orderNo;
		this.payPlatform = payPlatform;
		this.platformNumber = platformNumber;
		this.platformStatus = platformStatus;
		this.updateTime = updateTime;
	}
	public int getPryId() {
		return pryId;
	}
	public void setPryId(int pryId) {
		this.pryId = pryId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayPlatform() {
		return payPlatform;
	}
	public void setPayPlatform(String payPlatform) {
		this.payPlatform = payPlatform;
	}
	public String getPlatformNumber() {
		return platformNumber;
	}
	public void setPlatformNumber(String platformNumber) {
		this.platformNumber = platformNumber;
	}
	public String getPlatformStatus() {
		return platformStatus;
	}
	public void setPlatformStatus(String platformStatus) {
		this.platformStatus = platformStatus;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Pay [pryId=" + pryId + ", userId=" + userId + ", orderNo=" + orderNo + ", payPlatform=" + payPlatform
				+ ", platformNumber=" + platformNumber + ", platformStatus=" + platformStatus + ", updateTime="
				+ updateTime + "]";
	}
	
}
