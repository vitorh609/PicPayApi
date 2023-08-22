package com.servicepay.servicePay.domain.user.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class UserInput {
    private String firstName;

    private String lastName;

    private String document;

    private BigDecimal balance;

    private String email;

    private UserType userType;

    private String password;
}
