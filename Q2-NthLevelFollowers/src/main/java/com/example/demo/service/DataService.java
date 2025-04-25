package com.example.demo.service;
import com.example.demo.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class DataService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final FollowSolver solver = new FollowSolver();
    private final RetryService retryService = new RetryService();

    public void execute() {
        String regNo = "RA2211003010720";
        String name = "Tejas Kumar";
        String email = "tk7842@srmist.edu.in";
        Map<String, Object> request = Map.of(
            "name", name,
            "regNo", regNo,
            "email", email
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<InputPayload> response = restTemplate.exchange(
            "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook",
            HttpMethod.POST,
            entity,
            InputPayload.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            InputPayload input = response.getBody();
            assert input != null;
            List<User> users = input.getData().getUsers();
            List<Integer> result = solver.findNthLevelFollowers(users, input.getData().getFindId(), input.getData().getN());
            OutputPayload output = new OutputPayload(regNo, result);
            retryService.sendWithRetry(input.getWebhook(), input.getAccessToken(), output);
        }
    }
}