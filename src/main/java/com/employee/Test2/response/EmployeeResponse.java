package com.employee.Test2.response;

import lombok.Data;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 6:10 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Data
public class EmployeeResponse {

    private int id;
    private String name;
    private int age;
    AddressResponse addressResponse;
}
