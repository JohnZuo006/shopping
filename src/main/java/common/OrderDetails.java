package common;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderDetails {
	private int orderDetailId;
	private String orderNo;
	private int userId;
	private int productId;
	private String productName;
	private String productImage;
	private BigDecimal currentUnitPrice;
	private int quantity;
	private BigDecimal totalPrice;
	private Timestamp creatTime;
	private Timestamp updateTime;
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderDetails(int orderDetailId, String orderNo, int userId, int productId, String productName,
			String productImage, BigDecimal currentUnitPrice, int quantity, BigDecimal totalPrice, Timestamp creatTime,
			Timestamp updateTime) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderNo = orderNo;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.productImage = productImage;
		this.currentUnitPrice = currentUnitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.creatTime = creatTime;
		this.updateTime = updateTime;
	}
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public BigDecimal getCurrentUnitPrice() {
		return currentUnitPrice;
	}
	public void setCurrentUnitPrice(BigDecimal currentUnitPrice) {
		this.currentUnitPrice = currentUnitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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
	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", orderNo=" + orderNo + ", userId=" + userId
				+ ", productId=" + productId + ", productName=" + productName + ", productImage=" + productImage
				+ ", currentUnitPrice=" + currentUnitPrice + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", creatTime=" + creatTime + ", updateTime=" + updateTime + "]";
	}
	
}
