package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ServerResponse;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//前台
    public ServerResponse login(HttpServletRequest request, HttpServletResponse response) {
    	//用户登录
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
    
    	return null;
    }
    public ServerResponse register(HttpServletRequest request, HttpServletResponse response) {
    	//用户注册
    	
    	return null;
    }
    public ServerResponse changePassword(HttpServletRequest request, HttpServletResponse response) {
     //修改密码
    }
    public ServerResponse findPassword(HttpServletRequest request, HttpServletResponse response) {
    	//找回密码
    }
    public ServerResponse changeInformation(HttpServletRequest request, HttpServletResponse response) {
       //修改个人信息
        }
    public ServerResponse getInformation(HttpServletRequest request, HttpServletResponse response) {
    	  //查看个人信息
    }
    public ServerResponse checkName(HttpServletRequest request, HttpServletResponse response) {
    	//检查用户名是否有效
    }
    public ServerResponse loginOut(HttpServletRequest request, HttpServletResponse response) {
    	//退出登录
    }
    //后台
    public ServerResponse loginAdmin(HttpServletRequest request, HttpServletResponse response) {
      //管理员登录
    }
    public ServerResponse listUser(HttpServletRequest request, HttpServletResponse response) {
    	//获取用户列表
    }
}
