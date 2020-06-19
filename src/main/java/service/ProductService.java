package service;

import java.util.List;

import common.Product;
import common.ServerResponse;
import vo.Page;

public interface ProductService {
  public ServerResponse<Product> detail_logic(String productId);
  public ServerResponse<Page<List<Product>>> list_logic(int pageSize,int pageNum);
}
