package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.PaymentMethodService;
import com.txacon.gap.domain.payment.entities.PaymentType;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "PaymentMethods")
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService service;

    @GetMapping("")
    ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(Enum::name).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("")
    ResponseEntity<String> createNew(@RequestBody String name) {
        PaymentType toAdd = PaymentType.valueOf(name);
        service.add(toAdd);
        return ResponseEntity.accepted().body(toAdd.name());
    }
}
