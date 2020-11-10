package com.txacon.gap.application.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiError {
    ERROR_CUSTOMER_NOT_FOUND_BY_ID("Customer not found by id"),
    ERROR_CUSTOMER_NOT_FOUND_BY_EMAIL("Customer not found by email"),
    ERROR_CUSTOMER_NOT_FOUND_BY_EMAIL_AND_PASSWORD_HASH("Customer not found by email and password hash"),
    ERROR_CUSTOMER_INVALID_TO_CREATE("Customer need email and password to be created");


    @Getter
    private final String messageError;
}
