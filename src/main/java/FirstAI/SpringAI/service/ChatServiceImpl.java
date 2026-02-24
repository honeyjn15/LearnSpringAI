package FirstAI.SpringAI.service;

import FirstAI.SpringAI.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String chat() {
        String prompt = " Hello tell me about Honey Jain who has worked in Accenture, " +
                "Barclays, fidelity , sapient and currently working with Sber bank. Search on linkedin" +
                "you will able to find him there";
        Prompt prompt1 = new Prompt(prompt);

        return Objects.requireNonNull(chatClient
                        .prompt(prompt1)
                        .call()
                        .content());
    }

    @Override
    public Tut chatEntity() {
        String prompt = "tell me about virat kohli";
        Prompt prompt1 = new Prompt(prompt);

        return Objects.requireNonNull(chatClient
                .prompt(prompt1)
                .call()
                .entity(Tut.class));
    }


    @Override
    public String promptTemplate() {
        String queryStr = "List down top 10 cricketer in world";
        PromptTemplate promptTemplate = new PromptTemplate(queryStr);

        return Objects.requireNonNull(chatClient
                .prompt()
                .user(s -> s.text(queryStr).param("queryKey", queryStr ))
                .call()
                .content());
    }


}
