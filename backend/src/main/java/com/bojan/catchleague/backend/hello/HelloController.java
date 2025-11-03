package com.bojan.catchleague.backend.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/ping")
    public String ping() {
        return "pong";
    }
}
