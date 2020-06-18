package service.impl;

import java.util.List;

import common.Address;
import common.ServerResponse;
import service.IAddressService;

public class AddressService implements IAddressService {

	@Override
	public ServerResponse<Address> add_address_logic(Address address) {
		// TODO Auto-generated method stub
		ServerResponse<Address> resp=new ServerResponse<Address>();
		
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
