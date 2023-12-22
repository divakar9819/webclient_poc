package com.employee.Test2.request;

import com.employee.Test2.response.AddressResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Divakar Verma
 * @created_at : 17/12/2023 - 7:09 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@NoArgsConstructor
@Data
public class EmployeeRequest {

    private String name;
    private int age;
    AddressRequest addressRequest;
}
