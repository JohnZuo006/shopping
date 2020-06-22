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

import common.Address;
import common.ServerResponse;
import common.User;
import service.impl.AddressServiceImpl;
import service.impl.UserServiceImpl;
import vo.Page;

/**
 * Servlet implementation class AddressServlet
 */
@WebServlet("/Address")
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddressServlet() {
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
		System.out.println("addressservlet+"+type);
		if (type != null) {
			if (type.equals("add")) {
				add(request, response);
			} else if (type.equals("delete")) {
				delete(request, response);
			} else if (type.equals("list")) {
				list(request, response);
			} else if (type.equals("select")) {
				select(request, response);
			} else if (type.equals("update")) {
				update(request, response);
			}
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

	private void add(HttpServletRequest request, HttpServletResponse response) {
		ServerResponse<Address> sr = new ServerResponse<>();
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverMobile = request.getParameter("receiverMobile");
		String receiverProvince = request.getParameter("receiverProvince");
		String receiverCity = request.getParameter("receiverCity");
		String receiverDistrict = request.getParameter("receiverDistrict");
		String receiverAddress = request.getParameter("receiverAddress");
		String receiverZip = request.getParameter("receiverZip");
		String userId = (String) request.getSession().getAttribute("UserId");
		//userId = "1";// 测试用
		if (userId == null || userId == "") {
			sr.setStatus(1);
			sr.setMsg("新建地址失败");
		} else {
			Address address = new Address();
			address.setReceiverAddress(receiverAddress);
			address.setReceiverCity(receiverCity);
			address.setReceiverDistrict(receiverDistrict);
			address.setReceiverMobile(receiverMobile);
			address.setReceiverName(receiverName);
			address.setReceiverPhone(receiverPhone);
			address.setReceiverProvince(receiverProvince);
			address.setReceiverZip(receiverZip);
			address.setUserId(Integer.parseInt(userId));
			AddressServiceImpl as = new AddressServiceImpl();
			sr = as.add_address_logic(address);

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

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		ServerResponse<Address> sr = new ServerResponse<>();
		String addressId = request.getParameter("addressId");

		AddressServiceImpl as = new AddressServiceImpl();
		sr = as.delete_address_logic(addressId);

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

	private void update(HttpServletRequest request, HttpServletResponse response) {
		ServerResponse<Address> sr = new ServerResponse<>();
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String receiverMobile = request.getParameter("receiverMobile");
		String receiverProvince = request.getParameter("receiverProvince");
		String receiverCity = request.getParameter("receiverCity");
		String receiverDistrict = request.getParameter("receiverDistrict");
		String receiverAddress = request.getParameter("receiverAddress");
		String receiverZip = request.getParameter("receiverZip");
		String userId = (String) request.getSession().getAttribute("UserId");
		String addressId=request.getParameter("addressId");
		userId = "1";// 测试用
		if (userId == null || userId == "") {
			sr.setStatus(0);
			sr.setMsg("用户未登录");
		} else {
			Address address = new Address();
			address.setReceiverAddress(receiverAddress);
			address.setReceiverCity(receiverCity);
			address.setReceiverDistrict(receiverDistrict);
			address.setReceiverMobile(receiverMobile);
			address.setReceiverName(receiverName);
			address.setReceiverPhone(receiverPhone);
			address.setReceiverProvince(receiverProvince);
			address.setReceiverZip(receiverZip);
			address.setUserId(Integer.parseInt(userId));
			address.setAddressId(Integer.parseInt(addressId));
			AddressServiceImpl as = new AddressServiceImpl();
			sr = as.update_address_logic(address);

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

	private void select(HttpServletRequest request, HttpServletResponse response) {
		ServerResponse<Address> sr = new ServerResponse<>();
		String addressId = request.getParameter("addressId");
		String userId=(String) request.getSession().getAttribute("userId");
		userId="1";
		if(userId==null||userId=="")
		{
			sr.setStatus(2);
			sr.setMsg("用户未登录");
		}
		else
		{
			AddressServiceImpl as = new AddressServiceImpl();
			sr = as.select_address_logic(addressId);
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

	private void list(HttpServletRequest request, HttpServletResponse response) {
		ServerResponse<Page<List<Address>>> sr = new ServerResponse<Page<List<Address>>>();
		String userId=(String) request.getSession().getAttribute("userId");
		userId="1";//测试用
		if(userId==null||userId=="")
		{
			sr.setStatus(1);
			sr.setMsg("用户未登录");
		}
		else
		{
			String pageSize=request.getParameter("pageSize");
			String pageNum=request.getParameter("pageNum");
			if(pageSize==null||pageSize=="")
			{
				pageSize="10";
			}
			if(pageNum==null||pageNum=="")
			{
				pageNum="1";
			}
			AddressServiceImpl as=new AddressServiceImpl();
			sr=as.list_address_logic(userId,Integer.parseInt(pageSize),Integer.parseInt(pageNum));
			
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
