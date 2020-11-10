package com.txacon.gap.infrastructure.rest.api;


import com.txacon.gap.application.api.CustomerService;
import com.txacon.gap.infrastructure.rest.dto.CustomerDTO;
import com.txacon.gap.infrastructure.rest.mapper.CustomerRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    private final CustomerRestMapper mapper;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable
                                                   @NotNull
                                                   @Min(0) @Max(Long.MAX_VALUE) Long customerId) {
        return ResponseEntity.ok(mapper.toDTO(service.getById(customerId)));
    }

    @PostMapping("")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody
                                                      @NotNull CustomerDTO customerDTO) {
        return ResponseEntity.accepted().body(mapper.toDTO(service.addCustomer(mapper.toDomain(customerDTO))));
    }

    @PutMapping("")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody
                                                      @NotNull CustomerDTO customerDTO) {
        return ResponseEntity.accepted().body(mapper.toDTO(service.update(mapper.toDomain(customerDTO))));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable
                                               @NotNull
                                               @Min(0) @Max(Long.MAX_VALUE) Long customerId) {
        service.deleteById(customerId);
        return ResponseEntity.ok().build();
    }

}
