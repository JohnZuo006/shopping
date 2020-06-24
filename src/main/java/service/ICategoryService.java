package service;

import java.util.List;

import common.Category;
import common.ServerResponse;

public interface ICategoryService {
//获取类别（平级）
	public ServerResponse<List<Category>> get_category_logic(String categoryId);
//添加类别
	public ServerResponse<Category> add_category_logic(String parentId,String categoryName);
//修改类别名
	public ServerResponse<Category> change_category_logic(String categoryId,String categoryName);
//查找递归子类别
	public ServerResponse<List<String>> get_deep_category_logic(String categoryId);
//获取所有分类
	public ServerResponse<List<Category>> get_all_category_logic();
//删除分类
	public ServerResponse<Category> delete_category_logic(String categoryId);
}
