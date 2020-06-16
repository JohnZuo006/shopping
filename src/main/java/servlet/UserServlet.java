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
		String type=request.getParameter("type");
		if(type.equals("login"))
		{
			login(request,response);
		}
		else if(type.equals("register"))
		{
			register(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	//ǰ̨
    public void login(HttpServletRequest request, HttpServletResponse response) {
    	//登录
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserServiceImpl us=new UserServiceImpl();
        
        ServerResponse<User> sr=us.login_logic(username, password);
        Gson gson=new Gson();
        String json=gson.toJson(sr);
        
        try {
			PrintWriter pw=response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void register(HttpServletRequest request, HttpServletResponse response) {
    	//注册
    	String username=request.getParameter("username");
    	String password=request.getParameter("password");
    	String telephone=request.getParameter("telephone");
    	String email=request.getParameter("email");
    	String role=request.getParameter("role");
    	String question=request.getParameter("question");
    	String answer=request.getParameter("answer");
    	User user=new User(0, username, password, telephone, email, role, question, answer, null, null);
    	UserServiceImpl us=new UserServiceImpl();
    	ServerResponse<User> sr=us.register_logic(user);
        Gson gson=new Gson();
        String json=gson.toJson(sr);
        try {
			PrintWriter pw=response.getWriter();
			pw.print(json);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
