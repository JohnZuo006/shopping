package service.impl;

import java.util.ArrayList;
import java.util.List;

import common.Category;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {

	
	//获取类别（平级）
	@Override
	public ServerResponse<List<Category>> get_category_logic(String categoryId) {
		// TODO Auto-generated method stub
		ServerResponse<List<Category>> resp=new ServerResponse<>();
		String sql1="select * from category where parentId=?";
		List<Category> list=new ArrayList<Category>();
		list=JdbcUtil.executeQuery(sql1, Category.class, categoryId);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("未获取到该品类");
		}
		else
		{
			resp.setData(list);
			resp.setStatus(0);
		}
		return resp;
	}
	//添加类别

	@Override
	public ServerResponse<Category> add_category_logic(String parentId, String categoryName) {
		// TODO Auto-generated method stub
		ServerResponse<Category> resp=new ServerResponse<Category>();
		String sql="insert into category(categoryName,parentId,categoryStatus,createTime,updateTime) values(?,?,?,now(),now())";
		String sql2="select * from category where parentId=?&&categoryName=?";
		List<Category> list=JdbcUtil.executeQuery(sql2, Category.class, parentId,categoryName);
		if(list.isEmpty())
		{
			int i=JdbcUtil.executeUpdate(sql, categoryName,parentId,"正常");
			if(i==1)
			{
				resp.setStatus(0);
				resp.setMsg("添加品类成功");
			}
		}
		else
		{
			resp.setStatus(1);
			resp.setMsg("添加品类失败");
		}
		return resp;
	}

	//修改类别名
	@Override
	public ServerResponse<Category> change_category_logic(String categoryId, String categoryName) {
		// TODO Auto-generated method stub
		ServerResponse<Category> resp=new ServerResponse<Category>();
		String sql="update category set categoryName=? where categoryId=?";
		int i=JdbcUtil.executeUpdate(sql, categoryName,categoryId);
		if(i==1)
		{
			resp.setStatus(0);
			resp.setMsg("更新品类名字成功");
		}
		else
		{
			resp.setStatus(1);
			resp.setMsg("更新品类名字失败");
		}
		return resp;
	}	
	//查找递归子类别
	@Override
	public ServerResponse<List<String>> get_deep_category_logic(String categoryId) {
		ServerResponse<List<String>> resp=new ServerResponse<>();
		String sql1="select * from category where categoryId=?";
		List<Category> list=new ArrayList<Category>();
		list=JdbcUtil.executeQuery(sql1, Category.class, categoryId);
		if(list.isEmpty())
		{
			resp.setStatus(1);
			resp.setMsg("未获取到该品类");
		}
		else
		{
			List<String> lists=new ArrayList<String>();
			lists.add(categoryId);
			lists.addAll(getSonId(Integer.parseInt(categoryId)));
			resp.setStatus(0);
			resp.setData(lists);
		}
		
		return resp;
	}
	private List<String> getSonId(int parentId)
	{
		List<String> re= new ArrayList<String>();
		List<Category> list=new ArrayList<Category>();
		list.clear();
		String sql2="select * from category where parentId=?";
		list=JdbcUtil.executeQuery(sql2, Category.class, parentId);
		for(Category category:list)
		{
			re.add(Integer.toString(category.getCategoryId()));
			re.addAll(getSonId(category.getCategoryId()));
		}
		return re;
	}
}
