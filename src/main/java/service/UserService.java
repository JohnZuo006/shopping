package service;

import common.ServerResponse;
import common.User;

public interface UserService {
	public ServerResponse<User> login_logic(String username, String password);
	public ServerResponse<User> register_logic(User user);
	public ServerResponse<User> loginAdmin_logic(String username,String password);
	public ServerResponse<User> changePassword_logic(String username,String oldPassword, String newPassword);
	public ServerResponse<User> findPassword_logic(String username,String password);
	public ServerResponse<User> checkAnswer_logic(String username,String question,String answer);
}
