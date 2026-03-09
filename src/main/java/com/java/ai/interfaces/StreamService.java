package com.java.ai.interfaces;

import reactor.core.publisher.Flux;

public interface StreamService {

    Flux<String> streamChat(String query);
}
