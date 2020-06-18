package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)
	{
		
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
