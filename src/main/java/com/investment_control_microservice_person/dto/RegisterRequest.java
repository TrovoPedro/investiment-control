package com.investment_control_microservice_person.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private int age;
    private String email;
    private String password;
}

