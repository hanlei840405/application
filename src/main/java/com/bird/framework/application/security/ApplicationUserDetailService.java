package com.bird.framework.application.security;

import com.bird.framework.application.rest.consumer.UserRest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ApplicationUserDetailService implements UserDetailsService {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private UserRest userRest;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String json = userRest.getByUsername(s);
        if (StringUtils.isEmpty(json)) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }
        try {
            Map<String, Object> userMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            String password = (String) userMap.get("password");
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("admin"));

            UserDetails userDetails = new org.springframework.security.core.userdetails.
                    User(s, password, authorities);
            return userDetails;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
