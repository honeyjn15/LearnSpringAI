package com.java.ai.controller;

import com.java.ai.interfaces.AdvisorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advisor")
public class AdvisorController {

    private final AdvisorService advisorService;

    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @GetMapping("/s1")
    public ResponseEntity<String> simpleLoggerAdvisor() {
        var response = advisorService.simpleLoggerAdvisor();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/s2")
    public ResponseEntity<String> safeGuarLoggerAdvisor(@RequestParam(value = "q") String q) {
        var response = advisorService.safeGuardAdvisor(q);
        return ResponseEntity.ok(response);
    }

    // here advisor is custom which has configuration in AIConfig class
    @GetMapping("/s3")
    public ResponseEntity<String> customLoggerAdvisor(@RequestParam(value = "q") String q) {
        var response = advisorService.customGuardAdvisor(q);
        return ResponseEntity.ok(response);
    }
}
