package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import common.ServerResponse;
import common.User;
import service.impl.UserServiceImpl;
import vo.Page;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String originHeader=request.getHeader("Origin");
		/* 允许跨域的主机地址 */
		response.setHeader("Access-Control-Allow-Origin", originHeader);
		/* 允许跨域的请求方法GET, POST, HEAD 等 */
		response.setHeader("Access-Control-Allow-Methods", "*");
		/* 重新预检验跨域的缓存时间 (s) */
		response.setHeader("Access-Control-Max-Age", "3600");
		/* 允许跨域的请求头 */
		response.setHeader("Access-Control-Allow-Headers", "*");
		/* 是否携带cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		System.out.println(new Date()+"userservlet+"+type);
		if(type!=null)
		{
			if (type.equals("login"))
				login(request, response);
			else if (type.equals("register"))
				register(request, response);
			else if (type.equals("changePassword"))
				changePassword(request, response);
			else if (type.equals("findPassword"))
				findPassword(request, response);
			else if (type.equals("changeInformation"))
				changeInformation(request, response);
			else if (type.equals("getInformation"))
				getInformation(request, response);
			else if (type.equals("checkName"))
				checkName(request, response);
			else if (type.equals("loginOut"))
				loginOut(request, response);
			else if (type.equals("loginAdmin"))
				loginAdmin(request, response);
			else if (type.equals("listUser"))
				listUser(request, response);
			else if(type.equals("checkAnswer"))
				checkAnswer(request, response);
			else if(type.equals("findQuestion"))
				findQuestion(request, response);
			else if(type.equals("changeQuestion"))
				changeQuestion(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

public void login(HttpServletRequest request, HttpServletResponse response) {  // 登录
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.login_logic(username, password);
		Gson gson = new Gson();
		String json = gson.toJson(sr);
		if (sr.getStatus() == 0) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", sr.getData().getUserId());
			session.setAttribute("username", sr.getData().getUserName());
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {// 注册
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		User user = new User(0, username, password, telephone, email, role, question, answer, null, null);
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.register_logic(user);
		Gson gson = new Gson();
		String json = gson.toJson(sr);
		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changePassword(HttpServletRequest request, HttpServletResponse response) {// 修改密码
		
		String newPassword = request.getParameter("passwordNew");
		String oldPassword = request.getParameter("passwordOld");
		String username = request.getSession().getAttribute("username").toString();
		UserServiceImpl us = new UserServiceImpl();

		ServerResponse<User> sr = us.changePassword_logic(username, oldPassword, newPassword);
		Gson gson = new Gson();
		String json = gson.toJson(sr);

		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkAnswer(HttpServletRequest request, HttpServletResponse response) { // 验证密保问题
		
		String username = request.getSession().getAttribute("username").toString();
		//String username = request.getParameter("username");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		UserServiceImpl us = new UserServiceImpl();

		ServerResponse<User> sr = us.checkAnswer_logic(username, question, answer);
		Gson gson = new Gson();
		String json = gson.toJson(sr);

		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findPassword(HttpServletRequest request, HttpServletResponse response) {	// 找回密码
	
	
		String username = request.getParameter("username");
		String password = request.getParameter("newPassword");
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.findPassword_logic(username, password);
		Gson gson = new Gson();
		String json = gson.toJson(sr);

		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeInformation(HttpServletRequest request, HttpServletResponse response) {// 更改个人信息
		
		String username= request.getSession().getAttribute("username").toString();
		//String username=request.getParameter("username");
		//user.setPassWord(request.getParameter("password"));
		String telephone=request.getParameter("telephone");
		String email=request.getParameter("email");
		//user.setRole(request.getParameter("role"));
		//String question=request.getParameter("question");
		//String answer=request.getParameter("answer");
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.changeinformation_logic(telephone,email,username);

		Gson gson = new Gson();
		String json = gson.toJson(sr);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();

	}

	public void getInformation(HttpServletRequest request, HttpServletResponse response) {// 查看个人信息
		
		//String username = request.getParameter("username");
		String username=request.getSession().getAttribute("username").toString();
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.getinformation_logic(username);
		System.out.println("get:"+username);
		Gson gson = new Gson();
		String json = gson.toJson(sr);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();

	}

	public void checkName(HttpServletRequest request, HttpServletResponse response) {	// 检查名字是否存在
	
		String username = request.getParameter("username");
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.checkname_logic(username);

		Gson gson = new Gson();
		String json = gson.toJson(sr);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();

	}

	public void loginOut(HttpServletRequest request, HttpServletResponse response) {// 退出登录
		
		String username =request.getSession().getAttribute("username").toString();
		request.getSession().removeAttribute(username);
		request.getSession().invalidate();
		ServerResponse<User> sr =new ServerResponse<User>();
		Object user=request.getSession().getAttribute("username");
		if(user==null) { //session失效
			sr.setStatus(0);
			sr.setMsg("请登录账号");
		}else {
			sr.setStatus(1);
			sr.setMsg("用户"+username+"在线");
		}
		Gson gson = new Gson();
		String json = gson.toJson(sr);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();
	}

	public void findQuestion(HttpServletRequest request, HttpServletResponse response) { //通过用户名找到密保问题
		String username = request.getParameter("username");
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.getinformation_logic(username);

		Gson gson = new Gson();
		String json = gson.toJson(sr);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();
		
	}
	
	public void changeQuestion(HttpServletRequest request, HttpServletResponse response) { //更改密保问题
		
		String username=request.getSession().getAttribute("username").toString();
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		UserServiceImpl us = new UserServiceImpl();
		ServerResponse<User> sr = us.changequestion_logic(question,answer,username);

		Gson gson = new Gson();
		String json = gson.toJson(sr);

		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();
	}
	// 后台
	public void loginAdmin(HttpServletRequest request, HttpServletResponse response) {// 管理员登录
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserServiceImpl us = new UserServiceImpl();
		HttpSession session=request.getSession();
		ServerResponse<User> sr = us.loginAdmin_logic(username, password);
		Gson gson = new Gson();
		String json = gson.toJson(sr);
		session.setAttribute("userid", Integer.toString(sr.getData().getUserId()));
		session.setAttribute("username", sr.getData().getUserName());
		System.out.println(session.toString());
		try {
			PrintWriter pw = response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void listUser(HttpServletRequest request, HttpServletResponse response) {
		// 用户列表
		ServerResponse<Page<List<User>>> sr = new ServerResponse<Page<List<User>>>();
		//String userId=(String) request.getSession().getAttribute("userid");
		String userId="1";//测试用
		if(userId==null||userId=="")
		{
			sr.setStatus(1);
			sr.setMsg("用户未登录");
		}
		else
		{
			String role ="user";// request.getParameter("role");
			String pageSize=request.getParameter("limit");
			String pageNum=request.getParameter("page");
			if(pageSize==null||pageSize=="")
			{
				pageSize="10";
			}
			if(pageNum==null||pageNum=="")
			{
				pageNum="1";
			}
			UserServiceImpl us = new UserServiceImpl();
			sr=us.listuser_logic(role,userId,Integer.parseInt(pageSize),Integer.parseInt(pageNum));
			
		}
		Gson gson = new Gson();
		String json = gson.toJson(sr);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(json);
		pw.close();
		System.out.println("传出完成");
	}
}
