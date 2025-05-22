package com.appsoft.foodmart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class KhaltiController {

    @PostMapping("/khalti-verify")
    public ResponseEntity<String> verifyKhalti(@RequestBody Map<String, Object> payload) {
        String token = (String) payload.get("token");
        Integer amount = (Integer) payload.get("amount");

        String verificationUrl = "https://khalti.com/api/v2/payment/verify/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Key test_secret_key_3ddf0b6d2a064f1bb181e204cce3b67d");
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("token", token);
        requestBody.put("amount", amount);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(verificationUrl, request, String.class);

        // Optionally: Store transaction in DB here

        return ResponseEntity.ok(response.getBody());
    }
}
