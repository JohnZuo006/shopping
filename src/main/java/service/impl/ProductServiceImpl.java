package service.impl;

import java.util.List;

import common.Address;
import common.Product;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.ProductService;
import vo.Page;

public class ProductServiceImpl implements ProductService {

	public ServerResponse<Product> detail_logic(String productId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * from product where productId=?";
		List<Product> list = JdbcUtil.executeQuery(sql, Product.class, productId);
		ServerResponse<Product> sr = new ServerResponse<Product>();
		if (list.size() != 0) {
			Product product = list.get(0);
			sr.setStatus(0);
			sr.setData(product);
			sr.setMsg("已找到该商品");
		} else {
			sr.setStatus(1);
			sr.setMsg("该商品不存在");
		}
		return sr;
	}

	@Override
	public ServerResponse<Page<List<Product>>> list_logic(int pageSize, int pageNum) {
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
		return resp;
	}

}
