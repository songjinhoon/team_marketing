package com.devteam.marketing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController
public class ProfileApi {

    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }

}
