package service.impl;

import java.util.ArrayList;
import java.util.List;

import common.ServerResponse;
import common.Trolley;
import jdbcUtil.JdbcUtil;
import service.TrolleyService;

public class TrolleyServiceImpl implements TrolleyService{

	public ServerResponse<Trolley> add_logic(String userid,String productid,String quantity) {
		// TODO Auto-generated method stub
		ServerResponse<Trolley> resp=new ServerResponse<Trolley>();
		List<Trolley> trolley=new ArrayList<Trolley>();
		trolley=JdbcUtil.executeQuery("select * from trolley where userId=? and productId=?", Trolley.class, userid,productid);
		if(trolley.size()>0) {
			resp.setStatus(2);
			resp.setData(trolley.get(0));
			resp.setMsg("该商品已经在购物车");
		}
		else
		{ 
			//checked默认为1,已勾选,  0 未勾选
			String sql="insert into trolley(userId,productId,quantity,checked,createTime,updateTime) values(?,?,?,1,now(),now())";
			//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//String ts=df.format(new Date());
			Object[] params= {userid,productid,quantity};
			int i=JdbcUtil.executeUpdate(sql, params);
			if(i==1)
			{
				Trolley tro = new Trolley();
				tro.setChecked("1");
				tro.setProductId(Integer.parseInt(productid));
				tro.setQuantity(Integer.parseInt(quantity));
				tro.setUserId(Integer.parseInt(userid));
				resp.setData(tro);
				resp.setStatus(0);
				resp.setMsg("添加到购物车成功");
			}else {
				resp.setStatus(3);
				resp.setMsg("添加失败");
			}
		}
		return resp;
	}

	@Override
	public ServerResponse<List<Trolley>> listTrolley_logic(String userid) {
		// TODO Auto-generated method stub
		ServerResponse<List<Trolley>> sr = new ServerResponse<List<Trolley>>();
    	String sql = "select * from trolley where userId=?";
		List<Trolley> list = JdbcUtil.executeQuery(sql, Trolley.class,userid);
		if(list.size()>0) {	
			sr.setData(list);
			sr.setStatus(0);
		}else {
			sr.setStatus(1);
			sr.setMsg("购物车为空");
		}

    	return sr;

	}

	@Override
	public ServerResponse<Trolley> updateQuantity_logic(String userid, String productid, String quantity) {
		// TODO Auto-generated method stub
		ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
    	String sql = "update trolley set quantity=? where userId=? and productId=?";
    	int i=JdbcUtil.executeUpdate(sql,quantity,userid,productid);
		if(i==1) {
			Trolley tro = new Trolley();
			tro.setProductId(Integer.parseInt(productid));
			tro.setQuantity(Integer.parseInt(quantity));
			tro.setUserId(Integer.parseInt(userid));
			sr.setData(tro);
			sr.setStatus(0);
			sr.setMsg("数量更新成功");
		}else {
			sr.setStatus(2);
			sr.setMsg("该商品已不在购物车");
		}

    	return sr;
	}

	@Override
	public ServerResponse<Trolley> delete_logic(String userid, String productid) {
		// TODO Auto-generated method stub
		ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
    	String sql = "delete from trolley where userId=? and productId=?";
    	int i=JdbcUtil.executeUpdate(sql, userid,productid);
		if(i==1) {

			Trolley tro = new Trolley();
			tro.setProductId(Integer.parseInt(productid));
			tro.setUserId(Integer.parseInt(userid));
			sr.setData(tro);
			sr.setStatus(0);
			sr.setMsg("从购物车中删除成功");
		}else {
			sr.setStatus(2);
			sr.setMsg("该商品已不在购物车");
		}

    	return sr;
	}

	@Override
	public ServerResponse<Trolley> checked_logic(String userid, String productid) {
		// TODO Auto-generated method stub
		ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
		String sql = "update trolley set checked=1 where userId=? and productId=?";
		int i=JdbcUtil.executeUpdate(sql, userid,productid);
		if(i==1) {
			Trolley tro = new Trolley();
			tro.setProductId(Integer.parseInt(productid));
			tro.setUserId(Integer.parseInt(userid));
			tro.setChecked("1");
			sr.setData(tro);
			sr.setStatus(0);
			sr.setMsg("已勾选");
		}else {
			sr.setStatus(2);
			sr.setMsg("该商品已不再购物车");
		}

    	return sr;
	}

	@Override
	public ServerResponse<Trolley> unchecked_logic(String userid, String productid) {
		// TODO Auto-generated method stub
		ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
		String sql = "update trolley set checked=0 where userId=? and productId=?";
		int i=JdbcUtil.executeUpdate(sql,userid,productid);
		if(i==1) {
			Trolley tro = new Trolley();
            tro.setChecked("0");
			tro.setProductId(Integer.parseInt(productid));
			tro.setUserId(Integer.parseInt(userid));
			sr.setData(tro);
			sr.setStatus(0);
			sr.setMsg("取消勾选");
		}else {
			sr.setStatus(2);
			sr.setMsg("该商品已不在购物车内");
		}

    	return sr;
		
	}

	@Override
	public ServerResponse<Integer> searchCount_logic(String userid) {
		// TODO Auto-generated method stub
		String sql = "select count(*) as count from trolley where userId=?";
		Integer count = JdbcUtil.getSum(sql, userid);
		ServerResponse<Integer> sr = new ServerResponse<Integer>();
		if(count>=0) {	
			sr.setStatus(0);
			sr.setData(count);
			sr.setMsg("查询数量成功");
		}else {
			sr.setMsg("查询失败");
			sr.setStatus(1);
		}
		
		return sr;
	}

	public ServerResponse<List<Trolley>> allChecked_logic(String userid) {
		// TODO Auto-generated method stub
		String sql = "update trolley set checked=1 where userId=?";
		JdbcUtil.executeUpdate(sql, userid);
		ServerResponse<List<Trolley>> sr = listTrolley_logic(userid);
    	return sr;
	}

	@Override
	public ServerResponse<List<Trolley>> allUnchecked_logic(String userid) {
		// TODO Auto-generated method stub
		String sql = "update trolley set checked=0 where userId=?";
		JdbcUtil.executeUpdate(sql, userid);
		ServerResponse<List<Trolley>> sr = listTrolley_logic(userid);
    	return sr;
	}
 
}
