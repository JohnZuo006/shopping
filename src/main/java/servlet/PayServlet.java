package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.ServerResponse;
import service.impl.PayServiceImpl;
import vo.Main;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/Pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayServlet() {
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
		System.out.println(originHeader);
		/* 允许跨域的主机地址 */
		response.setHeader("access-control-allow-origin", originHeader);
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
		if(type!=null||type!="")
		{
			if(type.equals("pay"))
			{
				pay_pay(request, response);
			}
			else if(type.equals("callback"))
			{
				call_back(request, response);
			}
			else if(type.equals("status"))
			{
				pay_status(request, response);
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
	private void pay_pay(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<String> sr=new ServerResponse<>();
		String orderNo=request.getParameter("orderNo");
		PayServiceImpl ps=new PayServiceImpl();
		//Main ps=new Main();
		sr=ps.pay_logic(orderNo);
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
	private void call_back(HttpServletRequest request, HttpServletResponse response)
	{
		String tradeNo=request.getParameter("trade_no");
		String trade_status=request.getParameter("trade_status");
		String out_trade_no=request.getParameter("out_trade_no");
		if(trade_status.equals("TRADE_SUCCESS"))
		{
			PayServiceImpl ps=new PayServiceImpl();
			ps.call_back(out_trade_no, tradeNo);
		}
	}
	private void pay_status(HttpServletRequest request, HttpServletResponse response)
	{
		ServerResponse<Boolean> sr=new ServerResponse<Boolean>();
		String orderNo=request.getParameter("orderNo");
		
		PayServiceImpl ps=new PayServiceImpl();
		sr=ps.pay_status_logic(orderNo);
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
