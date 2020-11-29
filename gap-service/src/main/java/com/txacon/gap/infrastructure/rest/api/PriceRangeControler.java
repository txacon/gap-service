package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.PriceRangeService;
import com.txacon.gap.domain.pricerange.entities.PriceRange;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "PriceRanges")
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceRangeControler {

    private final PriceRangeService service;

    @GetMapping("")
    ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(Enum::name).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("")
    ResponseEntity<String> createNew(@RequestBody String name) {
        PriceRange toAdd = PriceRange.valueOf(name);
        service.add(toAdd);
        return ResponseEntity.accepted().body(toAdd.name());
    }
}
