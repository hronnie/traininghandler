package com.codeproj.traininghandler.manager.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codeproj.traininghandler.dao.AddressDAO;
import com.codeproj.traininghandler.model.Address;

@Component
public class AddressManager {
	
	@Autowired
	AddressDAO addressDAO;

	public Long create(String city, String country, String houseNo,
			String postCode, String street, String fullAddress) {
		Address addressModel = new Address(city, country, houseNo, postCode, street, fullAddress);
		return addressDAO.create(addressModel);
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

}
