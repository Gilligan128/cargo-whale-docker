package com.cargowhale.docker.config.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.jvm.*;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableMetrics(proxyTargetClass = true)
public class MetricsConfiguration extends MetricsConfigurerAdapter {

    private static final String PROP_METRIC_REG_JVM_MEMORY = "jvm.memory";
    private static final String PROP_METRIC_REG_JVM_GARBAGE = "jvm.garbage";
    private static final String PROP_METRIC_REG_JVM_THREADS = "jvm.threads";
    private static final String PROP_METRIC_REG_JVM_FILES = "jvm.files";
    private static final String PROP_METRIC_REG_JVM_BUFFERS = "jvm.buffers";

    private final Logger log = LoggerFactory.getLogger(MetricsConfiguration.class);

    private final MetricRegistry metricRegistry = new MetricRegistry();
    private final HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

    private final MetricsLogsProperties metricsLogsProperties;
    private final MetricsGraphiteProperties metricsGraphiteProperties;

    @Autowired
    public MetricsConfiguration(final MetricsLogsProperties metricsLogsProperties, final MetricsGraphiteProperties metricsGraphiteProperties) {
        this.metricsLogsProperties = metricsLogsProperties;
        this.metricsGraphiteProperties = metricsGraphiteProperties;
    }

    @Override
    @Bean
    public MetricRegistry getMetricRegistry() {
        return this.metricRegistry;
    }

    @Override
    @Bean
    public HealthCheckRegistry getHealthCheckRegistry() {
        return this.healthCheckRegistry;
    }

    @PostConstruct
    public void init() {
        this.log.debug("Registering JVM gauges");
        this.metricRegistry.register(PROP_METRIC_REG_JVM_MEMORY, new MemoryUsageGaugeSet());
        this.metricRegistry.register(PROP_METRIC_REG_JVM_GARBAGE, new GarbageCollectorMetricSet());
        this.metricRegistry.register(PROP_METRIC_REG_JVM_THREADS, new ThreadStatesGaugeSet());
        this.metricRegistry.register(PROP_METRIC_REG_JVM_FILES, new FileDescriptorRatioGauge());
        this.metricRegistry.register(PROP_METRIC_REG_JVM_BUFFERS, new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));

        if (this.metricsLogsProperties.getEnabled()) {
            this.log.info("Initializing Metrics Log reporting");

            final Slf4jReporter reporter = Slf4jReporter.forRegistry(this.metricRegistry)
                .outputTo(LoggerFactory.getLogger("metrics"))
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .prefixedWith(this.metricsLogsProperties.getPrefix())
                .build();
            reporter.start(this.metricsLogsProperties.getPeriod(), TimeUnit.SECONDS);
        }

        if (this.metricsGraphiteProperties.getEnabled()) {
            this.log.info("Initializing Metrics Graphite reporting");

            String host = this.metricsGraphiteProperties.getHost();
            Integer port = this.metricsGraphiteProperties.getPort();
            String prefix = this.metricsGraphiteProperties.getPrefix();
            Long period = this.metricsGraphiteProperties.getPeriod();

            Graphite graphite = new Graphite(new InetSocketAddress(host, port));
            final GraphiteReporter reporter = GraphiteReporter.forRegistry(this.metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .prefixedWith(prefix)
                .build(graphite);
            reporter.start(period, TimeUnit.SECONDS);
        }
    }
}
