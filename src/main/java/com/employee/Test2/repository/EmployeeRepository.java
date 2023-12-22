package com.employee.Test2.repository;

import com.employee.Test2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:57 pm
 * @mail_to: vermadivakar2022@gmail.com
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
