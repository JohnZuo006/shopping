package service.impl;

import java.util.List;

import common.Address;
import common.ServerResponse;
import common.User;
import jdbcUtil.JdbcUtil;
import service.IAddressService;
import vo.Page;

public class AddressServiceImpl implements IAddressService {

	@Override
	public ServerResponse<Address> add_address_logic(Address address) {
		// TODO Auto-generated method stub
		ServerResponse<Address> resp = new ServerResponse<Address>();
		String sql = "insert into address(userId,receiverName,receiverPhone,receiverMobile,receiverProvince,receiverCity,receiverDistrict,receiverAddress,receiverZip,createTime,updateTime)values(?,?,?,?,?,?,?,?,?,now(),now())";
		int i = JdbcUtil.executeUpdate(sql, address.getUserId(), address.getReceiverName(), address.getReceiverPhone(),
				address.getReceiverMobile(), address.getReceiverProvince(), address.getReceiverCity(),
				address.getReceiverDistrict(), address.getReceiverAddress(), address.getReceiverZip());
		if (i == 1) {
			resp.setStatus(0);
			resp.setMsg("新建地址成功");
			String sql2 = "select * from address order by addressId desc limit 1";
			List<Address> addr = JdbcUtil.executeQuery(sql2, Address.class);
			if (addr.isEmpty()) {

			} else {
				Address address2 = new Address();
				address2.setAddressId(addr.get(0).getAddressId());
				address2.setUserId(addr.get(0).getUserId());
				resp.setData(address2);
			}

		} else {
			resp.setStatus(2);
			resp.setMsg("添加失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<Address> update_address_logic(Address address) {
		// TODO Auto-generated method stub
		ServerResponse<Address> resp = new ServerResponse<Address>();
		String sql = "update address set receiverName=?,receiverPhone=?,"
				+ "receiverMobile=?,receiverProvince=?,receiverCity=?,"
				+ "receiverDistrict=?,receiverAddress=?,receiverZip=? where addressId=?";
		int i = JdbcUtil.executeUpdate(sql, address.getReceiverName(), address.getReceiverPhone(),
				address.getReceiverMobile(), address.getReceiverProvince(), address.getReceiverCity(),
				address.getReceiverDistrict(), address.getReceiverAddress(), address.getReceiverZip(),
				address.getAddressId());
		if (i == 1) {
			resp.setStatus(0);
			resp.setMsg("地址更新成功");
		} else {
			resp.setStatus(1);
			resp.setMsg("地址更新失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<Address> delete_address_logic(String addressId) {
		// TODO Auto-generated method stub
		ServerResponse<Address> resp = new ServerResponse<Address>();
		String sql = "delete from address where addressId=?";
		int i = JdbcUtil.executeUpdate(sql, addressId);
		if (i == 1) {
			resp.setStatus(0);
			resp.setMsg("删除地址成功");
		} else {
			resp.setStatus(1);
			resp.setMsg("删除地址失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<Address> select_address_logic(String addressId) {
		// TODO Auto-generated method stub
		ServerResponse<Address> resp = new ServerResponse<Address>();
		String sql = "select * from address where addressId=?";
		List<Address> list = JdbcUtil.executeQuery(sql, Address.class, addressId);
		if (list.isEmpty()) {
			resp.setStatus(1);
			resp.setMsg("未找到该地址");
		} else {
			resp.setStatus(0);
			resp.setData(list.get(0));
		}
		return resp;
	}

	@Override
	public ServerResponse<Page<List<Address>>> list_address_logic(String userId, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		ServerResponse<Page<List<Address>>> resp = new ServerResponse<Page<List<Address>>>();
		// 获取数据库中数据总数
		String sql = "select count(*) as count from address where userId=?";
		int sum = JdbcUtil.getSum(sql,userId);
		// 计算分页总数
		int pages = sum % pageSize == 0 ? sum / pageSize : (sum / pageSize) + 1;
		System.out.println("pages=" + pages);
		System.out.println("sum=" + sum);
		// 计算当前页的开始行和结束行
		int startRow = (pageNum - 1) * pageSize;
		int endRow = startRow + pageSize > sum ? sum : startRow + pageSize;
		// 获取当前页的数据
		String sql2 = "select * from address where userId=? limit ?,?";
		List<Address> list = JdbcUtil.executeQuery(sql2, Address.class,userId, startRow, pageSize);

		// 将数据赋值到page里面
		Page<List<Address>> page = new Page<List<Address>>();
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
