package com.fdmgroup.BankingApplication.address;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

	AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@GetMapping
	public ResponseEntity<List<Address>> getAddresses() {
		List<Address> addresses = addressService.getAddresses();
		return ResponseEntity.ok(addresses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
		Address address = addressService.getAddressById(id);
		return ResponseEntity.ok(address);
	}

	@GetMapping("/lookup/{postalCode}")
	public ResponseEntity<PostalLookupDto> getLookupByPostal(@PathVariable String postalCode) {
		PostalLookupDto result = addressService.postalLookup(postalCode);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody Address request) {
		Address createdAddress = addressService.createAddress(request);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdAddress.getAddressId()).toUri();
		return ResponseEntity.created(location).body(createdAddress);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address request) {
		Address updateAddress = addressService.updateAddress(request, id);
		return ResponseEntity.ok(updateAddress);
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable Integer id) {
		addressService.deleteAddress(id);
	}
}