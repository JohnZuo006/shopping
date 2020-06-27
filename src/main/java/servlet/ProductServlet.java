package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String originHeader = request.getHeader("Origin");
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
		System.out.println(new Date()+"productservlet+"+type);
		if (type != null) {
			if (type.equals("detail")) // 获取产品详细
				detail(request, response);
			else if (type.equals("add")) // 增加产品
				add(request, response);
			else if (type.equals("list")) // 获取产品列表
				list(request, response);
			else if (type.equals("update")) // 更新产品
				update(request, response);
			else if (type.equals("status")) // 更改产品状态
				status(request, response);
			else if (type.equals("search")) // 搜索
				search(request, response);
			else if (type.equals("simplesearch")) // 简单搜索
				simplesearch(request, response);
			else if(type.equals("delete"))
				delete_product(request, response);
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

	private void detail(HttpServletRequest request, HttpServletResponse response) {

		String productid = request.getParameter("productid"); // 获取商品ID值
		if(productid==null||productid=="")
		{
			productid=request.getParameter("productId");
		}
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

	private void list(HttpServletRequest request, HttpServletResponse response) {
		ServerResponse<Page<List<Product>>> sr = new ServerResponse<Page<List<Product>>>();
		String userId =(String)request.getSession().getAttribute("userid");
		System.out.println(userId);
		userId = "1";
		
		if (userId == null || userId == "") {
			sr.setStatus(1);
			sr.setMsg("用户未登录");
			//System.out.println(sr);
		} else {
			String pageSize = request.getParameter("limit");
			String pageNum = request.getParameter("page");
			if (pageNum == null || pageNum == "") {
				pageNum = "1";
			}
			if (pageSize == null || pageSize == "") {
				pageSize = "10";
			}
			ProductServiceImpl ps = new ProductServiceImpl();
			sr = ps.list_logic(Integer.parseInt(pageSize), Integer.parseInt(pageNum));
			//System.out.println(sr);
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

	public void add(HttpServletRequest request, HttpServletResponse response) { // 增加商品

		String categoryid = request.getParameter("categoryId");
		String name = request.getParameter("productName");
		String subtitle = request.getParameter("productSubtitle");
		String mainimage = request.getParameter("mainImage");
		String subimages = request.getParameter("subImages");
		String detail = request.getParameter("detail");
		String price = request.getParameter("price");
		String stock = request.getParameter("stock");
		String status = request.getParameter("productStatus");

		ProductServiceImpl us = new ProductServiceImpl();
		ServerResponse<Product> sr = us.add_logic(categoryid, name, subtitle, mainimage, subimages, detail, price,
				stock, status);
		System.out.println(sr);
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

	public void update(HttpServletRequest request, HttpServletResponse response) { // 更新产品
		Product product = new Product();
		String productid=request.getParameter("productid");
		if(productid==null||productid=="")
		{
			productid = request.getParameter("productId");
		}
		product.setProductId(Integer.parseInt(productid));
		String categoryid=request.getParameter("categoryid");
		if(categoryid==null||categoryid=="") {
			categoryid=request.getParameter("categoryId");
		}
		product.setCategoryId(Integer.parseInt(categoryid));
		String name=request.getParameter("name");
		if(name==null||name=="")
		{
			name=request.getParameter("productName");
		}
		product.setProductName(name);
		String productSubtitle=request.getParameter("subtitle");
		if(productSubtitle==null||productSubtitle=="")
		{
			productSubtitle=request.getParameter("productSubtitle");
		}
		product.setProductSubtitle(productSubtitle);
		String mainimage=request.getParameter("mainimage");
		if(mainimage==null||mainimage=="")
		{
			mainimage=request.getParameter("mainImage");
		}
		product.setMainImage(mainimage);
		String subimages=request.getParameter("subimages");
		if(subimages==null||subimages=="")
		{
			subimages=request.getParameter("mainImage");
		}
		product.setSubImages(subimages);
		product.setDetail(request.getParameter("detail"));
		BigDecimal price = new BigDecimal(request.getParameter("price"));
		price = price.setScale(2, BigDecimal.ROUND_HALF_UP); // 小数2位,四舍五入
		product.setPrice(price);
		product.setStock(Integer.parseInt(request.getParameter("stock")));
		String status=request.getParameter("status");
		if(status==null||status=="")
		{
			status=request.getParameter("productStatus");
		}
		product.setProductStatus(request.getParameter("status"));
		System.out.println(product);
		ProductServiceImpl us = new ProductServiceImpl();
		ServerResponse<Product> sr = us.update_logic(product);
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

	public void status(HttpServletRequest request, HttpServletResponse response) { // 更改产品状态 0在售 ,1下架,2删除
		String proid = request.getParameter("productid");
		if(proid==null||proid=="")
		{
			proid=request.getParameter("productId");
		}
		String status = request.getParameter("status");
		if(status==null||status=="")
		{
			status=request.getParameter("productStatus");
		}
		ProductServiceImpl us = new ProductServiceImpl();
		ServerResponse<Product> sr = us.status_logic(proid, status);
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

	public void search(HttpServletRequest request, HttpServletResponse response) { // 搜索商品
		String keyword = request.getParameter("keyword");
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		if (pageSize == null || pageSize == "") {
			pageSize = "10";
		}

		ProductServiceImpl us = new ProductServiceImpl();
		ServerResponse<Page<List<Product>>> sr = us.search_logic(keyword, Integer.parseInt(pageSize),
				Integer.parseInt(pageNum));

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

	public void simplesearch(HttpServletRequest request, HttpServletResponse response) {
		String keyword = request.getParameter("keyword");
		ProductServiceImpl us = new ProductServiceImpl();
		ServerResponse<List<Product>> sr = us.simplesearch_logic(keyword);

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
	
	public void delete_product(HttpServletRequest request, HttpServletResponse response)
	{
		
		String productid=request.getParameter("productid");
		if(productid==null||productid=="")
		{
			productid = request.getParameter("productId");
		}
		ProductServiceImpl ps = new ProductServiceImpl();
		ServerResponse<Product> sr = new ServerResponse<Product>();
		sr=ps.delete_product_logic(productid);
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
