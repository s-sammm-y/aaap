package com.aaap.webapi.Config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class KeepMeAlive {
    private final RestClient restClient;

    public KeepMeAlive() {
        this.restClient = RestClient.create(
                "https://aaap-63bx.onrender.com"
        );
    }

    @Scheduled(fixedRate = 14 * 60 * 1000)
    public void pingHealthController() {

        try {
            String response = restClient.get()
                    .uri("/health")
                    .retrieve()
                    .body(String.class);

            System.out.println("Health check: " + response);

        } catch (Exception e) {
            System.err.println("Health check failed: " + e.getMessage());
        }
    }
}
