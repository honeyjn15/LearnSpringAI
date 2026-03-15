package com.java.ai.interfaces;

public interface InMemoryService {

    String inMemory(String q);

    String inMemoryViaConversationId(String q, String userId);
}
