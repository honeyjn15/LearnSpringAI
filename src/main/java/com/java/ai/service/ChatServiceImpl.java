package com.java.ai.service;

import com.java.ai.entity.Tut;
import com.java.ai.interfaces.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessageFileReader;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * Simple plain text prompt call to LLM
     * Returns response as String
     */
    @Override
    public String chat() {

        String promptText = """
                Hello, tell me about Honey Jain who has worked in
                Accenture, Barclays, Fidelity, Sapient and currently
                working with Sber Bank.
                """;

        Prompt prompt = new Prompt(promptText);

        return Objects.requireNonNull(
                chatClient.prompt(prompt)
                        .call()
                        .content()
        );
    }

    /**
     * Demonstrates mapping LLM response directly to a Java Entity (Tut class)
     * Useful when expecting structured JSON output
     */
    @Override
    public Tut chatEntity() {

        String promptText = "Tell me about Virat Kohli";

        Prompt prompt = new Prompt(promptText);

        return Objects.requireNonNull(
                chatClient.prompt(prompt)
                        .call()
                        .entity(Tut.class)
        );
    }

    /**
     * Static PromptTemplate example
     * Demonstrates how prompt template works
     */
    @Override
    public String promptTemplate() {

        String queryStr = "List down top 10 cricketers in the world";

        PromptTemplate promptTemplate = new PromptTemplate(queryStr);

        return Objects.requireNonNull(
                chatClient.prompt()
                        .user(u -> u.text(queryStr))
                        .call()
                        .content()
        );
    }

    /**
     * Dynamic PromptTemplate example
     * Template -> Render -> Prompt -> LLM
     */
    @Override
    public String dynamicPromptTemplate() {

        PromptTemplate promptTemplate = PromptTemplate.builder()
                .template("What is {techName} and give an example of {example}")
                .build();

        // Rendering template with dynamic values
        String renderedMessage = promptTemplate.render(
                Map.of("techName", "Java",
                        "example", "Collection")
        );

        Prompt prompt = new Prompt(renderedMessage);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    /**
     * Demonstrates System Role + User Role
     * System role defines assistant behavior
     */
    @Override
    public String systemAndUserRoleTemplate() {

        var systemRoleTemplate = SystemPromptTemplate.builder()
                .template("You are a helpful coding assistant.")
                .build();

        var systemMessage = systemRoleTemplate.createMessage();

        var userTemplate = PromptTemplate.builder()
                .template("What is {techName} and give example of {example}")
                .build();

        var userMessage = userTemplate.createMessage(
                Map.of("techName", "Java",
                        "example", "Collection")
        );

        Prompt prompt = new Prompt(systemMessage, userMessage);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    /**
     * Fluent ChatClient API example
     * Cleaner and more readable approach
     */
    @Override
    public String fluentChatClientApi() {

        return chatClient.prompt()
                .system(s -> s.text("You are an expert coding assistant."))
                .user(u -> u.text("Tell me about {cricketer} and the {country} they belong to.")
                        .param("cricketer", "Virat Kohli")
                        .param("country", "pakistan"))
                .call()
                .content();
    }

    @Override
    public String externalFile() {
        return chatClient.prompt()
                .system("read the file and tell")
                .user(u -> u.text(this.userMessageFileReader).param("war" , "country"))
                .call().content();

    }
}
