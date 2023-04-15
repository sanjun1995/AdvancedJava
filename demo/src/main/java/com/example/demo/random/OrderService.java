package com.example.demo.random;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class OrderService {
    private static final double TEST_PERCENTAGE = 0.5; // 灰度测试比例
    private static final Map<Integer, Random> randomMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        User user = new User(10001); // 假设用户ID为 10001

        // 下单
        placeOrder(user, new Order("order1"));
        // 发货
        deliverOrder(user, new Order("order1"));
    }

    private static void placeOrder(User user, Order order) {
        if (shouldShowNewOrderProcess(user)) {
            showNewOrderProcess(user); // 展示新的下单流程
        } else {
            showNormalOrderProcess(user); // 展示普通的下单流程
        }
    }

    private static void deliverOrder(User user, Order order) {
        if (shouldUseNewDeliveryProcess(user)) {
            useNewDeliveryProcess(user, order); // 使用新的发货流程
        } else {
            doNormalDelivery(user, order); // 使用普通的发货流程
        }
    }

    private static boolean shouldShowNewOrderProcess(User user) {
        Random random = getRandomForUser(user.getId());
        return random.nextDouble() < TEST_PERCENTAGE; // 返回一个随机布尔值，决定是否展示新的下单流程
    }

    private static void showNewOrderProcess(User user) {
        System.out.println("展示新的下单流程给用户：" + user.getId());
    }

    private static void showNormalOrderProcess(User user) {
        System.out.println("展示旧的下单流程给用户：" + user.getId());
    }

    private static boolean shouldUseNewDeliveryProcess(User user) {
        Random random = getRandomForUser(user.getId());
        return random.nextDouble() < TEST_PERCENTAGE; // 返回一个随机布尔值，决定是否使用新的发货流程
    }

    private static void useNewDeliveryProcess(User user, Order order) {
        System.out.println("使用新的发货流程给用户：" + user.getId() + " 的订单：" + order.getOrderId());
    }

    private static void doNormalDelivery(User user, Order order) {
        System.out.println("使用旧的发货流程给用户：" + user.getId() + " 的订单：" + order.getOrderId());
    }

    private static Random getRandomForUser(int userId) {
        Random random = randomMap.get(userId);
        if (random == null) {
            random = new Random(userId); // 使用固定的随机数种子
            randomMap.put(userId, random);
        }
        // 可以注释上面的代码，使用下面代码
//        Random random = new Random();
        return random;
    }

    private static class User {
        private int id;

        public User(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private static class Order {
        private String orderId;

        public Order(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderId() {
            return orderId;
        }
    }
}
