package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.ServerResponse;
import common.Trolley;
import service.impl.TrolleyServiceImpl;

/**
 * Servlet implementation class TrolleyServlet
 */
@WebServlet("/Trolley")
public class TrolleyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrolleyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* 允许跨域的主机地址 */
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
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
		if(type!=null)
		{
			if(type.equals("add"))  //添加到购物车
				add(request,response);
			else if(type.equals("listTrolley"))
				listTrolley(request,response);
			else if(type.equals("updateQuantity"))
				updateQuantity(request,response);
			else if(type.equals("delete"))
				delete(request,response);
			else if(type.equals("checked"))
				checked(request,response);
			else if(type.equals("unchecked"))
				unchecked(request,response);
			else if(type.equals("searchCount"))
				searchCount(request,response);
			else if(type.equals("allChecked"))
				allChecked(request,response);
			else if(type.equals("allUnchecked"))
				allUnchecked(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    public void add(HttpServletRequest request, HttpServletResponse response) {
    	Object userId=request.getSession().getAttribute("userid");
    	ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
		if(userId==null) { //session失效
			sr.setStatus(1);
			sr.setMsg("请登录账号");
		}else {
			TrolleyServiceImpl ts = new TrolleyServiceImpl();
			
			String userid = userId.toString();
			String productid = request.getParameter("productid");
			String quantity = request.getParameter("quantity");
			
			sr = ts.add_logic(userid,productid,quantity);
			
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
    public void listTrolley(HttpServletRequest request, HttpServletResponse response) {
    	String userid = request.getSession().getAttribute("userid").toString();
    	ServerResponse<List<Trolley>> sr = new ServerResponse<List<Trolley>>();
    	if(userid!=null) {
    		TrolleyServiceImpl ts = new TrolleyServiceImpl();
    		sr = ts.listTrolley_logic(userid);
    	}else {
    		sr.setStatus(1);
    		sr.setMsg("用户未登录");
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
    public void updateQuantity(HttpServletRequest request, HttpServletResponse response) {
    	String userid = request.getSession().getAttribute("userid").toString();
    	ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
    	if(userid!=null) {
    		String productid = request.getParameter("productid");
    		String quantity = request.getParameter("quantity");
    		TrolleyServiceImpl ts = new TrolleyServiceImpl();
    		sr = ts.updateQuantity_logic(userid,productid,quantity);
    	}else {
    		sr.setStatus(1);
    		sr.setMsg("用户未登录");
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
    public void delete(HttpServletRequest request, HttpServletResponse response) {
    	String userid = request.getSession().getAttribute("userid").toString();
    	ServerResponse<Trolley> sr = new ServerResponse<Trolley>();
    	if(userid!=null) {
    		String productid = request.getParameter("productid");
    		TrolleyServiceImpl ts = new TrolleyServiceImpl();
    		sr = ts.delete_logic(userid,productid);
    	}else {
    		sr.setStatus(1);
    		sr.setMsg("用户未登录");
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
    public void checked(HttpServletRequest request, HttpServletResponse response) {
    	Object userid = request.getSession().getAttribute("userid");
    	String productid = request.getParameter("productid");
    	ServerResponse <Trolley> sr = new ServerResponse<Trolley>();
		if(userid==null) { //session失效
			sr.setStatus(1);
			sr.setMsg("请登录账号");
		}else {
			TrolleyServiceImpl ts = new TrolleyServiceImpl();
			String userId = userid.toString();
			sr = ts.checked_logic(userId,productid);
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
    public void unchecked(HttpServletRequest request, HttpServletResponse response) {
    	Object userid = request.getSession().getAttribute("userid");
    	String productid = request.getParameter("productid");
    	ServerResponse <Trolley> sr = new ServerResponse<Trolley>();
		if(userid==null) { //session失效
			sr.setStatus(1);
			sr.setMsg("请登录账号");
		}else {
			TrolleyServiceImpl ts = new TrolleyServiceImpl();
			String userId = userid.toString();
			sr = ts.unchecked_logic(userId,productid);
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
    public void searchCount(HttpServletRequest request, HttpServletResponse response) {
    	String userid = request.getSession().getAttribute("userid").toString();
    	ServerResponse<Integer> sr = new ServerResponse<Integer>();
    	if(userid!=null) {
    		TrolleyServiceImpl ts = new TrolleyServiceImpl();
    		sr = ts.searchCount_logic(userid);
    	}else {
    		sr.setStatus(1);
    		sr.setMsg("用户未登录");
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
    public void allChecked(HttpServletRequest request, HttpServletResponse response) {
    	Object userid = request.getSession().getAttribute("userid");
    	ServerResponse <List<Trolley>> sr = new ServerResponse<List<Trolley>>();
		if(userid==null) { //session失效
			sr.setStatus(1);
			sr.setMsg("请登录账号");
		}else {
			TrolleyServiceImpl ts = new TrolleyServiceImpl();
			String userId = userid.toString();
			sr = ts.allChecked_logic(userId);
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
    public void allUnchecked(HttpServletRequest request, HttpServletResponse response) {
    	Object userid = request.getSession().getAttribute("userid");
    	ServerResponse <List<Trolley>> sr = new ServerResponse<List<Trolley>>();
		if(userid==null) { //session失效
			sr.setStatus(1);
			sr.setMsg("请登录账号");
		}else {
			TrolleyServiceImpl ts = new TrolleyServiceImpl();
			String userId = userid.toString();
			sr = ts.allUnchecked_logic(userId);
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

}
