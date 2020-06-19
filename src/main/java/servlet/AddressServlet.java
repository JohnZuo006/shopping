package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.Address;
import common.ServerResponse;
import service.impl.AddressServiceImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		if(type!=null)
		{
			if(type.equals("add"))
			{
				add(request, response);
			}
			else if(type.equals("delete"))
			{
				delete(request, response);
			}
			else if(type.equals("list"))
			{
				list(request, response);
			}
			else if(type.equals("select"))
			{
				select(request, response);
			}
			else if(type.equals("update"))
			{
				update(request, response);
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
	private void add(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Address> sr = new ServerResponse<>();
		String receiverName=request.getParameter("receiverName");
		String receiverPhone=request.getParameter("receiverPhone");
		String receiverMobile=request.getParameter("receiverMobile");
		String receiverProvince=request.getParameter("receiverProvince");
		String receiverCity=request.getParameter("receiverCity");
		String receiverDistrict=request.getParameter("receiverDistrict");
		String receiverAddress=request.getParameter("receiverAddress");
		String receiverZip=request.getParameter("receiverZip");
		String userId=(String) request.getSession().getAttribute("UserId");
		if(userId==null||userId=="")
		{
			sr.setStatus(0);
			sr.setMsg("新建地址失败");
		}
		else
		{
			Address address=new Address();
			address.setReceiverAddress(receiverAddress);
			address.setReceiverCity(receiverCity);
			address.setReceiverDistrict(receiverDistrict);
			address.setReceiverMobile(receiverMobile);
			address.setReceiverName(receiverName);
			address.setReceiverPhone(receiverPhone);
			address.setReceiverProvince(receiverProvince);
			address.setReceiverZip(receiverZip);
			address.setUserId(Integer.parseInt(userId));
			AddressServiceImpl as=new AddressServiceImpl();
			sr=as.add_address_logic(address);
			

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
	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Address> sr = new ServerResponse<>();
		String receiverName=request.getParameter("receiverName");
		String receiverPhone=request.getParameter("receiverPhone");
		String receiverMobile=request.getParameter("receiverMobile");
		String receiverProvince=request.getParameter("receiverProvince");
		String receiverCity=request.getParameter("receiverCity");
		String receiverDistrict=request.getParameter("receiverDistrict");
		String receiverAddress=request.getParameter("receiverAddress");
		String receiverZip=request.getParameter("receiverZip");
		String userId=(String) request.getSession().getAttribute("UserId");
		String addressId=request.getParameter("addressId");
		if(userId==null||userId=="")
		{
			sr.setStatus(0);
			sr.setMsg("更新地址失败");
		}
		else
		{
			Address address=new Address();
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
			AddressServiceImpl as=new AddressServiceImpl();
			sr=as.add_address_logic(address);
			

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
	private void update(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	private void select(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	private void list(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
}
