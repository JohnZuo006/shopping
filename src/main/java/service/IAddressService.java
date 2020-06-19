package service;

import java.util.List;

import common.Address;
import common.ServerResponse;
import vo.Page;

public interface IAddressService {
	public ServerResponse<Address> add_address_logic(Address address);
	public ServerResponse<Address> update_address_logic(Address address);
	public ServerResponse<Address> delete_address_logic(String addressId);
	public ServerResponse<Address> select_address_logic(String addressId);
	public ServerResponse<Page<List<Address>>> list_address_logic(String userId,int pageSize,int pageNum);
}
