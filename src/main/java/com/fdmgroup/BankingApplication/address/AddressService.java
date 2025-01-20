package com.fdmgroup.BankingApplication.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

	private AddressRepository addressRepo;
	private RestTemplate restTemplate;

	@Autowired
	public AddressService(AddressRepository addressRepo, RestTemplate restTemplate) {
		super();
		this.addressRepo = addressRepo;
		this.restTemplate = restTemplate;
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

	public PostalLookupDto postalLookup(String postalCode) {
		String url = "http://geocoder.ca/?locate=" + postalCode + "&json=1";
		ResponseEntity<PostalLookupDto> response = restTemplate.exchange(url, HttpMethod.GET, null,
				PostalLookupDto.class);
		return response.getBody();
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