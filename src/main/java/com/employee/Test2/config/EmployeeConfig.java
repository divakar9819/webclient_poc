package com.employee.Test2.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Divakar Verma
 * @created_at : 15/12/2023 - 6:33 pm
 * @mail_to: vermadivakar2022@gmail.com
 */
@Configuration
public class EmployeeConfig {

    @Value("${addressservice.base.url}")
    private String addressBaseUrl ;
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public WebClient getWebClient(){
        System.out.println(addressBaseUrl);
        return WebClient.builder().baseUrl(addressBaseUrl).build();
    }
}
