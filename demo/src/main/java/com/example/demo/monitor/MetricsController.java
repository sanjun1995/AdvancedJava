package com.example.demo.monitor;

/**
 * @author caozhixin
 * @date 2023/4/8 09:48
 */
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MetricsController {

    private final MeterRegistry meterRegistry;

    @Autowired
    public MetricsController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @GetMapping("/metrics")
    public Map<String, Double> getMetrics() {
        Map<String, Double> metrics = new HashMap<>();
        for (Meter meter : meterRegistry.getMeters()) {
            metrics.put(meter.getId().getName(), meter.measure().iterator().next().getValue());
        }
        return metrics;
    }

}
