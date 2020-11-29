package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.AggregateRatingService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.domain.rating.entities.AggregateRating;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Ratings")
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class AggregateRatingController {

    private final AggregateRatingService service;

    @GetMapping("")
    @Loggable
    ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(Enum::name).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("")
    @Loggable
    ResponseEntity<String> createNew(@RequestBody String name) {
        AggregateRating toAdd = AggregateRating.valueOf(name);
        service.add(toAdd);
        return ResponseEntity.accepted().body(toAdd.name());
    }


}
