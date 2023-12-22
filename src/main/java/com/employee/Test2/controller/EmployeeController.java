package com.employee.Test2.controller;

import com.employee.Test2.request.EmployeeRequest;
import com.employee.Test2.response.EmployeeResponse;
import com.employee.Test2.service.EmployeeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Divakar Verma
 * @created_at : 17/12/2023 - 11:08 am
 * @mail_to: vermadivakar2022@gmail.com
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/home")
    public String home(){
        return "Employee home";
    }


    @GetMapping("/getEmployee")
    public ResponseEntity<EmployeeResponse> getEmployee(@RequestParam Integer empId, @RequestParam Integer addressId){
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(empId,addressId);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);
        return new ResponseEntity<>(employeeResponse,HttpStatus.CREATED);
    }

    @PutMapping("updateEmployee/{empId}/{addressId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest employeeRequest,@PathVariable Integer empId, @PathVariable Integer addressId){
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(empId,employeeRequest,addressId);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{empId}/{addressId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId, @PathVariable Integer addressId){
        String message = employeeService.deleteEmployee(empId,addressId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
