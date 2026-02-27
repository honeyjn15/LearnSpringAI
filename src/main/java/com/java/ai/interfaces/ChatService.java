package com.java.ai.interfaces;

 import com.java.ai.entity.Tut;

public interface ChatService {

    String chat();

    Tut chatEntity();

    String promptTemplate();

    String dynamicPromptTemplate();

    String systemAndUserRoleTemplate();

    String fluentChatClientApi();

    String externalFile();
}
