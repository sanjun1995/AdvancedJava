package com.example.demo.monitor;

import io.prometheus.client.Counter;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.PushGateway;
import java.io.IOException;
import java.util.Date;

public class PushGatewayExample {

    private static final String PUSHGATEWAY_ADDRESS = "localhost:9090";
    private static final String JOB_NAME = "my_job";
    private static final String METRIC_NAME = "my_metric";

    public static void main(String[] args) throws IOException {
        Counter metric = Counter.build()
                .name(METRIC_NAME)
                .help("A simple metric.")
                .register();
        metric.inc();

        PushGateway pushGateway = new PushGateway(PUSHGATEWAY_ADDRESS);
        pushGateway.pushAdd(CollectorRegistry.defaultRegistry, JOB_NAME);

        System.out.println("Pushed " + METRIC_NAME + " at " + new Date());
    }
}