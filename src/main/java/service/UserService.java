package service;

import common.ServerResponse;
import common.User;

public interface UserService {


	public ServerResponse<User> login_logic(String username, String password);
}
