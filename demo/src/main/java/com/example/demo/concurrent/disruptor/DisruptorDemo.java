package com.example.demo.concurrent.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// 定义事件类
class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long get() {
        return value;
    }
}

// 定义事件工厂
class LongEventFactory implements EventFactory<LongEvent> {
    public LongEvent newInstance() {
        return new LongEvent();
    }
}

// 定义事件处理器
class LongEventHandler implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + event.get());
    }
}

public class DisruptorDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        Executor executor = Executors.newCachedThreadPool();

        // 创建事件工厂
        LongEventFactory eventFactory = new LongEventFactory();

        // 创建缓冲区大小
        int bufferSize = 1024;

        // 创建 Disruptor 实例
        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, executor);

        // 设置事件处理器
        disruptor.handleEventsWith(new LongEventHandler());

        // 启动 Disruptor
        disruptor.start();

        // 获取 RingBuffer，用于发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        // 发布事件
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l < 10; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }

        // 关闭 Disruptor
        disruptor.shutdown();
    }
}

// 定义事件生产者
class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.set(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}

