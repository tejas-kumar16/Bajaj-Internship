package com.example.demo.model;

import lombok.Data;
import java.util.List;

@Data
public class InputPayload {
    private String webhook;
    private String accessToken;
    private InnerData data;

    @Data
    public static class InnerData {
        private int n;
        private int findId;
        private List<User> users;
    }
}