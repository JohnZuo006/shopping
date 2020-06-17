package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String r=request.getParameter("s");
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
	}
	private void add_category(HttpServletRequest request, HttpServletResponse response)
	{
		//添加类别
	}
	private void change_category_name(HttpServletRequest request, HttpServletResponse response)
	{
		//更改类别名字
	}
	private void get_deep_category(HttpServletRequest request, HttpServletResponse response)
	{
		//获取当前类别以及子类别
	}
}
