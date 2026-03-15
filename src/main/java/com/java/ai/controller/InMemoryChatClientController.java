package com.java.ai.controller;

import com.java.ai.interfaces.InMemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InMemoryChatClientController {

    private final InMemoryService inMemoryService;


    @GetMapping("/memory")
    public ResponseEntity<String> inMemoryChatClient(@RequestParam(value = "q") String q) {
        var response = inMemoryService.inMemory(q);
        return ResponseEntity.ok(response);
    }



}
