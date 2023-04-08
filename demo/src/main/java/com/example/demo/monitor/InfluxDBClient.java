package com.example.demo.monitor;

/**
 * @author caozhixin
 * @date 2023/4/8 09:22
 */
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class InfluxDBClient {

    private final InfluxDB influxDB;

    public InfluxDBClient(String host, String username, String password, String database) {
        influxDB = InfluxDBFactory.connect(host, username, password);
        influxDB.createDatabase(database);
        influxDB.setDatabase(database);
    }

    public void writeData(long timestamp, String measurement, String[] tags, Object[] values) {
        Point point = Point.measurement(measurement)
                .time(timestamp, TimeUnit.MILLISECONDS)
                .tag(tags[0], tags[1])
                .addField("value", values[0].toString())
                .build();

        BatchPoints batchPoints = BatchPoints.builder().points(point).build();
        influxDB.write(batchPoints);
    }

    public void close() {
        influxDB.close();
    }
}

