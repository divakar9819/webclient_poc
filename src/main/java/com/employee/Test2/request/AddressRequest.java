package com.employee.Test2.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import reactor.core.publisher.Mono;

/**
 * @author Divakar Verma
 * @created_at : 17/12/2023 - 7:09 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@NoArgsConstructor
@Data
public class AddressRequest{
    private String city;
    private String state;

}
