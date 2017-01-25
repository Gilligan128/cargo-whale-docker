package com.pillartechnology.pluggedinmetrics;

import com.pillartechnology.pluggedinmetrics.config.DefaultProfileUtil;
import com.pillartechnology.pluggedinmetrics.config.ProfileConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class MetricsDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(MetricsDemoApplication.class);

    public static void main(final String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(MetricsDemoApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:{}\n\t" +
                "External: \thttp://{}:{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));

        checkProfiles(env);
    }

    private static void checkProfiles(final Environment env) {
        log.info("Running with Spring profile(s) : {}", Arrays.toString(DefaultProfileUtil.getActiveProfiles(env)));
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        if (activeProfiles.contains(ProfileConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(ProfileConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
    }
}
