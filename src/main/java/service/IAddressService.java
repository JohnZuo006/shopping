package service;

import java.util.List;

import common.Address;
import common.ServerResponse;

public interface IAddressService {
	public ServerResponse<Address> add_address_logic(Address address);
	public ServerResponse<Address> update_address_logic(Address address);
	public ServerResponse<Address> delete_address_logic(String addressId);
	public ServerResponse<Address> select_address_logic(String addressId);
	public ServerResponse<List<Address>> list_address_logic(String userId);
}
