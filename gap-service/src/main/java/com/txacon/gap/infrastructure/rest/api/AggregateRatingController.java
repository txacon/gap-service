package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.AggregateRatingService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.domain.rating.entities.AggregateRating;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Ratings")
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class AggregateRatingController {

  private final AggregateRatingService service;

  @GetMapping("")
  @Loggable
  public ResponseEntity<List<String>> findAll() {
    return ResponseEntity.ok(
        service.findAll().stream().map(Enum::name).collect(Collectors.toList()));
  }

  @PreAuthorize("hasRole({'ROLE_ADMIN'})")
  @PostMapping("")
  @Loggable
  public ResponseEntity<String> createNew(@RequestBody String name) {
    AggregateRating toAdd = AggregateRating.valueOf(name);
    service.add(toAdd);
    return ResponseEntity.accepted().body(toAdd.name());
  }
}
