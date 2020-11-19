package com.txacon.gap.infrastructure.rest.api;


import com.txacon.gap.application.api.CustomerService;
import com.txacon.gap.infrastructure.rest.dto.CustomerDTO;
import com.txacon.gap.infrastructure.rest.mapper.CustomerRestMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;


@RestController
@Api(tags = "Customers")
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    private final CustomerRestMapper mapper;

    @PreAuthorize("hasRole({'ROLE_USER','ROLE_ADMIN'})")
    @GetMapping("/me")
    public ResponseEntity<CustomerDTO> getMe(Principal principal) {
        Long customerId = Long.parseLong(principal.getName());
        return ResponseEntity.ok(getCustomerDTOById(customerId));
    }

    @PreAuthorize("hasRole({'ROLE_USER','ROLE_ADMIN'})")
    @PutMapping("/me")
    public ResponseEntity<CustomerDTO> updateMe(Principal principal, @RequestBody CustomerDTO customerDTO) {
        Long customerId = Long.parseLong(principal.getName());
        customerDTO.setId(customerId);
        return ResponseEntity.ok(updateCustomerDTO(customerDTO));
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable
                                                   @NotNull
                                                   @Min(0) @Max(Long.MAX_VALUE) Long customerId) {
        return ResponseEntity.ok(getCustomerDTOById(customerId));
    }

    @PostMapping("")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody
                                                      @NotNull CustomerDTO customerDTO) {
        return ResponseEntity.accepted().body(mapper.toDTO(service.addCustomer(mapper.toDomain(customerDTO))));
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable
                                                      @NotNull
                                                      @Min(0) @Max(Long.MAX_VALUE) Long customerId,
                                                      @RequestBody
                                                      @NotNull CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return ResponseEntity.accepted().body(updateCustomerDTO(customerDTO));
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable
                                               @NotNull
                                               @Min(0) @Max(Long.MAX_VALUE) Long customerId) {
        service.deleteById(customerId);
        return ResponseEntity.ok().build();
    }

    private CustomerDTO getCustomerDTOById(@PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long customerId) {
        return mapper.toDTO(service.getById(customerId));
    }

    private CustomerDTO updateCustomerDTO(@RequestBody @NotNull CustomerDTO customerDTO) {
        return mapper.toDTO(service.update(mapper.toDomain(customerDTO)));
    }

}
