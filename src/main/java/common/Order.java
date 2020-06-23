package common;

import java.util.List;

public class Order {
	private OrderT order;
	private List<OrderDetails> orderDetail;
	private Address address;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(OrderT order, List<OrderDetails> orderDetail) {
		super();
		this.order = order;
		this.orderDetail = orderDetail;
	}
	public OrderT getOrder() {
		return order;
	}
	public void setOrder(OrderT order) {
		this.order = order;
	}
	public List<OrderDetails> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetails> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
