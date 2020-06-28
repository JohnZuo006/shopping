package service;

import java.util.List;

import common.Order;
import common.ServerResponse;
import vo.Page;

public interface IOrderService {
	public ServerResponse<Order> add_order_logic(String userId,String addressId,List<String> productIds,List<String> quantities);
	public ServerResponse<Page<List<Order>>> list_order_logic(String userId, int pageSize, int pageNum,String otype);
	public ServerResponse<Page<List<Order>>> admin_list_order_logic(int pageSize, int pageNum);
	public ServerResponse<Order> detail_order_logic(String userId,String orderNo);
	public ServerResponse<Order> admin_detail_order_logic(String orderNo);
	public ServerResponse<Order> cancel_order_logic(String userId,String orderNo);
	public ServerResponse<Order> admin_send_order_logic(String orderNo);
}
