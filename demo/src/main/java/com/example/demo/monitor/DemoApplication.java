package com.example.demo.monitor;

/**
 * @author caozhixin
 * @date 2023/4/8 09:23
 */
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@ComponentScan(basePackages = {"io.prometheus.client.spring.boot"})
public class DemoApplication {

    static final Counter requests = Counter.build()
            .name("demo_requests_total")
            .help("Total number of demo requests.")
            .register();

    static final Gauge queueSize = Gauge.build()
            .name("demo_queue_size")
            .help("Size of the demo queue.")
            .register();

    private final InfluxDBClient influxDBClient = new InfluxDBClient("http://localhost:9991", "username", "password", "database");

    @Scheduled(fixedDelay = 5000)
    public void processQueue() {
        int queueSizeValue = getQueueSize();
        queueSize.set(queueSizeValue);

        // 将时序数据存储到InfluxDB中
        long timestamp = System.currentTimeMillis();
        influxDBClient.writeData(timestamp, "queue_size", new String[]{"queue_name", "demo_queue"}, new Object[]{queueSizeValue});

    }

    private int getQueueSize() {
        // 假设获取队列大小的方法为getQueueSize()
        return getQueueSize();
    }

    // 主函数
    public static void main(String[] args) {
        DefaultExports.initialize();
        SpringApplication.run(DemoApplication.class, args);
    }
}
