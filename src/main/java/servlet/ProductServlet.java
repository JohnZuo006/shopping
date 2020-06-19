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

import common.Product;
import common.ServerResponse;
import service.impl.ProductServiceImpl;
import vo.Page;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
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
		String type=request.getParameter("type");
		if(type!=null&&type!="")
		{
			if(type.equals("detail"))
			{
				detail(request, response);
			}
			else if(type.equals("list"))
			{
				list(request, response);
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
	
	private void detail(HttpServletRequest request, HttpServletResponse response) {
		
		String productid = request.getParameter("productid"); //获取商品ID值
		ProductServiceImpl us = new ProductServiceImpl();
		ServerResponse<Product> sr = us.detail_logic(productid);

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
	private void list(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Page<List<Product>>> sr = new ServerResponse<Page<List<Product>>>();
		String userId=(String) request.getSession().getAttribute("userId");
		userId="1";
		if(userId==null||userId=="")
		{
			sr.setStatus(1);
			sr.setMsg("用户未登录");
		}
		else
		{
			String pageSize=request.getParameter("pageSize");
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null||pageNum=="")
			{
				pageNum="1";
			}
			if(pageSize==null||pageSize=="")
			{
				pageSize="10";
			}
			ProductServiceImpl ps=new ProductServiceImpl();
			sr=ps.list_logic(Integer.parseInt(pageSize), Integer.parseInt(pageNum));
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
