package com.bird.framework.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@Order(1)
public class BasicAuthAspect {

    /**
     * 服务熔断
     *
     * @param jp
     */
    @Around("@annotation(com.bird.framework.application.annotations.BasicAuth)")
    public void fuse(JoinPoint jp) {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String credentials = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        String credentials = "admin:12345";
        String base64Credentials = new String(Base64.encodeBase64(credentials.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }
}
