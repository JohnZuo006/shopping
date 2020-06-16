package service;

import common.ServerResponse;
import common.User;

public interface UserService {
	public ServerResponse<User> login_logic(String username, String password);
	public ServerResponse<User> register_logic(User user);
	
}
