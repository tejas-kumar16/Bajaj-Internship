package com.example.demo.service;
import com.example.demo.model.OutputPayload;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RetryService {
    private final RestTemplate restTemplate = new RestTemplate();
    public void sendWithRetry(String url, String token, OutputPayload body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OutputPayload> entity = new HttpEntity<>(body, headers);
        for (int i = 0; i < 4; i++) {
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
                if (response.getStatusCode().is2xxSuccessful()) return;
            } catch (Exception e) {}
        }
    }
}