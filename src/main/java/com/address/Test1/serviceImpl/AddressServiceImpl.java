package com.address.Test1.serviceImpl;

import com.address.Test1.entity.Address;
import com.address.Test1.repository.AddressRespository;
import com.address.Test1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:41 pm
 * @mail_to: vermadivakar2022@gmail.com
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRespository addressRespository;
    @Override
    public Optional<Address> getAddressById(int id) {
        Optional<Address> address = addressRespository.findById(id);
        return address;
    }

    @Override
    public Address createAddress(Address address) {
        Address createdAddress = addressRespository.save(address);
        return createdAddress;
    }

    @Override
    public Address updateAddress(int id, Address newAddress) {
        Address address = addressRespository.findById(id).orElseThrow(()-> new RuntimeException("Address not found"));
        address.setCity(newAddress.getCity());
        address.setState(newAddress.getState());
        Address updatedAddress = addressRespository.save(address);
        return updatedAddress;
    }

    @Override
    public String deleteAddress(int id) {
        Address address = addressRespository.findById(id).orElseThrow(()-> new RuntimeException("Address not found"));
        addressRespository.delete(address);
        return "Address deleted successfully";
    }
}
