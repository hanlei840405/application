package com.bird.framework.application.rest.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserRest {
    @Autowired
    private RestTemplate restTemplate;

    public String getByUsername(String username) {
    }
}
