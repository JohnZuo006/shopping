package service;

import java.util.List;

import common.ServerResponse;
import common.Trolley;

public interface TrolleyService {
	
	public ServerResponse<List<Trolley>> listTrolley_logic(String userid); //购物车列表
    public ServerResponse<Trolley> add_logic(String userid,String productid,String quantity);//添加商品
    public ServerResponse <Trolley> updateQuantity_logic(String userid,String productid,String quantity); //更新某个商品数量
    public ServerResponse <Trolley> delete_logic(String userid,String productid); //移除商品
    public ServerResponse <Trolley> checked_logic(String userid,String productid); //选中某个商品
    public ServerResponse <Trolley> unchecked_logic(String userid,String productid); //取消选中
    public ServerResponse <Integer> searchCount_logic(String userid); //查询产品数量
    public ServerResponse <List<Trolley>> allChecked_logic(String userid);   //全选
    public ServerResponse <List<Trolley>> allUnchecked_logic(String userid);  //全不选
}
