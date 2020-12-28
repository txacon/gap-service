package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.BusinessService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.application.exceptions.ApiError;
import com.txacon.gap.application.exceptions.BusinessNotFoundException;
import com.txacon.gap.infrastructure.rest.dto.business.BusinessDTO;
import com.txacon.gap.infrastructure.rest.dto.product.ProductDTO;
import com.txacon.gap.infrastructure.rest.mapper.business.BusinessRestMapper;
import com.txacon.gap.infrastructure.rest.mapper.product.ProductRestMapper;

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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Businesses")
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService service;
    private final BusinessRestMapper mapper;
    private final ProductRestMapper productRestMapper;

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BusinessDTO>> getBusiness(Principal principal) {
        Long customerId = Long.parseLong(principal.getName());
        return ResponseEntity
                .ok(service.findByOwnerId(customerId).stream().map(mapper::toDTO).collect(Collectors.toList()));
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @GetMapping(value = "/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessDTO> getBusiness(
            @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId) {
        return ResponseEntity.ok(mapper.toDTO(service.findById(businessId)));
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessDTO> createBusiness(Principal principal,
            @RequestBody @NotNull BusinessDTO businessDTO) {
        Long customerId = Long.parseLong(principal.getName());
        return ResponseEntity.accepted().body(mapper.toDTO(service.add(mapper.toDomain(businessDTO), customerId)));
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessDTO> updateBusiness(Principal principal,
            @RequestBody @NotNull BusinessDTO businessDTO) {
        Long customerId = Long.parseLong(principal.getName());
        return ResponseEntity.accepted().body(mapper.toDTO(service.update(mapper.toDomain(businessDTO), customerId)));
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @DeleteMapping(value = "/{businessId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBusiness(@PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId) {
        service.deleteById(businessId);
        return ResponseEntity.ok().build();
    }

    // PRODUCTS

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @GetMapping(value = "/{businessId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getBussinessProducts(
            @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId) {
        return ResponseEntity.ok(mapper.toDTO(service.findById(businessId)).getProducts());
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @GetMapping(value = "/{businessId}/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getBusinessProductById(
            @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId,
            @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long productId) {
                Optional<ProductDTO> productSearch = mapper.toDTO(service.findById(businessId)).getProducts().stream().filter(e->e.getId().equals(productId)).findFirst();
                return ResponseEntity.ok(productSearch.orElseThrow(() -> new BusinessNotFoundException(ApiError.ERROR_PRODUCT_NOT_FOUND)));
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @PostMapping(value = "/{businessId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBussinessProduct(@PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId,
            @RequestBody ProductDTO productDTO) {
        service.addBussinessProduct(businessId, productRestMapper.toDomain(productDTO));
        return ResponseEntity.accepted().build();
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @PutMapping(value = "/{businessId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateBussinessProduct(
            @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId, @RequestBody ProductDTO productDTO) {
        service.updateBussinessProduct(businessId, productRestMapper.toDomain(productDTO));
        return ResponseEntity.accepted().build();
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_SELLER'})")
    @DeleteMapping(value = "/{businessId}/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBussinessProduct(
            @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long businessId, @PathVariable @NotNull @Min(0) @Max(Long.MAX_VALUE) Long productId) {
                boolean removed = mapper.toDTO(service.findById(businessId)).getProducts().removeIf(e -> e.getId().equals(productId));
                if (!removed) throw new BusinessNotFoundException(ApiError.ERROR_PRODUCT_NOT_FOUND);
                return ResponseEntity.accepted().build();
    }

}
