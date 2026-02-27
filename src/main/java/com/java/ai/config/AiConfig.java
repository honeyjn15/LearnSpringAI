
package com.java.ai.config;

import com.java.ai.advisor.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AiConfig {


    @Bean("customAdvisor")
    public ChatClient customchatClient(ChatClient.Builder chatClient) {
        return chatClient
                .defaultAdvisors(new TokenPrintAdvisor(), new SimpleLoggerAdvisor() )
                .defaultSystem("you are helpful coding assistant")
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("gpt-4o-mini")
                        .temperature(0.3)
                        .maxTokens(200)
                        .build()).build();
    }


    @Bean("normalChatClient")
    public ChatClient normalChatClient(ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("war", "weapon", "destroy"))
                )
                .defaultSystem("You are a helpful coding assistant")
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("gpt-4o-mini")
                        .temperature(0.3)
                        .maxTokens(200)
                        .build())
                .build();
    }


}