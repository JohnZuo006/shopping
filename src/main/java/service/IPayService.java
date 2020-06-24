package service;

import common.ServerResponse;

public interface IPayService {
	public ServerResponse<Object> pay_logic(String orderNo);
}
