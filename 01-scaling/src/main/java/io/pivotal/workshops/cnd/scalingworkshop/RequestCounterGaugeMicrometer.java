package io.pivotal.workshops.cnd.scalingworkshop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Configuration
public class RequestCounterGaugeMicrometer {

    public RequestCounterGaugeMicrometer(RequestCounter requestCounter) {
        this.requestCounter = requestCounter;
    }

    private final RequestCounter requestCounter;

    @Bean
    public Gauge accessLogCounter(MeterRegistry registry) {
        return Gauge.builder("workshop.request.counter", () -> requestCounter.getCounter())
                .tag("kind", "performance")
                .description("Request count")
                .register(registry);
    }
}