package service;

import common.ServerResponse;

public interface IPayService {
	public ServerResponse<String> pay_logic(String orderNo);
	public void call_back(String OrderNo,String tradeNo);
	public ServerResponse<Boolean> pay_status_logic(String orderNo);
}
