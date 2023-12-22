package com.address.Test1.controller;

import com.address.Test1.entity.Address;
import com.address.Test1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:43 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("byId/{id}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Integer id){
        Optional<Address> address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping("/createAddress")
    public ResponseEntity<Address> createAddress(@RequestBody Address address){
        Address createdAddress = addressService.createAddress(address);
        return new ResponseEntity<>(createdAddress,HttpStatus.CREATED);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer id, @RequestBody Address address){
        Address updatedAddress = addressService.updateAddress(id,address);
        return new ResponseEntity<>(updatedAddress,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id){
        String message = addressService.deleteAddress(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
