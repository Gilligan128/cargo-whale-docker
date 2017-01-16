package com.cargowhale.docker.config;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

public class DefaultProfileUtil {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil() {
    }

    public static void addDefaultProfile(final SpringApplication app) {
        Map<String, Object> properties = new HashMap<>();
        /*
        * See https://github.com/spring-projects/spring-boot/issues/1219
        */
        properties.put(SPRING_PROFILE_DEFAULT, ProfileConstants.SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(properties);
    }

    public static String[] getActiveProfiles(final Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
