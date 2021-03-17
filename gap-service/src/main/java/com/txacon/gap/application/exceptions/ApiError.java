package com.txacon.gap.application.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApiError {
  ERROR_CUSTOMER_NOT_FOUND_BY_ID("Customer not found by id"),
  ERROR_CUSTOMER_NOT_FOUND_BY_EMAIL("Customer not found by email"),
  ERROR_CUSTOMER_NOT_FOUND_BY_EMAIL_AND_PASSWORD_HASH(
      "Customer not found by email and password hash"),
  ERROR_CUSTOMER_INVALID_TO_CREATE("Customer need email and password to be created"),
  ERROR_ENTITY_NOT_FOUND_BY_NAME("Entity not found by name"),
  ERROR_BUSINESS_NOT_FOUND_BY_ID("Business not found by Id"),
  ERROR_BUSINESS_INVALID_TO_CREATE("Business fields (fiscalId, Address and email) ara mandatory"),
  ERROR_BUSINESS_INVALID_TO_UPDATE("Business need id to update"),
  PRODUCT_INVALID_TO_UPDATE("Invalid producto to update, need to have valid id"),
  ERROR_PRODUCT_NOT_FOUND("Product not found by Id"),
  ERROR_ON_REPORT_RENDER("Error on report render");

  @Getter
  private final String messageError;
}
