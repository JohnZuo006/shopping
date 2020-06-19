package service.impl;

import java.util.List;

import common.Product;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.ProductService;

public class ProductServiceImpl implements ProductService{

	public ServerResponse<Product> detail_logic(String productId) {
		// TODO Auto-generated method stub
		String sql="SELECT * from product where productId=?";
		List<Product> list=JdbcUtil.executeQuery(sql,Product.class,productId);
		 ServerResponse<Product> sr = new ServerResponse<Product>();
		if(list.size()!=0) {
			Product product = list.get(0);
			 sr.setStatus(0);
			 sr.setData(product);
			 sr.setMsg("已找到该商品");
		}else {
			sr.setStatus(1);
			sr.setMsg("该商品不存在");
		}
		return sr;
	}

}
