package com.java.ai.service;


import com.java.ai.interfaces.StreamService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StreamServiceImpl implements StreamService {

    private final ChatClient basicChatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;


    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    public StreamServiceImpl(@Qualifier("normalChatClient") ChatClient basicChatClient) {
        this.basicChatClient = basicChatClient;
    }


    @Override
    public Flux<String> streamChat(String query) {

        return basicChatClient
                .prompt(query)
                .system(promptSystemSpec -> promptSystemSpec.text(this.systemMessage))
                .user(promptUserSpec -> promptUserSpec.text(this.userMessage)
                        .param("concept" , query))
                .stream()
                .content();

    }
}
