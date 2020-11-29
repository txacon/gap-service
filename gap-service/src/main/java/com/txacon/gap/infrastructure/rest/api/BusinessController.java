package com.txacon.gap.infrastructure.rest.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Businesses")
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessController {


}
