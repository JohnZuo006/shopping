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

import com.google.gson.Gson;

import common.Category;
import common.ServerResponse;
import service.impl.CategoryServiceImpl;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String originHeader=request.getHeader("Origin");
		/* 允许跨域的主机地址 */
		System.out.println(originHeader);
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
		String type=request.getParameter("type");
		System.out.println(new Date()+"categoryservlet+"+type);
		if(type!=null)
		{
			if(type.equals("getCategory"))
			{
				get_category(request, response);
			}
			else if(type.equals("addCategory"))
			{
				add_category(request, response);
			}
			else if(type.equals("changeCategory"))
			{
				change_category_name(request, response);
			}
			else if(type.equals("getDeepCategory"))
			{
				get_deep_category(request, response);
			}
			else if(type.equals("getAll"))
			{
				get_all_category(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void get_category(HttpServletRequest request, HttpServletResponse response)
	{
		//获取类别（平级）
		String categoryId =request.getParameter("categoryId");
		ServerResponse<List<Category>> sr=new ServerResponse<>();
		CategoryServiceImpl category=new CategoryServiceImpl();
		sr=category.get_category_logic(categoryId);
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
	private void add_category(HttpServletRequest request, HttpServletResponse response)
	{
		//添加类别
		String parentId=request.getParameter("parentId");
		if(parentId==null)
		{
			parentId="0";
		}
		String categoryName=request.getParameter("categoryName");
		ServerResponse<Category> sr=new ServerResponse<Category>();
		CategoryServiceImpl category=new CategoryServiceImpl();
		sr=category.add_category_logic(parentId, categoryName);
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
	private void change_category_name(HttpServletRequest request, HttpServletResponse response)
	{
		//更改类别名字
		String categoryId=request.getParameter("categoryId");
		String categoryName=request.getParameter("categoryName");
		ServerResponse<Category> sr=new ServerResponse<Category>();
		CategoryServiceImpl category=new CategoryServiceImpl();
		sr=category.change_category_logic(categoryId, categoryName);
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
	private void get_deep_category(HttpServletRequest request, HttpServletResponse response)
	{
		//获取当前类别以及子类别
		String categoryId=request.getParameter("categoryId");
		ServerResponse<List<String>> sr=new ServerResponse<>();
		CategoryServiceImpl category=new CategoryServiceImpl();
		sr=category.get_deep_category_logic(categoryId);
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
	private void get_all_category(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<List<Category>> sr=new ServerResponse<>();
		CategoryServiceImpl category=new CategoryServiceImpl();
		sr=category.get_all_category_logic();
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
