package com.txacon.gap.infrastructure.rest.api;

import com.txacon.gap.application.api.TagService;
import com.txacon.gap.application.aspect.Loggable;
import com.txacon.gap.domain.tags.entities.TagName;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "ProductTags")
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService service;

    @Loggable
    @GetMapping("")
    ResponseEntity<List<String>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(Enum::name).collect(Collectors.toList()));
    }

    @Loggable
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("")
    ResponseEntity<String> createNew(@RequestBody String name) {
        TagName toAdd = TagName.valueOf(name);
        service.add(toAdd);
        return ResponseEntity.accepted().body(toAdd.name());
    }
}
