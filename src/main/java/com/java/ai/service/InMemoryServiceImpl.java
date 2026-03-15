package com.java.ai.service;

import com.java.ai.interfaces.InMemoryService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class InMemoryServiceImpl implements InMemoryService {

    @Value("classpath:/prompts/in-memory-user-message.st")
    private Resource userMessageFileReader;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessageFileReader;

    private final ChatClient chatClient;

    public InMemoryServiceImpl(@Qualifier("inMemoryChatClient") ChatClient inMemoryChClient) {
        this.chatClient = inMemoryChClient;
    }


    @Override
    public String inMemory(String q) {

        return
                this.chatClient
                        .prompt(q)
                        .system(promptSystemSpec -> promptSystemSpec.text(this.systemMessageFileReader))
                        .user(promptUserSpec -> promptUserSpec.text(this.userMessageFileReader)
                                .param("concept" , q))
                        .call()
                        .content();

    }
}
