package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ServerResponse;
import common.User;
import service.impl.UserServiceImpl;

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
	//ǰ̨
    public ServerResponse login(HttpServletRequest request, HttpServletResponse response) {
    	//登录
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserServiceImpl us=new UserServiceImpl();
        
        ServerResponse<User> sr=us.login_logic(username, password);
        
    
    	return null;
    }
    public ServerResponse register(HttpServletRequest request, HttpServletResponse response) {
    	//�û�ע��
    	
    	return null;
    }
    public ServerResponse changePassword(HttpServletRequest request, HttpServletResponse response) {
     //�޸�����
    }
    public ServerResponse findPassword(HttpServletRequest request, HttpServletResponse response) {
    	//�һ�����
    }
    public ServerResponse changeInformation(HttpServletRequest request, HttpServletResponse response) {
       //�޸ĸ�����Ϣ
        }
    public ServerResponse getInformation(HttpServletRequest request, HttpServletResponse response) {
    	  //�鿴������Ϣ
    }
    public ServerResponse checkName(HttpServletRequest request, HttpServletResponse response) {
    	//����û����Ƿ���Ч
    }
    public ServerResponse loginOut(HttpServletRequest request, HttpServletResponse response) {
    	//�˳���¼
    }
    //��̨
    public ServerResponse loginAdmin(HttpServletRequest request, HttpServletResponse response) {
      //����Ա��¼
    }
    public ServerResponse listUser(HttpServletRequest request, HttpServletResponse response) {
    	//��ȡ�û��б�
    }
}
