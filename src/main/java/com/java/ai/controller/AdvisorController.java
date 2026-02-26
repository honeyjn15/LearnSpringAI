package com.java.ai.controller;

import com.java.ai.interfaces.AdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisor")
public class AdvisorController {

    private final AdvisorService advisorService;

    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> basicAdvisor() {
        var response = advisorService.chatAdvisor();
        return ResponseEntity.ok(response);
    }
}
