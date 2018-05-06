package com.bird.framework.application.rest.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("system")
public interface UserRest {
    @RequestMapping("/user/username/{username}")
    String getByUsername(@PathVariable("username") String username);
}
