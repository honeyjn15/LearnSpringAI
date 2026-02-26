package com.java.ai.controller;

import com.java.ai.entity.Tut;
import com.java.ai.interfaces.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MultiModalController {

    private final ChatService chatService;


    public MultiModalController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> basicChatClientUsage(@RequestParam(value = "q") String q) {
        var response = chatService.chat();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/chatEntity")
    public ResponseEntity<Tut> useEntityClass() {
        var response = chatService.chatEntity();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/promptTemplate")
    public ResponseEntity<String> promptTemplate() {
        var response = chatService.promptTemplate();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dynamicTemplate")
    public ResponseEntity<String> dynamicPromptTemplate() {
        var response = chatService.dynamicPromptTemplate();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/userPromptTemplate")
    public ResponseEntity<String> systemAndUserRoleTemplate() {
        var response = chatService.systemAndUserRoleTemplate();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/fluentApi")
    public ResponseEntity<String> fluentClientApi() {
        var response = chatService.fluentChatClientApi();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/fileReader")
    public ResponseEntity<String> externalFileReader() {
        var response = chatService.externalFile();
        return ResponseEntity.ok(response);
    }

}
