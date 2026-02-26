package com.java.ai.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Tut {

    private String topic;
    private String content;
    private String launchYear;
}
