package com.codeproj.traininghandler.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeproj.traininghandler.model.Address;

@Service
@Transactional
public interface AddressDAO {
	public Long create(Address address);

	public boolean edit(Address address);

	public Address getAddressByAddressId(Long addressId);
}
