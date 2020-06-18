package service.impl;

import java.util.List;

import common.Address;
import common.ServerResponse;
import jdbcUtil.JdbcUtil;
import service.IAddressService;

public class AddressServiceImpl implements IAddressService {

	@Override
	public ServerResponse<Address> add_address_logic(Address address) {
		// TODO Auto-generated method stub
		ServerResponse<Address> resp=new ServerResponse<Address>();
		String sql="insert into address(userId,receiverName,receiverPhone,receiverMobile,receiverProvince,receiverCity,receiverDistrict,receiverAddress,receiverZip,createTime,updateTime)values(?,?,?,?,?,?,?,?,?,now(),now())";
		int i=JdbcUtil.executeUpdate(sql, address.getUserId(),address.getReceiverName(),address.getReceiverPhone(),
				address.getReceiverMobile(),address.getReceiverProvince(),address.getReceiverCity(),address.getReceiverDistrict(),address.getReceiverAddress(),address.getReceiverZip());
		if(i==1)
		{
			resp.setStatus(0);
			resp.setMsg("新建地址成功");
			String sql2="select * from t order by id desc limit 1";
			List<Address> addr=JdbcUtil.executeQuery(sql2, Address.class);
			Address address2=new Address();
			address2.setAddressId(addr.get(0).getAddressId());
			resp.setData(address2);
		}
		else
		{
			resp.setStatus(2);
			resp.setMsg("添加失败");
		}
		return resp;
	}

	@Override
	public ServerResponse<Address> update_address_logic(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<Address> delete_address_logic(String addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<Address> select_address_logic(String addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerResponse<List<Address>> list_address_logic(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
