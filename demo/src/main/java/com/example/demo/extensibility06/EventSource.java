//package com.example.demo.extensibility06;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author caozhixin
// * @date 2023/11/17 22:18
// */
//public class EventSource {
//    private List<EventListener> listeners = new ArrayList<>();
//    private ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//    public void addEventListener(EventListener listener) {
//        listeners.add(listener);
//    }
//
//    public void fireEvent(final Event event) {
//        for (final EventListener listener : listeners) {
//            executorService.submit(() -> {
//                try {
//                    listener.handleEvent(event);
//                } catch (Exception e) {
//                    // 异常处理
//                    e.printStackTrace();
//                }
//            });
//        }
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
