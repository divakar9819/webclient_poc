package com.address.Test1.repository;

import com.address.Test1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:40 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Repository
public interface AddressRespository extends JpaRepository<Address,Integer> {
}
