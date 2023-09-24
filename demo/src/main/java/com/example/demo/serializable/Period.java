package com.example.demo.serializable;

/**
 * @author caozhixin
 * @date 2023/9/24 15:13
 */

import java.io.*;
import java.util.Date;

public class Period implements Serializable {
    private Date start;
    private Date end;

    public Period(Date start, Date end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    // To prevent MutablePeriod attack, provide custom serialization methods
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Serialize non-transient fields

        // Serialize start and end as milliseconds
        out.writeLong(start.getTime());
        out.writeLong(end.getTime());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize non-transient fields

        // Deserialize start and end from milliseconds
        long startTime = in.readLong();
        long endTime = in.readLong();
        this.start = new Date(startTime);
        this.end = new Date(endTime);

        // Ensure the deserialized period is consistent
        if (start.compareTo(end) > 0) {
            throw new InvalidObjectException("Deserialized period is invalid");
        }
    }

    public static void main(String[] args) throws Exception {
        // Create a period
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 1000); // 1 second later
        Period originalPeriod = new Period(startDate, endDate);

        // Serialize the period to a file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("period.ser"))) {
            out.writeObject(originalPeriod);
        }

        // Deserialize the period from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("period.ser"))) {
            Period deserializedPeriod = (Period) in.readObject();
            System.out.println("Deserialized period: " + deserializedPeriod.getStart() + " to " + deserializedPeriod.getEnd());
        }
    }
}

