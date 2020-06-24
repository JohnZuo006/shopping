package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import common.Address;
import common.GenNum;
import common.Order;
import common.OrderDetails;
import common.OrderT;
import common.Product;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.IOrderService;
import vo.Page;

public class OrderServiceImpl implements IOrderService {

	@Override
	public ServerResponse<Order> add_order_logic(String userId, String addressId, List<String> productIds,
			List<String> quantities) {
		ServerResponse<Order> resp = new ServerResponse<Order>();
		// Order order=new Order();
		List<OrderDetails> list = new ArrayList<OrderDetails>();
		// order.setUserId(Integer.parseInt(userId));
		// order.setOrderNo();
		String orderno = GenNum.getOrderCode(Long.parseLong(userId));
		String sql1 = "select * from product where productId=?";
		String sql6="update product set stock=? where productId=?";
		BigDecimal paymant = new BigDecimal(0);
		System.out.println(productIds.size());
		// 以下是获取
		for (int i = 0; i < productIds.size(); i++) {
			String id = productIds.get(i);
			List<Product> product = JdbcUtil.executeQuery(sql1, Product.class, id);
			String qua = quantities.get(i);
			int quai = Integer.parseInt(qua);
			JdbcUtil.executeUpdate(sql6,product.get(0).getStock()-quai ,id);
			System.out.println(qua);
			
			BigDecimal bigde = new BigDecimal(quai);
			BigDecimal total = product.get(0).getPrice().multiply(bigde);
			paymant = paymant.add(total);
			
			//订单详情写入到数据库
			String sql2 = "insert into orderdetail(" + "orderNo,userId,productId,productName,productImage,"
					+ "currentUnitPrice,quantity,totalPrice,creatTime,updateTime)"
					+ "values(?,?,?,?,?,?,?,?,now(),now())";
			Object[] params = { orderno, userId, product.get(0).getProductId(), product.get(0).getProductName(),
					product.get(0).getMainImage(), product.get(0).getPrice(), Integer.parseInt(quantities.get(i)),
					total };
			int re = JdbcUtil.executeUpdate(sql2, params);
			if (re == 1) {
				String sql3 = "select * from orderdetail where orderNo=?&&productId=?";
				List<OrderDetails> list_orderde = JdbcUtil.executeQuery(sql3, OrderDetails.class, orderno,
						product.get(0).getProductId());
				list.addAll(list_orderde);
			}
		}
		//将订单信息写入到数据库
		String sql4 = "insert into theOrder("
				+ "orderNo,userId,addressId,payment,paymentType,postage,orderStatus,createTime,updateTime)"
				+ "values(?,?,?,?,?,?,?,now(),now())";
		Object[] params = { orderno, userId, addressId, paymant, "alipay", 0, "未支付" };
		int re2 = JdbcUtil.executeUpdate(sql4, params);
		if (re2 == 1) {
			String sql5 = "select * from theOrder where orderNo=?";
			List<OrderT> list_order = JdbcUtil.executeQuery(sql5, OrderT.class, orderno);
			Order order = new Order();
			order.setOrder(list_order.get(0));
			order.setOrderDetail(list);
			resp.setStatus(0);
			resp.setData(order);
		} else {
			resp.setStatus(1);
			resp.setMsg("订单创建失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<Page<List<Order>>> list_order_logic(String userId, int pageSize, int pageNum) {
		ServerResponse<Page<List<Order>>> resp = new ServerResponse<>();
		// 获取数据库中数据总数
		String sql = "select count(*) as count from theOrder where userId=?";
		int sum = JdbcUtil.getSum(sql, userId);
		// 计算分页总数
		int pages = sum % pageSize == 0 ? sum / pageSize : (sum / pageSize) + 1;
		System.out.println("pages=" + pages);
		System.out.println("sum=" + sum);
		// 计算当前页的开始行和结束行
		int startRow = (pageNum - 1) * pageSize;
		int endRow = startRow + pageSize > sum ? sum : startRow + pageSize;
		// 获取当前页的数据
		String sql2 = "select * from theOrder where userId=? order by orderId desc limit ?,?";
		List<OrderT> list = JdbcUtil.executeQuery(sql2, OrderT.class,userId, startRow, pageSize);
		List<Order> orders=new ArrayList<Order>();
		for(OrderT order:list)
		{
			String orderNo=order.getOrderNo();
			String sql3="select * from orderdetail where orderNo=?";
			List<OrderDetails> orderDeatils=JdbcUtil.executeQuery(sql3, OrderDetails.class, orderNo);
			Order neworder=new Order();
			if(!orderDeatils.isEmpty())
			{
				neworder.setOrder(order);
				neworder.setOrderDetail(orderDeatils);
				orders.add(neworder);
			}
		}
		// 将数据赋值到page里面
		Page<List<Order>> page = new Page<List<Order>>();
		page.setPages(pages);
		page.setTotal(sum);
		page.setEndRow(endRow);
		page.setStartRow(startRow);
		page.setList(orders);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setFirstPage(1);
		page.setLastPage(pages);
		// 判断分页的前后是否还有页
		if (pageNum == 1) {
			page.setFirstPage(true);
			page.setPrePage(0);
			page.setLastPage(false);
			page.setNextPage(pageNum + 1);
			page.setHasNextPage(true);
			page.setHasPreviousPage(false);
		} else if (pageNum == pages) {
			page.setFirstPage(false);
			page.setLastPage(true);
			page.setPrePage(pageNum - 1);
			page.setNextPage(0);
			page.setHasNextPage(false);
			page.setHasPreviousPage(true);
		} else {
			page.setFirstPage(false);
			page.setLastPage(false);
			page.setPrePage(pageNum - 1);
			page.setNextPage(pageNum + 1);
		}
		resp.setStatus(0);
		resp.setData(page);

		return resp;
	}

	@Override
	public ServerResponse<Page<List<Order>>> admin_list_order_logic(int pageSize, int pageNum) {
		ServerResponse<Page<List<Order>>> resp = new ServerResponse<>();
		// 获取数据库中数据总数
		String sql = "select count(*) as count from theOrder";
		int sum = JdbcUtil.getSum(sql);
		// 计算分页总数
		int pages = sum % pageSize == 0 ? sum / pageSize : (sum / pageSize) + 1;
		System.out.println("pages=" + pages);
		System.out.println("sum=" + sum);
		// 计算当前页的开始行和结束行
		int startRow = (pageNum - 1) * pageSize;
		int endRow = startRow + pageSize > sum ? sum : startRow + pageSize;
		// 获取当前页的数据
		String sql2 = "select * from theOrder order by orderId desc limit ?,?";
		List<OrderT> list = JdbcUtil.executeQuery(sql2, OrderT.class, startRow, pageSize);
		List<Order> orders=new ArrayList<Order>();
		for(OrderT order:list)
		{
			String orderNo=order.getOrderNo();
			String sql3="select * from orderdetail where orderNo=?";
			List<OrderDetails> orderDeatils=JdbcUtil.executeQuery(sql3, OrderDetails.class, orderNo);
			Order neworder=new Order();
			if(!orderDeatils.isEmpty())
			{
				neworder.setOrder(order);
				neworder.setOrderDetail(orderDeatils);
				orders.add(neworder);
			}
		}
		// 将数据赋值到page里面
		Page<List<Order>> page = new Page<List<Order>>();
		page.setPages(pages);
		page.setTotal(sum);
		page.setEndRow(endRow);
		page.setStartRow(startRow);
		page.setList(orders);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setFirstPage(1);
		page.setLastPage(pages);
		// 判断分页的前后是否还有页
		if (pageNum == 1) {
			page.setFirstPage(true);
			page.setPrePage(0);
			page.setLastPage(false);
			page.setNextPage(pageNum + 1);
			page.setHasNextPage(true);
			page.setHasPreviousPage(false);
		} else if (pageNum == pages) {
			page.setFirstPage(false);
			page.setLastPage(true);
			page.setPrePage(pageNum - 1);
			page.setNextPage(0);
			page.setHasNextPage(false);
			page.setHasPreviousPage(true);
		} else {
			page.setFirstPage(false);
			page.setLastPage(false);
			page.setPrePage(pageNum - 1);
			page.setNextPage(pageNum + 1);
		}
		resp.setStatus(0);
		resp.setData(page);

		return resp;
	}

	@Override
	public ServerResponse<Order> detail_order_logic(String userId, String orderNo) {
		// TODO Auto-generated method stub
		ServerResponse<Order> resp = new ServerResponse<Order>();
		String sql="select * from theOrder where orderNo=?&&userId=?";
		List<OrderT> list=JdbcUtil.executeQuery(sql, OrderT.class, orderNo,userId);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("查询不到这个订单或不是本人订单");
		}
		else
		{
			String sql2="select * from orderdetail where orderNo=?";
			List<OrderDetails> details=JdbcUtil.executeQuery(sql2, OrderDetails.class, orderNo);
			Order order=new Order();
			order.setOrder(list.get(0));
			order.setOrderDetail(details);
			String sql3="select * from address where addressId=?";
			List<Address> address=JdbcUtil.executeQuery(sql3, Address.class, list.get(0).getAddressId());
			order.setAddress(address.get(0));
			resp.setStatus(0);
			resp.setData(order);
		}
		return resp;
	}

	@Override
	public ServerResponse<Order> admin_detail_order_logic(String orderNo) {
		// TODO Auto-generated method stub
		ServerResponse<Order> resp = new ServerResponse<Order>();
		String sql="select * from theOrder where orderNo=?";
		List<OrderT> list=JdbcUtil.executeQuery(sql, OrderT.class, orderNo);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("查询不到这个订单");
		}
		else
		{
			String sql2="select * from orderdetail where orderNo=?";
			List<OrderDetails> details=JdbcUtil.executeQuery(sql2, OrderDetails.class, orderNo);
			Order order=new Order();
			order.setOrder(list.get(0));
			order.setOrderDetail(details);
			String sql3="select * from address where addressId=?";
			List<Address> address=JdbcUtil.executeQuery(sql3, Address.class, list.get(0).getAddressId());
			order.setAddress(address.get(0));
			resp.setStatus(0);
			resp.setData(order);
		}
		return resp;
	}

	@Override
	public ServerResponse<Order> cancel_order_logic(String userId, String orderNo) {
		ServerResponse<Order> resp = new ServerResponse<Order>();
		String sql="select * from theOrder where orderNo=?&&userId=?";
		List<OrderT> list=JdbcUtil.executeQuery(sql, OrderT.class, orderNo,userId);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("查询不到这个订单或不是本人订单");
		}
		else
		{
			if(!list.get(0).getOrderStatus().equals("未支付"))
			{
				resp.setStatus(2);
				resp.setMsg("订单已支付，不能取消");
			}
			else
			{
				String sql2="update theOrder set orderStatus='已取消',closeTime=now() where orderNo=?";
				int re=JdbcUtil.executeUpdate(sql2, orderNo);
				if(re==1)
				{
					resp.setStatus(0);
					resp.setMsg("订单取消成功");
				}
			}
		}
		return resp;
	}

	@Override
	public ServerResponse<Order> admin_send_order_logic(String orderNo) {
		ServerResponse<Order> resp = new ServerResponse<Order>();
		String sql="select * from theOrder where orderNo=?";
		List<OrderT> list=JdbcUtil.executeQuery(sql, OrderT.class, orderNo);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("查询不到这个订单");
		}
		else
		{
			if(!list.get(0).getOrderStatus().equals("已支付"))
			{
				resp.setStatus(2);
				resp.setMsg("订单不是已支付状态，不能发货");
			}
			else
			{
				String sql2="update theOrder set orderStatus='已发货',sendTime=now() where orderNo=?";
				int re=JdbcUtil.executeUpdate(sql2, orderNo);
				if(re==1)
				{
					resp.setStatus(0);
					resp.setMsg("订单发送成功");
				}
			}
		}
		return resp;
	}

	
}
