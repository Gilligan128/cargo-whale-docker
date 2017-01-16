package com.cargowhale.docker;

import com.cargowhale.docker.config.DefaultProfileUtil;
import com.cargowhale.docker.config.docker.DockerProperties;
import com.cargowhale.docker.config.ProfileConstants;
import com.cargowhale.docker.config.metrics.MetricsGraphiteProperties;
import com.cargowhale.docker.config.metrics.MetricsJvmProperties;
import com.cargowhale.docker.config.metrics.MetricsLogsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@EnableConfigurationProperties({DockerProperties.class, MetricsLogsProperties.class, MetricsGraphiteProperties.class, MetricsJvmProperties.class})
@SpringBootApplication
public class CargoWhaleApplication {

    private static final Logger log = LoggerFactory.getLogger(CargoWhaleApplication.class);

    public static void main(final String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(CargoWhaleApplication.class);
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
        log.info("\n----------------------------------------------------------\n\t" +
                "Docker running at '{}'\n----------------------------------------------------------",
            env.getProperty("cargowhale.docker.uri"));

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
