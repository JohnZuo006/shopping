package service;

import java.util.List;

import common.ServerResponse;
import common.User;
import vo.Page;

public interface UserService {
	public ServerResponse<User> login_logic(String username, String password);
	public ServerResponse<User> register_logic(User user);
	public ServerResponse<User> loginAdmin_logic(String username,String password);
	public ServerResponse<User> changePassword_logic(String username,String oldPassword, String newPassword);
	public ServerResponse<User> findPassword_logic(String username,String password);
	public ServerResponse<User> checkAnswer_logic(String username,String question,String answer);
	public ServerResponse<User> changeinformation_logic(String telephone,String email,String username);
	public ServerResponse<User> getinformation_logic(String username);
	public ServerResponse<User> changequestion_logic(String question,String answer,String username);
	public ServerResponse<User> checkname_logic(String username);
	public ServerResponse<Page<List<User>>> listuser_logic(String role,String UserId,int pageSize,int pageNum);
}
