package com.example.umc9th.global.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthController {

    @GetMapping("/health")
    public String health() {
        return "ok";
    }
}
