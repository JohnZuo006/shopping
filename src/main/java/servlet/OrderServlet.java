package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.Order;
import common.ServerResponse;
import service.impl.OrderServiceImpl;
import vo.Page;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/Order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		System.out.println(new Date()+"orderservlet+"+type);
		if(type.equals("add"))
		{
			add_order(request, response);
		}
		else if(type.equals("list"))
		{
			list_order(request, response);
		}
		else if(type.equals("adminList"))
		{
			admin_list_order(request, response);
		}
		else if(type.equals("detail"))
		{
			detail_order(request, response);
		}
		else if(type.equals("adminDetail"))
		{
			admin_detail_order(request, response);
		}
		else if(type.equals("cancel"))
		{
			cancel_order(request, response);
		}
		else if(type.equals("send"))
		{
			admin_send_order(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void add_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Order> sr= new ServerResponse<Order>();
		String userId=request.getSession().getAttribute("userid").toString();
		//String userId=request.getParameter("userId");
		//userId="2";
		if(userId==null||userId=="")
		{
			sr.setStatus(2);
			sr.setMsg("用户未登录");
		}
		else
		{
			List<String> productIds=Arrays.asList(request.getParameter("productIds").split(","));
			List<String> quantities=Arrays.asList(request.getParameter("quantities").split(","));
			String addressId=request.getParameter("addressId");
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.add_order_logic(userId, addressId, productIds, quantities);
		}
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
	private void list_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Page<List<Order>>> sr= new ServerResponse<>();
		String userId=request.getSession().getAttribute("userid").toString();
		String otype=request.getParameter("otype");
		//String userId=request.getParameter("userId");
		//userId="1";//测试用
		if(userId==null||userId=="")
		{
			sr.setStatus(2);
			sr.setMsg("用户未登录");
		}
		else
		{
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
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.list_order_logic(userId,Integer.parseInt(pageSize),Integer.parseInt(pageNum),otype);
		}
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
	private void admin_list_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Page<List<Order>>> sr= new ServerResponse<>();
		//String userId=request.getSession().getAttribute("userid").toString();
		String userId="1";//测试用
		if(userId==null||userId=="")
		{
			sr.setStatus(2);
			sr.setMsg("用户未登录");
		}
		else
		{
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
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.admin_list_order_logic(Integer.parseInt(pageSize),Integer.parseInt(pageNum));
		}
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
	private void detail_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Order> sr= new ServerResponse<Order>();
		String userId=request.getSession().getAttribute("userid").toString();
		//userId="1";
		if(userId==null||userId=="")
		{
			sr.setStatus(2);
			sr.setMsg("用户未登录");
		}
		else
		{
			//List<String> productIds=Arrays.asList(request.getParameter("productIds").split(","));
			//List<String> quantities=Arrays.asList(request.getParameter("quantities").split(","));
			String orderNo=request.getParameter("orderNo");
			//String addressId=request.getParameter("addressId");
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.detail_order_logic(userId, orderNo);
		}
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
	private void admin_detail_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Order> sr= new ServerResponse<Order>();
//		String userId=(String) request.getSession().getAttribute("userId");
//		userId="1";
//		if(userId==null||userId=="")
//		{
//			sr.setStatus(2);
//			sr.setMsg("用户未登录");
//		}
//		else
//		{
			//List<String> productIds=Arrays.asList(request.getParameter("productIds").split(","));
			//List<String> quantities=Arrays.asList(request.getParameter("quantities").split(","));
			String orderNo=request.getParameter("orderNo");
			//String addressId=request.getParameter("addressId");
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.admin_detail_order_logic( orderNo);
//		}
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
	private void cancel_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Order> sr= new ServerResponse<Order>();
		String userId=(String) request.getSession().getAttribute("userid");
		//userId="1";
		if(userId==null||userId=="")
		{
			sr.setStatus(3);
			sr.setMsg("用户未登录");
		}
		else
		{
			//List<String> productIds=Arrays.asList(request.getParameter("productIds").split(","));
			//List<String> quantities=Arrays.asList(request.getParameter("quantities").split(","));
			String orderNo=request.getParameter("orderNo");
			//String addressId=request.getParameter("addressId");
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.cancel_order_logic(userId, orderNo);
		}
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
	private void admin_send_order(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Order> sr= new ServerResponse<Order>();
			//List<String> productIds=Arrays.asList(request.getParameter("productIds").split(","));
			//List<String> quantities=Arrays.asList(request.getParameter("quantities").split(","));
			String orderNo=request.getParameter("orderNo");
			//String addressId=request.getParameter("addressId");
			OrderServiceImpl os=new OrderServiceImpl();
			sr=os.admin_send_order_logic(orderNo);
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
}
