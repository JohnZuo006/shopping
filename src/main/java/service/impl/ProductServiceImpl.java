package service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.Category;
import common.Product;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.ProductService;
import vo.Page;

public class ProductServiceImpl implements ProductService {

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
	public ServerResponse<Product> add_logic(String cateid,String name,String subtitle,String mainimage,String subimages,String detail,String price,String stock,String status){
		 ServerResponse<Product> sr = new ServerResponse<Product>();
		String sql="insert into product(categoryId,productName,ProductSubtitle,mainImage,subImages,detail,price,stock,productStatus,createTime) values(?,?,?,?,?,?,?,?,?,?)";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ts=df.format(new Date());
		Object[] params= { cateid,name, subtitle,mainimage,subimages,detail, price,stock,status,ts};
		int i=JdbcUtil.executeUpdate(sql, params);
		if(i==1)
		{
			sr.setStatus(0);
			sr.setMsg("添加产品成功");
		}else {
			sr.setStatus(1);
			sr.setMsg("添加产品失败");
		}
		return sr;
	}
   
	@Override
	public ServerResponse<Page<List<Product>>> list_logic(int pageSize, int pageNum) {  //商品列表
		// TODO Auto-generated method stub
		ServerResponse<Page<List<Product>>> resp = new ServerResponse<Page<List<Product>>>();
		// 获取数据库中数据总数
		String sql = "select count(*) as count from product";
		int sum = JdbcUtil.getSum(sql);
		// 计算分页总数
		int pages = sum % pageSize == 0 ? sum / pageSize : (sum / pageSize) + 1;
		System.out.println("pages=" + pages);
		System.out.println("sum=" + sum);
		// 计算当前页的开始行和结束行
		int startRow = (pageNum - 1) * pageSize;
		int endRow = startRow + pageSize > sum ? sum : startRow + pageSize;
		// 获取当前页的数据
		String sql2 = "select * from product limit ?,?";
		List<Product> list = JdbcUtil.executeQuery(sql2, Product.class, startRow, pageSize);

		// 将数据赋值到page里面
		Page<List<Product>> page = new Page<List<Product>>();
		page.setPages(pages);
		page.setTotal(sum);
		page.setEndRow(endRow);
		page.setStartRow(startRow);
		page.setList(list);
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setFirstPage(1);
		page.setLastPage(pages);
		// 判断分页的前后是否还有页
		if (pageNum == 1) {
			page.setFirstPage(true);
			page.setPrePage(0);
			page.setLastPage(false);
			page.setNextPage(pageNum + 1);
			page.setHasNextPage(true);
			page.setHasPreviousPage(false);
		} else if (pageNum == pages) {
			page.setFirstPage(false);
			page.setLastPage(true);
			page.setPrePage(pageNum - 1);
			page.setNextPage(0);
			page.setHasNextPage(false);
			page.setHasPreviousPage(true);
		} else {
			page.setFirstPage(false);
			page.setLastPage(false);
			page.setPrePage(pageNum - 1);
			page.setNextPage(pageNum + 1);
		}
		resp.setStatus(0);
		resp.setData(page);
		//System.out.println(resp);
		return resp;
	}
	@Override
	public ServerResponse<Product> update_logic(Product product) {
		// TODO Auto-generated method stub
		 ServerResponse<Product> sr = new ServerResponse<Product>();
		String sql = "update product set categoryId=?,productName=?,productSubtitle=?,"
				+ "mainImage=?,subImages=?,price=?,stock=?,productStatus=? where productId=?";
		int i=JdbcUtil.executeUpdate(sql, product.getCategoryId(),product.getProductName(),
				product.getProductSubtitle(),product.getMainImage(),product.getSubImages(),
				product.getPrice(),product.getStock(),product.getProductStatus(),product.getProductId());
		if(i==1)
		{
			sr.setStatus(0);
			sr.setMsg("更新产品成功");
		}else {
			sr.setStatus(1);
			sr.setMsg("更新产品失败");
		}
		return sr;
	}
	@Override
	public ServerResponse<Product> status_logic(String productid, String status) {
		// TODO Auto-generated method stub
		
		String sql="update product set productStatus=? where productId=?";
		int i=JdbcUtil.executeUpdate(sql,status,productid);
		ServerResponse<Product> sr = new ServerResponse<Product>();
		if(i==1)
		{
			sr.setStatus(0);
			sr.setMsg("修改产品状态成功,当前状态为:"+status);
		}else {
			sr.setStatus(1);
			sr.setMsg("修改产品状态失败");
		}
		
		return sr;
	}
	@Override
	public ServerResponse<Page<List<Product>>> search_logic(String keyword,int pageSize,int pageNum) {
		// TODO Auto-generated method stub
		ServerResponse<Page<List<Product>>> sr = new ServerResponse<Page<List<Product>>>();
		if(keyword == "") {
			sr.setStatus(1);
			sr.setMsg("关键词为空");
		}else {
			//String[] keys=keyword.split(" ");  多关键词,以空格间隔
			// 获取数据库中数据总数
			String key = "%" + keyword + "%";
			String sql = "select count(*) as count from product where productName LIKE ?";
			int sum = JdbcUtil.getSum(sql,key);
			if(sum!=0) {
				// 计算分页总数
				int pages = sum % pageSize == 0 ? sum / pageSize : (sum / pageSize) + 1;
				System.out.println("pages=" + pages);
				System.out.println("sum=" + sum);
				// 计算当前页的开始行和结束行
				int startRow = (pageNum - 1) * pageSize;
				int endRow = startRow + pageSize > sum ? sum : startRow + pageSize;
				// 获取当前页的数据
				
				String sql2 = "select * from product where productName like ? limit ?,?";
				List<Product> list = JdbcUtil.executeQuery(sql2, Product.class,key, startRow, pageSize);

				// 将数据赋值到page里面
				Page<List<Product>> page = new Page<List<Product>>();
				page.setPages(pages);
				page.setTotal(sum);
				page.setEndRow(endRow);
				page.setStartRow(startRow);
				page.setList(list);
				page.setPageNum(pageNum);
				page.setPageSize(pageSize);
				page.setFirstPage(1);
				page.setLastPage(pages);
				// 判断分页的前后是否还有页
				if (pageNum == 1) {
					page.setFirstPage(true);
					page.setPrePage(0);
					page.setLastPage(false);
					page.setNextPage(pageNum + 1);
					page.setHasNextPage(true);
					page.setHasPreviousPage(false);
				} else if (pageNum == pages) {
					page.setFirstPage(false);
					page.setLastPage(true);
					page.setPrePage(pageNum - 1);
					page.setNextPage(0);
					page.setHasNextPage(false);
					page.setHasPreviousPage(true);
				} else {
					page.setFirstPage(false);
					page.setLastPage(false);
					page.setPrePage(pageNum - 1);
					page.setNextPage(pageNum + 1);
				}
				sr.setStatus(0);
				sr.setData(page);
			}else {
				sr.setStatus(1);
				sr.setMsg("未查询到相关产品");
			}
		}

		return sr;
	}
    public ServerResponse<List<Product>> simplesearch_logic(String keyword) {
    	ServerResponse<List<Product>> sr = new ServerResponse<List<Product>>();
    	String key = "%" + keyword + "%";
    	String sql = "select * from product where productName like ? ";
		List<Product> list = JdbcUtil.executeQuery(sql, Product.class,key);
		if(list.size()!=0) {
			sr.setStatus(0);
			sr.setData(list);
		}else {
			sr.setStatus(1);
			sr.setMsg("未查询到相关产品");
		}

    	return sr;
    }
	@Override
	public ServerResponse<Product> delete_product_logic(String productId) {
		// TODO Auto-generated method stub
		ServerResponse<Product> resp=new ServerResponse<Product>();
		String sql="delete from product where productId=?";
		int i=JdbcUtil.executeUpdate(sql, productId);
		if(i==1)
		{
			resp.setStatus(0);
			resp.setMsg("删除成功");
		}
		else
		{
			resp.setStatus(2);
			resp.setMsg("删除失败");
		}
		return resp;
	}
	public String listToString(List list, char separator) {    
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {        
			if (i == list.size() - 1) {            
		sb.append(list.get(i));        
		} else {            
		sb.append(list.get(i));            
		sb.append(separator);        
		}	
		}    
	return sb.toString();}
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
	@Override
	public ServerResponse<Page<List<Product>>> searchCategory_logic(String categoryid, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		ServerResponse<Page<List<Product>>> sr = new ServerResponse<Page<List<Product>>>();
		if(categoryid == "") {
			sr.setStatus(1);
			sr.setMsg("关键词为空");
		}else {
			List<String> catess=getSonId(Integer.parseInt(categoryid));
			catess.add(categoryid);
			String cates=listToString(catess, ',');
			System.out.println(cates);
			//String[] keys=keyword.split(" ");  多关键词,以空格间隔
			// 获取数据库中数据总数
			String sql = "select count(*) as count from product where categoryId in("+cates+")";
			int sum = JdbcUtil.getSum(sql);
			if(sum!=0) {
				// 计算分页总数
				int pages = sum % pageSize == 0 ? sum / pageSize : (sum / pageSize) + 1;
				System.out.println("pages=" + pages);
				System.out.println("sum=" + sum);
				// 计算当前页的开始行和结束行
				int startRow = (pageNum - 1) * pageSize;
				int endRow = startRow + pageSize > sum ? sum : startRow + pageSize;
				// 获取当前页的数据
				
				String sql2 = "select * from product where categoryId in("+cates+") limit ?,?";
				List<Product> list = JdbcUtil.executeQuery(sql2, Product.class,startRow, pageSize);

				// 将数据赋值到page里面
				Page<List<Product>> page = new Page<List<Product>>();
				page.setPages(pages);
				page.setTotal(sum);
				page.setEndRow(endRow);
				page.setStartRow(startRow);
				page.setList(list);
				page.setPageNum(pageNum);
				page.setPageSize(pageSize);
				page.setFirstPage(1);
				page.setLastPage(pages);
				// 判断分页的前后是否还有页
				if (pageNum == 1) {
					page.setFirstPage(true);
					page.setPrePage(0);
					page.setLastPage(false);
					page.setNextPage(pageNum + 1);
					page.setHasNextPage(true);
					page.setHasPreviousPage(false);
				} else if (pageNum == pages) {
					page.setFirstPage(false);
					page.setLastPage(true);
					page.setPrePage(pageNum - 1);
					page.setNextPage(0);
					page.setHasNextPage(false);
					page.setHasPreviousPage(true);
				} else {
					page.setFirstPage(false);
					page.setLastPage(false);
					page.setPrePage(pageNum - 1);
					page.setNextPage(pageNum + 1);
				}
				sr.setStatus(0);
				sr.setData(page);
			}else {
				sr.setStatus(1);
				sr.setMsg("未查询到相关产品");
			}
		}

		return sr;
	}
	@Override
	public ServerResponse<Product> update_detail_logic(String productId, String detail) {
		// TODO Auto-generated method stub
		ServerResponse<Product> resp=new ServerResponse<Product>();
		String sql="update product set detail=? where productId=?";
		int re=JdbcUtil.executeUpdate(sql, detail,productId);
		if(re==1)
		{
			resp.setStatus(0);
			resp.setMsg("更新成功");
		}
		else
		{
			resp.setStatus(1);
			resp.setMsg("更新失败");
		}
		return resp;
	}
}
