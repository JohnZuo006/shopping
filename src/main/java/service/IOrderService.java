package service;

import java.util.List;

import common.Order;
import common.ServerResponse;

public interface IOrderService {
	public ServerResponse<Order> add_order_logic(String userId,String addressId,List<String> productIds,List<String> quantities);
	public ServerResponse<List<Order>> list_order_logic(String userId);
}
