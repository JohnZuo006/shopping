package service;

import common.Product;
import common.ServerResponse;

public interface ProductService {
  public ServerResponse<Product> detail_logic(String productId);
  
}
