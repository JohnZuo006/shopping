package service.impl;

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
				user.setPassWord("");
				user.setAnswer("");
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
	public ServerResponse<User> changePassword_logic(String username,String oldPassword, String newPassword)
	{
		ServerResponse<User> resp=new ServerResponse<User>();
		List<User> users=new ArrayList<User>();
		users=JdbcUtil.executeQuery("select * from user where userName=?&&passWord=?", User.class, username,oldPassword);
		if(users.size()==0) {
			resp.setStatus(1);
			resp.setMsg("原密码错误");
		}
		else {
			int i=JdbcUtil.executeUpdate("update from user set passWord=? where userName=?", newPassword,username);
			if(i==1)
			{
				resp.setStatus(0);
				resp.setMsg("修改密码成功");
			}
		}
		return resp;
	}
	public ServerResponse<User> checkAnswer_logic(String username,String question,String answer)
	{
		ServerResponse<User> resp=new ServerResponse<User>();
		List<User> users=new ArrayList<User>();
		users=JdbcUtil.executeQuery("select * from user where userName=?&&question=?&&answer=?", User.class, username,question,answer);
		if(users.size()==0) {
			resp.setStatus(1);
			resp.setMsg("密保问题验证错误");
		}
		else {
			resp.setStatus(0);
			resp.setMsg("密保问题验证成功");
		}
		return resp;
	}
	public ServerResponse<User> findPassword_logic(String username,String password){
		ServerResponse<User> resp=new ServerResponse<User>();
		int i=JdbcUtil.executeUpdate("update from user set passWord=? where userName=?", password,username);
		if(i==1) {
			resp.setStatus(0);
			resp.setMsg("重置密码成功");
		}
		else {
			resp.setStatus(1);
			resp.setMsg("重置密码失败");
		}
		return resp;
	}
	@Override
	public ServerResponse<String> changeinformation_logic(User user) {
		// TODO Auto-generated method stub
		ServerResponse<User> osr = getinformation_logic(user.getUserName());
		osr.getData().setTelephone(user.getTelephone());
		osr.getData().setEmail(user.getEmail());
		osr.getData().setQuestion(user.getQuestion());
		osr.getData().setAnswer(user.getAnswer());
		String sql="SELECT * FROM user WHERE userid=?";
		int re = JdbcUtil.executeUpdate(sql, osr,osr.getData().getUserId());
		ServerResponse<String> sr=new ServerResponse<String>();
		if(re==1) {
			sr.setStatus(0);
			sr.setData("更新个人信息成功");
		}else {
			sr.setStatus(1);
			sr.setData("更新失败");
		}
			
		return null;
	}
	@Override
	public ServerResponse<User> getinformation_logic(String username) { //获取个人信息
		// TODO Auto-generated method stub
		String sql="SELECT * from user where userName=?";
		List<User> list=JdbcUtil.executeQuery(sql,User.class,username);
		User user = list.get(0);
		 ServerResponse<User> sr = new ServerResponse<User>();
		 sr.setStatus(0);
		 sr.setData(user);
		return sr;
	}
	@Override
	public ServerResponse<String> checkname_logic(String username) {  //检查名字
		// TODO Auto-generated method stub
		ServerResponse<User> sr = getinformation_logic(username);
		ServerResponse<String> re = new  ServerResponse<String>();
		if(sr.getData().getUserName().equals(username)) {
			re.setData("用户已存在");
			re.setStatus(1);
		}else {
			re.setData("校验成功");
			re.setStatus(0);
		}
		return re;
	}
	
	
	@Override
	public ServerResponse<List> listuser_logic(String role) {   //查询列表
		// TODO Auto-generated method stub
		String sql="SELECT * from user where role=?";
		 List<User> list=JdbcUtil.executeQuery(sql,User.class,role);
		 ServerResponse<List> sr = new ServerResponse<List>();
		 sr.setStatus(0);
		 sr.setData(list);
		 return sr;
	}
	
}
