package service.impl;

import java.util.ArrayList;
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

}
