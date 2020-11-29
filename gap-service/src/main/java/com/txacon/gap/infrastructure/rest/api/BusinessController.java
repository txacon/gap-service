package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.BusinessService;
import com.txacon.gap.infrastructure.rest.dto.business.BusinessDTO;
import com.txacon.gap.infrastructure.rest.mapper.business.BusinessRestMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
@Api(tags = "Businesses")
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService service;
    private final BusinessRestMapper mapper;

    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @GetMapping(value = "/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessDTO> getBusiness(@PathVariable
                                                   @NotNull
                                                   @Min(0) @Max(Long.MAX_VALUE) Long businessId) {
        return ResponseEntity.ok(mapper.toDTO(service.findById(businessId)));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessDTO> createBusiness(Principal principal, @RequestBody
    @NotNull BusinessDTO businessDTO) {
        Long customerId = Long.parseLong(principal.getName());
        return ResponseEntity.accepted().body(mapper.toDTO(service.add(mapper.toDomain(businessDTO), customerId)));
    }

    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @PutMapping(value = "/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessDTO> updateBusiness(Principal principal, @RequestBody
    @NotNull BusinessDTO businessDTO) {
        Long customerId = Long.parseLong(principal.getName());
        return ResponseEntity.accepted().body(mapper.toDTO(service.update(mapper.toDomain(businessDTO), customerId)));
    }

    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @DeleteMapping(value = "/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBusiness(@PathVariable
                                               @NotNull
                                               @Min(0) @Max(Long.MAX_VALUE) Long businessId) {
        service.deleteById(businessId);
        return ResponseEntity.ok().build();
    }

}
