package com.java.ai.interfaces;

public interface AdvisorService {
    String simpleLoggerAdvisor();

    String safeGuardAdvisor(String q);

    String customGuardAdvisor(String q);
}