//package com.example.demo.extensibility06;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//
///**
// * @author caozhixin
// * @date 2023/11/17 22:24
// */
//public class EventSource02 {
//    private List<EventListener> listeners = new ArrayList<>();
//    private ExecutorService executorService = Executors.newFixedThreadPool(2);
//    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();
//
//    public void addEventListener(EventListener listener) {
//        listeners.add(listener);
//    }
//
//    public void fireEvent(final Event event) {
//        try {
//            eventQueue.put(event);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    public void startEventProcessing() {
//        executorService.submit(() -> {
//            while (true) {
//                try {
//                    Event event = eventQueue.take();
//                    for (final EventListener listener : listeners) {
//                        executorService.submit(() -> listener.handleEvent(event));
//                    }
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    break;
//                }
//            }
//        });
//    }
//
//    public void shutdown() {
//        executorService.shutdown();
//        try {
//            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
//                executorService.shutdownNow();
//                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
//                    System.err.println("Pool did not terminate");
//                }
//            }
//        } catch (InterruptedException e) {
//            executorService.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
//    }
//}
