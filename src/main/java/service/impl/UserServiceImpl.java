package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ServerResponse;
import common.User;
import jdbcUtil.JdbcUtil;
import service.UserService;
import vo.Page;

public class UserServiceImpl implements UserService {

	public ServerResponse<User> login_logic(String username, String password) {
		// TODO Auto-generated method stub
		ServerResponse<User> resp = new ServerResponse<User>();
		List<User> users = new ArrayList<User>();
		users = JdbcUtil.executeQuery("select * from user where userName=?", User.class, username);
		if (users.size() == 0) {
			resp.setStatus(2);
			resp.setMsg("用户不存在");
		} else {
			users.clear();
			users = JdbcUtil.executeQuery("select * from user where userName=?&&passWord=?", User.class, username,
					password);
			if (users.size() == 0) {
				resp.setStatus(1);
				resp.setMsg("密码错误");
			} else {
				User user = users.get(0);
				user.setPassWord("");
				resp.setStatus(0);
				resp.setData(user);
			}
		}
		return resp;
	}

	@Override
	public ServerResponse<User> register_logic(User user) {
		ServerResponse<User> resp = new ServerResponse<User>();
		List<User> users = new ArrayList<User>();
		users = JdbcUtil.executeQuery("select * from user where userName=?", User.class, user.getUserName());
		if (users.size() > 0) {
			resp.setStatus(1);
			resp.setMsg("用户已存在");
		} else {
			String sql = "insert into user(userName,passWord,telephone,email,role,question,answer,createTime) values(?,?,?,?,?,?,?,?)";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ts = df.format(new Date());
			Object[] params = { user.getUserName(), user.getPassWord(), user.getTelephone(), user.getEmail(),
					user.getRole(), user.getQuestion(), user.getAnswer(), ts };
			int i = JdbcUtil.executeUpdate(sql, params);
			if (i == 1) {
				resp.setStatus(0);
				resp.setMsg("校验成功");
			}
		}
		return resp;
	}

	@Override
	public ServerResponse<User> loginAdmin_logic(String username, String password) {
		ServerResponse<User> resp = new ServerResponse<User>();
		List<User> users = new ArrayList<User>();
		users = JdbcUtil.executeQuery("select * from user where userName=?", User.class, username);
		if (users.size() == 0) {
			resp.setStatus(2);
			resp.setMsg("用户不存在");
		} else if (!(users.get(0).getRole().equals("admin"))) {
			resp.setStatus(3);
			resp.setMsg("该用户不是管理员");
		} else {
			users.clear();
			users = JdbcUtil.executeQuery("select * from user where userName=?&&passWord=?", User.class, username,
					password);
			if (users.size() == 0) {
				resp.setStatus(1);
				resp.setMsg("密码错误");
			} else {
				User user = users.get(0);
				resp.setStatus(0);
				resp.setData(user);
			}
		}
		return resp;
	}

	public ServerResponse<User> changePassword_logic(String username, String oldPassword, String newPassword) {
		ServerResponse<User> resp = new ServerResponse<User>();
		List<User> users = new ArrayList<User>();
		users = JdbcUtil.executeQuery("select * from user where userName=?&&passWord=?", User.class, username,
				oldPassword);
		if (users.size() == 0) {
			resp.setStatus(1);
			resp.setMsg("原密码错误");
		} else {
			int i = JdbcUtil.executeUpdate("update user set passWord=? where userName=?", newPassword, username);
			if (i == 1) {
				resp.setStatus(0);
				resp.setMsg("修改密码成功");
			}
		}
		return resp;
	}

	public ServerResponse<User> checkAnswer_logic(String username, String question, String answer) {
		ServerResponse<User> resp = new ServerResponse<User>();
		List<User> users = new ArrayList<User>();
		users = JdbcUtil.executeQuery("select * from user where userName=?&&question=?&&answer=?", User.class, username,
				question, answer);
		if (users.size() == 0) {
			resp.setStatus(1);
			resp.setMsg("密保问题验证错误");
		} else {
			resp.setStatus(0);
			resp.setMsg("密保问题验证成功");
		}
		return resp;
	}

	public ServerResponse<User> findPassword_logic(String username, String password) {
		ServerResponse<User> resp = new ServerResponse<User>();
		int i = JdbcUtil.executeUpdate("update user set passWord=? where userName=?", password, username);
		if (i == 1) {
			resp.setStatus(0);
			resp.setMsg("重置密码成功");
		} else {
			resp.setStatus(1);
			resp.setMsg("重置密码失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<User> changeinformation_logic(String telephone, String email, String username) {
		// TODO Auto-generated method stub

		String sql = "update user set telephone=?,email=? where username=?";
		int re = JdbcUtil.executeUpdate(sql, telephone, email, username);
		ServerResponse<User> sr = new ServerResponse<User>();
		if (re == 1) {
			sr.setStatus(0);
			sr.setMsg("更新个人信息成功");
		} else {
			sr.setStatus(1);
			sr.setMsg("更新失败");
		}

		return sr;
	}

	@Override
	public ServerResponse<User> getinformation_logic(String username) { // 获取个人信息
		// TODO Auto-generated method stub
		String sql = "SELECT * from user where userName=?";
		List<User> list = JdbcUtil.executeQuery(sql, User.class, username);
		ServerResponse<User> sr = new ServerResponse<User>();
		if (list.size() != 0) {
			User user = list.get(0);
			sr.setStatus(0);
			sr.setData(user);
		} else {
			sr.setStatus(1);
			sr.setMsg("用户不存在");
		}

		return sr;
	}

	@Override
	public ServerResponse<User> checkname_logic(String username) { // 检查名字
		// TODO Auto-generated method stub
		String sql = "select * from user where userName=?";
		List<User> users = new ArrayList<User>();
		users = JdbcUtil.executeQuery(sql, User.class, username);
		ServerResponse<User> re = new ServerResponse<User>();
		if (users.size() == 0) {
			re.setMsg("用户名未注册");
			re.setStatus(0);

		} else {
			re.setMsg("用户已存在");
			re.setStatus(1);
		}
		return re;
	}

	@Override
	public ServerResponse<User> changequestion_logic(String question, String answer, String username) {
		// TODO Auto-generated method stub
		String sql = "update user set question=?,answer=? where username=?";
		int re = JdbcUtil.executeUpdate(sql, question, answer, username);
		ServerResponse<User> sr = new ServerResponse<User>();
		if (re == 1) {
			sr.setStatus(0);
			sr.setMsg("更改密保问题和答案成功");
		} else {
			sr.setStatus(1);
			sr.setMsg("更改密保失败");
		}

		return sr;

	}

	@Override
	public ServerResponse<Page<List<User>>> listuser_logic(String role,String userId,int pageSize,int pageNum) { // 查询列表
		// TODO Auto-generated method stub
		ServerResponse<Page<List<User>>> resp = new ServerResponse<Page<List<User>>>();
		//判断用户是不是admin
		String sql3="select * from user where userId=?";
		List<User> list2=JdbcUtil.executeQuery(sql3, User.class, userId);
		if(!list2.get(0).getRole().equals("admin"))
		{
			resp.setStatus(2);
			resp.setMsg("没有权限");
		}
		else
		{
			// 获取数据库中数据总数
			String sql = "select count(*) as count from user where role=?";
			int sum = JdbcUtil.getSum(sql,role);
			//计算分页总数
			int pages=sum%pageSize==0?sum/pageSize:(sum/pageSize)+1;
			System.out.println("pages="+pages);
			System.out.println("sum="+sum);
			//计算当前页的开始行和结束行
			int startRow=(pageNum-1)*pageSize;
			int endRow=startRow+pageSize>sum?sum:startRow+pageSize;
			//获取当前页的数据
			String sql2="select * from user where role=? limit ?,?";
			List<User> list=JdbcUtil.executeQuery(sql2, User.class,role,startRow,pageSize );
			
			//将数据赋值到page里面
			Page<List<User>> page=new Page<List<User>>();
			page.setPages(pages);
			page.setTotal(sum);
			page.setEndRow(endRow);
			page.setStartRow(startRow);
			page.setList(list);
			page.setPageNum(pageNum);
			page.setPageSize(pageSize);
			page.setFirstPage(1);
			page.setLastPage(pages);
			//判断分页的前后是否还有页
			if(pageNum==1)
			{
				page.setFirstPage(true);
				page.setPrePage(0);
				page.setLastPage(false);
				page.setNextPage(pageNum+1);
				page.setHasNextPage(true);
				page.setHasPreviousPage(false);
			}
			else if(pageNum==pages)
			{
				page.setFirstPage(false);
				page.setLastPage(true);
				page.setPrePage(pageNum-1);
				page.setNextPage(0);
				page.setHasNextPage(false);
				page.setHasPreviousPage(true);
			}
			else
			{
				page.setFirstPage(false);
				page.setLastPage(false);
				page.setPrePage(pageNum-1);
				page.setNextPage(pageNum+1);
			}
			resp.setStatus(0);
			resp.setData(page);;
		}
		
		return resp;
	}

	public static void main(String[] args) {
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<Page<List<User>>> resp=us.listuser_logic("13","1",3,1);
		System.out.println(resp.getData());
	}
}
