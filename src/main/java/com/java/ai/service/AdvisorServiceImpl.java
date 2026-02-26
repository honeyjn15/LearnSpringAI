package com.java.ai.service;

import com.java.ai.interfaces.AdvisorService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
public class AdvisorServiceImpl implements AdvisorService {

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;


    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;


    public AdvisorServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    private ChatClient chatClient;
    @Override
    public String chatAdvisor() {
        return this.chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system(system -> system.text(this.systemMessage))
                .user(user -> user.text(this.userMessage))
                .call()
                .content();
    }
}
