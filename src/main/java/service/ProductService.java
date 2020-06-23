package service;

import java.util.List;

import common.Product;
import common.ServerResponse;
import vo.Page;

public interface ProductService {
	public ServerResponse<Product> detail_logic(String productId);

	public ServerResponse<Product> add_logic(String cateid, String name, String subtitle, String mainimage,
			String subimages, String detail, String price, String stock, String status);

	public ServerResponse<Page<List<Product>>> list_logic(int pageSize, int pageNum);

	public ServerResponse<Product> update_logic(Product product);

	public ServerResponse<Product> status_logic(String productid, String status);

	public ServerResponse<Page<List<Product>>> search_logic(String keyword, int pageSize, int pageNum);

	public ServerResponse<List<Product>> simplesearch_logic(String keyword);
	
	public ServerResponse<Product> delete_product_logic(String productId);
}
