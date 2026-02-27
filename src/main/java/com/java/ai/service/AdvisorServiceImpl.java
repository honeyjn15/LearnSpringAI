package com.java.ai.service;

import com.java.ai.interfaces.AdvisorService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdvisorServiceImpl implements AdvisorService {

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;


    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    private final ChatClient customTokenAdvisorChatClient;
    private final ChatClient normalChatClient;

    public AdvisorServiceImpl(@Qualifier("customAdvisor") ChatClient customTokenAdvisorChatClient,
                              @Qualifier("normalChatClient") ChatClient normalChatClient) {
        this.customTokenAdvisorChatClient = customTokenAdvisorChatClient;
        this.normalChatClient = normalChatClient;
    }

    //you are calling system message and user message from normal chatClient. So you can call either
    //from Config class or in service class depending on the requirement
    @Override
    public String simpleLoggerAdvisor() {
        return this.normalChatClient
                .prompt()
                .system(system -> system.text(this.systemMessage))
                .user(user -> user.text(this.userMessage))
                .call()
                .content();
    }


    //you can safeguard or whitelist some of the words at global level also by defining in config
    //and method level also by defining in service layer
    @Override
    public String safeGuardAdvisor(String q) {
        return this.normalChatClient
                .prompt(q)
                .advisors(new SafeGuardAdvisor(List.of("game")))
                .call()
                .content();
    }


    @Override
    public String customGuardAdvisor(String q) {
        return this.customTokenAdvisorChatClient
                .prompt(q)
                .call()
                .content();
    }
}
