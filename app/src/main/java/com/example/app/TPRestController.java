package com.example.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TPRestController {

    @GetMapping("/feur")
    public String feur() {
        return "paquerette";
    }

}
