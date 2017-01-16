package com.cargowhale.docker.config.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
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

    private final MetricsLogsProperties metricsLogsProperties;
    private final MetricsGraphiteProperties metricsGraphiteProperties;
    private final MetricsJvmProperties metricsJvmProperties;

    @Autowired
    public MetricsConfiguration(final MetricsLogsProperties metricsLogsProperties, final MetricsGraphiteProperties metricsGraphiteProperties, final MetricsJvmProperties metricsJvmProperties) {
        this.metricsLogsProperties = metricsLogsProperties;
        this.metricsGraphiteProperties = metricsGraphiteProperties;
        this.metricsJvmProperties = metricsJvmProperties;
    }

    @Bean
    @Override
    public MetricRegistry getMetricRegistry() {
        return new MetricRegistry();
    }

    @PostConstruct
    public void init() {
        MetricRegistry metricRegistry = getMetricRegistry();

        if (this.metricsJvmProperties.getEnabled()) {
            registerJvmMetrics(metricRegistry);
        }

        if (this.metricsLogsProperties.getEnabled()) {
            registerLogReporter(metricRegistry);
        }

        if (this.metricsGraphiteProperties.getEnabled()) {
            registerGraphiteReporter(metricRegistry);
        }
    }

    private void registerJvmMetrics(final MetricRegistry metricRegistry) {
        this.log.debug("Registering JVM gauges");

        metricRegistry.register(PROP_METRIC_REG_JVM_MEMORY, new MemoryUsageGaugeSet());
        metricRegistry.register(PROP_METRIC_REG_JVM_GARBAGE, new GarbageCollectorMetricSet());
        metricRegistry.register(PROP_METRIC_REG_JVM_THREADS, new ThreadStatesGaugeSet());
        metricRegistry.register(PROP_METRIC_REG_JVM_FILES, new FileDescriptorRatioGauge());
        metricRegistry.register(PROP_METRIC_REG_JVM_BUFFERS, new BufferPoolMetricSet(ManagementFactory.getPlatformMBeanServer()));
    }

    private void registerLogReporter(final MetricRegistry metricRegistry) {
        this.log.debug("Initializing Metrics Log reporting");

        String prefix = this.metricsLogsProperties.getPrefix();
        Long period = this.metricsLogsProperties.getPeriod();

        final Slf4jReporter reporter = Slf4jReporter.forRegistry(metricRegistry)
            .outputTo(LoggerFactory.getLogger("metrics"))
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .prefixedWith(prefix)
            .build();
        registerReporter(reporter).start(period, TimeUnit.SECONDS);
    }

    private void registerGraphiteReporter(final MetricRegistry metricRegistry) {
        this.log.debug("Initializing Metrics Graphite reporting");

        String host = this.metricsGraphiteProperties.getHost();
        Integer port = this.metricsGraphiteProperties.getPort();
        String prefix = this.metricsGraphiteProperties.getPrefix();
        Long period = this.metricsGraphiteProperties.getPeriod();
        Graphite graphite = new Graphite(new InetSocketAddress(host, port));

        final GraphiteReporter reporter = GraphiteReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .prefixedWith(prefix)
            .build(graphite);
        registerReporter(reporter).start(period, TimeUnit.SECONDS);
    }
}
