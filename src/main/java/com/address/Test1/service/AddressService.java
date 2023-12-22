package com.address.Test1.service;

import com.address.Test1.entity.Address;

import java.util.Optional;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:40 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
public interface AddressService {

    public Optional<Address> getAddressById(int id);
    public Address createAddress(Address address);
    public Address updateAddress(int id,Address address);

    public String deleteAddress(int id);

}
