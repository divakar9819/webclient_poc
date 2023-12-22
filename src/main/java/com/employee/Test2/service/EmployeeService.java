package com.employee.Test2.service;

import com.employee.Test2.entity.Employee;
import com.employee.Test2.request.EmployeeRequest;
import com.employee.Test2.response.EmployeeResponse;

import java.util.Optional;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:58 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
public interface EmployeeService {

    public EmployeeResponse getEmployeeById(int id, int addressId);
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    public EmployeeResponse updateEmployee(int empId, EmployeeRequest employeeRequest,int addressId);

    public String deleteEmployee(int empId,int addressId);
}
