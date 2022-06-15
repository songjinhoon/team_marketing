package com.devteam.marketing.config;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileApi {

    private final Environment env;

    @GetMapping("/profile")
    public String profile() {
        final List<String> profiles = Arrays.asList(env.getActiveProfiles());
        final List<String> realProfiles = Arrays.asList("rea1", "real2");
        final String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }

}
