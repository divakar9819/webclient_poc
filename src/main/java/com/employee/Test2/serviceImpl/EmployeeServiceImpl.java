package com.employee.Test2.serviceImpl;

import com.employee.Test2.entity.Employee;
import com.employee.Test2.repository.EmployeeRepository;
import com.employee.Test2.request.AddressRequest;
import com.employee.Test2.request.EmployeeRequest;
import com.employee.Test2.response.AddressResponse;
import com.employee.Test2.response.EmployeeResponse;
import com.employee.Test2.service.EmployeeService;
import org.hibernate.annotations.Bag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 5:59 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeResponse getEmployeeById(int id, int addressId) {
        Optional<Employee> employee = employeeRepository.findById(id);
        AddressResponse addressResponse = webClient.get().uri("/byId/"+String.valueOf(addressId)).retrieve().bodyToMono(AddressResponse.class).block();
        System.out.println(addressResponse);
        EmployeeResponse employeeResponse = modelMapper.map(employee,EmployeeResponse.class);
        employeeResponse.setAddressResponse(addressResponse);

        return employeeResponse;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
       Employee employee = new Employee();
       employee.setName(employeeRequest.getName());
       employee.setAge(employeeRequest.getAge());
       Employee createdEmployee = employeeRepository.save(employee);
       EmployeeResponse employeeResponse = modelMapper.map(createdEmployee,EmployeeResponse.class);
       AddressRequest addressRequest = employeeRequest.getAddressRequest();
       AddressRequest addressRequest1 = webClient.post().uri("/createAddress").body(Mono.just(addressRequest), AddressRequest.class)
               .retrieve().onStatus(HttpStatusCode::is4xxClientError,response ->{
                   return Mono.error(new WebClientResponseException
                           (response.statusCode().value(), "Bad Request", null, null, null));
               })
               .onStatus(HttpStatusCode::is5xxServerError, response -> {
                   //logError("Server error occurred");
                   return Mono.error(new WebClientResponseException
                           (response.statusCode().value(),"Server error", null, null, null));
               })
               .bodyToMono(AddressRequest.class).block();
        AddressResponse addressResponse1 = modelMapper.map(addressRequest1,AddressResponse.class);
        System.out.println(addressResponse1);
        employeeResponse.setAddressResponse(addressResponse1);
        System.out.println(employeeResponse);
       return employeeResponse;
    }

    @Override
    public EmployeeResponse updateEmployee(int empId, EmployeeRequest employeeRequest,int addressId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RuntimeException("Employee not found"));
        employee.setAge(employeeRequest.getAge());
        employee.setName(employeeRequest.getName());
        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeResponse employeeResponse = modelMapper.map(updatedEmployee,EmployeeResponse.class);
        AddressRequest addressRequest = employeeRequest.getAddressRequest();
        AddressResponse updatedAddress = webClient.put().uri("/updateAddress/"+addressId)
                .body(Mono.just(addressRequest),AddressRequest.class).retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    return Mono.error(new WebClientResponseException(clientResponse.statusCode().value(),"Bad request",null,null,null));
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    return Mono.error(new WebClientResponseException(clientResponse.statusCode().value(),"Server error",null,null,null));
                })
                .bodyToMono(AddressResponse.class).block();

        //AddressResponse addressResponse = modelMapper.map(updatedAddress,AddressResponse.class);
        employeeResponse.setAddressResponse(updatedAddress);

//        Mono<ResponseEntity<AddressRequest>> updatedAddress = webClient.put().uri("/updateAddress/"+addressId)
//                .body(Mono.just(addressRequest),AddressRequest.class).retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
//                    return Mono.error(new WebClientResponseException(clientResponse.statusCode().value(),"Bad request",null,null,null));
//                })
//                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
//                    return Mono.error(new WebClientResponseException(clientResponse.statusCode().value(),"Server error",null,null,null));
//                })
//                .toEntity(AddressRequest.class);
//
//        updatedAddress.subscribe(res->{
//            System.out.println(res.getBody());
//            AddressResponse addressResponse = modelMapper.map(res.getBody(),AddressResponse.class);
//            if(addressResponse!=null){
//                employeeResponse.setAddressResponse(addressResponse);
//            }
 //       });
        return employeeResponse;
    }

    @Override
    public String deleteEmployee(int empId, int addressId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RuntimeException("Employee not found"));
        String message = webClient.delete().uri("/deleteAddress/"+addressId).retrieve().
                onStatus(HttpStatusCode::is4xxClientError , clientResponse -> {
                    return Mono.error(new WebClientResponseException(clientResponse.statusCode().value(),"Bad Request",null,null,null));
                }).onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    return Mono.error(new WebClientResponseException(clientResponse.statusCode().value(),"Bad Request",null,null,null));
                }).bodyToMono(String.class).block();

//        employeeRepository.delete(employee);
//        String newMessage;
//        message.subscribe();
        return "Employee and "+ message;
    }
}
