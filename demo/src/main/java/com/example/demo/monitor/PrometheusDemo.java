package com.example.demo.monitor;

/**
 * @author caozhixin
 * @date 2023/4/8 12:59
 */
import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import net.minidev.json.JSONUtil;

import java.io.IOException;
import java.util.Random;

public class PrometheusDemo {

    private static final Counter requestsTotal = Counter.build()
            .name("requests_caozhixin_total")
            .help("Total number of requests.")
            .register();

    public static void main(String[] args) throws IOException {
        // Start the HTTP server and expose metrics endpoint.
        HTTPServer server = new HTTPServer(8080);

        // Generate some random data and increase the counter.
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double value = random.nextDouble();
            requestsTotal.inc(value);
            System.out.println(requestsTotal.collect());
        }
    }
}