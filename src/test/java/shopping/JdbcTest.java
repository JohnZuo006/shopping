package shopping;

import java.util.ArrayList;
import java.util.List;

import jdbcUtil.JdbcUtil;
import po.User;

public class JdbcTest {
	public static void main(String[]args)
	{
		String sql="insert into user (userName,passWord,telephone,email,role,question,answer,createTime) values(?,?,?,?,?,?,?,?)";
		Object []params= {"john","123456","13333333333","1@1.com","admin","question","answer","2020-06-16 12:12"};
		int i=JdbcUtil.executeUpdate(sql, params);
		System.out.println(i);
		
		String sql2="select * from user";
		List<User> users=new ArrayList<>();
		users=JdbcUtil.executeQuery(sql2, User.class);
		System.out.println(users.get(0));
	}
}
