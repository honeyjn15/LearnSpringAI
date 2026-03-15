package com.java.ai.controller;

import com.java.ai.interfaces.InMemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("/conversation")
    public ResponseEntity<String> inMemoryChatClientViaConversationId(@RequestParam(value = "q") String q,
        @RequestHeader("userId") String userId) {
        var response = inMemoryService.inMemoryViaConversationId(q, userId);
        return ResponseEntity.ok(response);
    }


}
