package service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import common.GenNum;
import common.Order;
import common.OrderDetails;
import common.OrderT;
import common.Product;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.IOrderService;

public class OrderServiceImpl implements IOrderService {

	@Override
	public ServerResponse<Order> add_order_logic(String userId, String addressId, List<String> productIds,
			List<String> quantities) {
		ServerResponse<Order> resp=new ServerResponse<Order>();
		//Order order=new Order();
		List<OrderDetails> list=new ArrayList<OrderDetails>();
		//order.setUserId(Integer.parseInt(userId));
		//order.setOrderNo();
		String orderno=GenNum.getOrderCode(Long.parseLong(userId));
		String sql1="select * from product where productId=?";
		BigDecimal paymant = new BigDecimal(0);
		System.out.println(productIds.size());
		//以下是获取
		for(int i=0;i<productIds.size();i++)
		{
			String id=productIds.get(i);
			List<Product> product=JdbcUtil.executeQuery(sql1, Product.class, id);
//			OrderDetails details=new OrderDetails();
//			details.setOrderNo(order.getOrderNo());
//			details.setProductId(product.get(0).getProductId());
//			details.setProductImage(product.get(0).getMainImage());
//			details.setProductName(product.get(0).getProductName());
//			details.setCurrentUnitPrice(product.get(0).getPrice());
//			details.setQuantity(Integer.parseInt(quantities.get(i)));
			String qua=quantities.get(i);
			System.out.println(qua);
			int quai=Integer.parseInt(qua);
			BigDecimal bigde=new BigDecimal(quai);
			BigDecimal total=product.get(0).getPrice().multiply(bigde);
			paymant=paymant.add(total);
//			details.setTotalPrice(total);
			String sql2="insert into orderdetail("
					+ "orderNo,userId,productId,productName,productImage,"
					+ "currentUnitPrice,quantity,totalPrice,creatTime,updateTime)"
					+ "values(?,?,?,?,?,?,?,?,now(),now())";
			Object[] params= {orderno,userId,product.get(0).getProductId(),
					product.get(0).getProductName(),product.get(0).getMainImage(),
					product.get(0).getPrice(),Integer.parseInt(quantities.get(i)),
					total};
			int re=JdbcUtil.executeUpdate(sql2, params);
			if(re==1)
			{
				String sql3="select * from orderdetail where orderNo=?&&productId=?";
				List<OrderDetails> list_orderde=JdbcUtil.executeQuery(sql3, OrderDetails.class,orderno
						,product.get(0).getProductId());
				list.addAll(list_orderde);
			}
		}
		String sql4="insert into theOrder("
				+ "orderNo,userId,addressId,payment,paymentType,postage,orderStatus,creatTime,updateTime)"
				+ "values(?,?,?,?,?,?,?,now(),now())";
		Object[] params= {orderno,userId,addressId,paymant,"alipay",0,"未支付"};
		int re2=JdbcUtil.executeUpdate(sql4, params);
		if(re2==1)
		{
			String sql5="select * from theOrder where orderNo=?";
			List<OrderT> list_order=JdbcUtil.executeQuery(sql5, OrderT.class, orderno);
			Order order=new Order(list_order.get(0),null);
			order.setOrderDetail(list);
			resp.setStatus(0);
			resp.setData(order);
		}
		else
		{
			resp.setStatus(1);
			resp.setMsg("订单创建失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<List<Order>> list_order_logic(String userId) {
		ServerResponse<List<Order>> resp=new ServerResponse<>();
		
		return resp;
	}
	
}
