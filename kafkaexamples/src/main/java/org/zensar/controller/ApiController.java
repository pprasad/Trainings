package org.zensar.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
public class ApiController {

    @GetMapping(value = "/health")
    public String health() {
      return "Hello";
    }
}
