//package com.example.demo.io.aio;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousServerSocketChannel;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.nio.channels.CompletionHandler;
//import java.nio.charset.StandardCharsets;
//
//public class AIOExample {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
//        serverChannel.bind(new InetSocketAddress("localhost", 8888));
//
//        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
//            @Override
//            public void completed(AsynchronousSocketChannel clientChannel, Void attachment) {
//                serverChannel.accept(null, this);
//
//                ByteBuffer buffer = ByteBuffer.allocate(1024);
//                clientChannel.read(buffer, buffer, new CompletionHandler<>() {
//                    @Override
//                    public void completed(Integer bytesRead, ByteBuffer buffer) {
//                        if (bytesRead > 0) {
//                            buffer.flip();
//                            String message = StandardCharsets.UTF_8.decode(buffer).toString();
//                            System.out.println("Received message from client: " + message);
//
//                            // Process the message here
//
//                            // Send response back to the client
//                            String response = "Response from server";
//                            ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8));
//                            clientChannel.write(responseBuffer, null, new CompletionHandler<Integer, Void>() {
//                                @Override
//                                public void completed(Integer bytesWritten, Void attachment) {
//                                    if (responseBuffer.hasRemaining()) {
//                                        clientChannel.write(responseBuffer, null, this);
//                                    } else {
//                                        // Handle response write completion
//                                    }
//                                }
//
//                                @Override
//                                public void failed(Throwable exc, Void attachment) {
//                                    // Handle response write failure
//                                }
//                            });
//                        } else {
//                            try {
//                                clientChannel.close();
//                            } catch (IOException e) {
//                                // Handle channel close failure
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void failed(Throwable exc, ByteBuffer buffer) {
//                        // Handle read failure
//                    }
//                });
//            }
//
//            @Override
//            public void failed(Throwable exc, Void attachment) {
//                // Handle accept failure
//            }
//        });
//
//        // Keep the main thread running to allow accepting client connections
//        Thread.sleep(Long.MAX_VALUE);
//    }
//}
//
