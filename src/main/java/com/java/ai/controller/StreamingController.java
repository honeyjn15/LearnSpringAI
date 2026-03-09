package com.java.ai.controller;


import com.java.ai.interfaces.StreamService;
import com.java.ai.service.StreamServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/stream")
@RestController
public class StreamingController {

    public final StreamService streamService;

    public StreamingController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/v1")
    public ResponseEntity<Flux<String>> streamChat(@RequestParam(value = "q") String q) {
        var response = streamService.streamChat(q);
        return ResponseEntity.ok(response);
    }
}
