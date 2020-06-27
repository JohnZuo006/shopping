package service.impl;

import java.util.List;

import common.Order;
import common.OrderDetails;
import common.OrderT;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.IPayService;
import vo.Main;

public class PayServiceImpl implements IPayService {
	@Override
	public ServerResponse<String> pay_logic(String orderNo) {
		ServerResponse<String> resp = new ServerResponse<>();
		String sql="select * from theOrder where orderNo=?";
		List<OrderT> list=JdbcUtil.executeQuery(sql, OrderT.class, orderNo);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("订单未找到");
		}
		else if(list.get(0).getOrderStatus().equals("未支付"))
		{
			String sql2="select * from orderdetail where orderNo=?";
			List<OrderDetails> details=JdbcUtil.executeQuery(sql2, OrderDetails.class, orderNo);
			Order order=new Order();
			order.setOrder(list.get(0));
			order.setOrderDetail(details);
			Main m=new Main();
			String status=m.test_trade_precreate(order);
			if(status.equals("1")||status.equals("2")||status.equals("3"))
			{
				resp.setStatus(3);
				resp.setMsg("支付宝订单生成失败");
			}
			else
			{
				String sql3="insert into pay(userId,orderNo,payPlatform,"
						+ "platformNumber,platformStatus,createTime,updateTime)"
						+ "values(?,?,'alipay',?,?,now(),now())";
				Object[] params= {order.getUserId(),order.getOrderNo(),"","支付中"};
				int re=JdbcUtil.executeUpdate(sql3, params);
				if(re==1)
				{
					resp.setData("http://ser.zuoxinlei.top:38000/Uploadpic/tmp/qr-"+status+".png");
					resp.setStatus(0);
				}
				else
				{
					resp.setStatus(100);
					resp.setMsg("出现未知错误");
				}
				
			}
			
		}
		else
		{
			resp.setStatus(2);
			resp.setMsg("订单不是未支付状态");
		}

		return resp;
	}

	@Override
	public void call_back(String OrderNo,String tradeNo) {
		// TODO Auto-generated method stub
		String sql="update pay set platformNumber=?,platformStatus='已支付' where orderNo=?";
		int re =JdbcUtil.executeUpdate(sql, tradeNo,OrderNo);
		String sql2="update theOrder set paymentTime=now(),orderStatus='已支付' where orderNo=?";
		int re2=JdbcUtil.executeUpdate(sql2, OrderNo);
		if(re==1&&re2==1)
		{
			System.out.println(OrderNo+"支付成功");
		}
	}

	@Override
	public ServerResponse<Boolean> pay_status_logic(String orderNo) {
		// TODO Auto-generated method stub
		ServerResponse<Boolean> resp=new ServerResponse<Boolean>();
		String sql="select *from theOrder where orderNo=?";
		List<OrderT> list=JdbcUtil.executeQuery(sql, OrderT.class, orderNo);
		if(list.isEmpty())
		{
			resp.setStatus(2);
			resp.setMsg("订单未找到");
		}
		else
		{
			if(list.get(0).getOrderStatus().equals("已支付"))
			{
				resp.setStatus(0);
				resp.setData(true);
			}
			else
			{
				resp.setStatus(1);
				resp.setData(false);
			}
		}
		return resp;
	}

}
