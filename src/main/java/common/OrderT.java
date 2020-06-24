package common;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderT {
	private int orderId;
	private String orderNo;
	private int userId;
	private int addressId;
	private BigDecimal payment;
	private String paymentType;
	private BigDecimal postage;
	private String orderStatus;
	private Timestamp paymentTime;
	private Timestamp sendTime;
	private Timestamp endTime;
	private Timestamp closeTime;
	private Timestamp createTime;
	private Timestamp updateTime;
	public OrderT() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderT(int orderId, String orderNo, int userId, int addressId, BigDecimal payment, String paymentType,
			BigDecimal postage, String orderStatus, Timestamp paymentTime, Timestamp sendTime, Timestamp endTime,
			Timestamp closeTime, Timestamp creatTime, Timestamp updateTime) {
		super();
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.userId = userId;
		this.addressId = addressId;
		this.payment = payment;
		this.paymentType = paymentType;
		this.postage = postage;
		this.orderStatus = orderStatus;
		this.paymentTime = paymentTime;
		this.sendTime = sendTime;
		this.endTime = endTime;
		this.closeTime = closeTime;
		this.createTime = creatTime;
		this.updateTime = updateTime;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public BigDecimal getPostage() {
		return postage;
	}
	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Timestamp getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Timestamp paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Timestamp closeTime) {
		this.closeTime = closeTime;
	}
	public Timestamp getCreatTime() {
		return createTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.createTime = creatTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "OrderT [orderId=" + orderId + ", orderNo=" + orderNo + ", userId=" + userId + ", addressId=" + addressId
				+ ", payment=" + payment + ", paymentType=" + paymentType + ", postage=" + postage + ", orderStatus="
				+ orderStatus + ", paymentTime=" + paymentTime + ", sendTime=" + sendTime + ", endTime=" + endTime
				+ ", closeTime=" + closeTime + ", creatTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
