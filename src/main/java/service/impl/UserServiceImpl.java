package service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ServerResponse;
import common.User;
import jdbcUtil.JdbcUtil;
import service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public ServerResponse<User> login_logic(String username, String password) {
		// TODO Auto-generated method stub
		ServerResponse<User> resp=new ServerResponse<User>();
		List<User> users=new ArrayList<User>();
		users=JdbcUtil.executeQuery("select * from user where userName=?", User.class, username);
		if(users.size()==0) {
			resp.setStatus(2);
			resp.setMsg("用户不存在");
		}
		else {
			users.clear();
			users=JdbcUtil.executeQuery("select * from user where userName=?&&passWord=?", User.class, username,password);
			if(users.size()==0)
			{
				resp.setStatus(1);
				resp.setMsg("密码错误");
			}
			else
			{
				User user=users.get(0);
				resp.setStatus(0);
				resp.setData(user);
			}
		}
		return resp;
	}
	@Override
	public ServerResponse<User> register_logic(User user)
	{
		ServerResponse<User> resp=new ServerResponse<User>();
		List<User> users=new ArrayList<User>();
		users=JdbcUtil.executeQuery("select * from user where userName=?", User.class, user.getUserName());
		if(users.size()>0) {
			resp.setStatus(1);
			resp.setMsg("用户已存在");
		}
		else
		{
			String sql="insert into user(userName,passWord,telephone,email,role,question,answer,createTime) values(?,?,?,?,?,?,?,?)";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ts=df.format(new Date());
			Object[] params= {user.getUserName(),user.getPassWord(),user.getTelephone(),user.getEmail(),user.getRole(),user.getQuestion(),user.getAnswer(),ts};
			int i=JdbcUtil.executeUpdate(sql, params);
			if(i==1)
			{
				resp.setStatus(0);
				resp.setMsg("校验成功");
			}
		}
		return resp;
	}
	@Override
	public ServerResponse<User> loginAdmin_logic(String username,String password)
	{
		ServerResponse<User> resp=new ServerResponse<User>();
		List<User> users=new ArrayList<User>();
		users=JdbcUtil.executeQuery("select * from user where userName=?", User.class, username);
		if(users.size()==0) {
			resp.setStatus(2);
			resp.setMsg("用户不存在");
		}
		else if(!(users.get(0).getRole().equals("admin")))
		{
			resp.setStatus(3);
			resp.setMsg("该用户不是管理员");
		}
		else {
			users.clear();
			users=JdbcUtil.executeQuery("select * from user where userName=?&&passWord=?", User.class, username,password);
			if(users.size()==0)
			{
				resp.setStatus(1);
				resp.setMsg("密码错误");
			}
			else
			{
				User user=users.get(0);
				resp.setStatus(0);
				resp.setData(user);
			}
		}
		return resp;
	}
}