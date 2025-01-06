package com.fdmgroup.BankingApplication.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

	private AddressRepository addressRepo;

	@Autowired
	public AddressService(AddressRepository addressRepo) {
		super();
		this.addressRepo = addressRepo;
	}

	public Address createAddress(Address address) {
		return addressRepo.save(address);
	}

	public List<Address> getAddresses() {
		return addressRepo.findAll();
	}

	public Address getAddressById(Integer addressId) {
		return addressRepo.findById(addressId).get();
	}

	public Address updateAddress(Address request, Integer id) {
		Address addressReceived = addressRepo.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address not found"));
		addressReceived.setProvince(request.getProvince());
		addressReceived.setPostalCode(request.getPostalCode());
		return addressReceived;
	}

	public void deleteAddress(Integer addressId) {
		addressRepo.deleteById(addressId);
	}
}