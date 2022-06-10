package com.devteam.marketing.config;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileApiTest {

    @Test
    public void profile() {
        String expectedProfile = "local";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);

        ProfileApi profileApi = new ProfileApi(env);
        String profile = profileApi.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }


}