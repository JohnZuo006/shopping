package common;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order {
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
	private Timestamp creatTime;
	private Timestamp updateTime;
	private List<OrderDetails> orderDetail;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, String orderNo, int userId, int addressId, BigDecimal payment, String paymentType,
			BigDecimal postage, String orderStatus, Timestamp paymentTime, Timestamp sendTime, Timestamp endTime,
			Timestamp closeTime, Timestamp creatTime, Timestamp updateTime, List<OrderDetails> orderDetail) {
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
		this.creatTime = creatTime;
		this.updateTime = updateTime;
		this.orderDetail = orderDetail;
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
		return creatTime;
	}
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public List<OrderDetails> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetails> orderDetail) {
		this.orderDetail = orderDetail;
	}
	
}
