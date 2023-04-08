package com.example.demo.monitor;

/**
 * @author caozhixin
 * @date 2023/4/8 13:10
 */
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BankAccountSystem {

    // 存储每个银行账户的当前余额
    private static final Map<String, Double> accountBalances = new HashMap<>();

    // 创建一个Counter指标来表示已经从每个账户取款的次数
    private static final Counter withdrawals = Counter.build()
            .name("bank_account_withdrawals_total")
            .help("Total number of account withdrawals.")
            .labelNames("account_number") // 为了方便查询，使用名为"account_number"的标签分别标识每个账户的指标
            .register();

    // 创建一个Gauge指标来表示每个账户的当前余额
    private static final Gauge accountBalance = Gauge.build()
            .name("bank_account_balance")
            .help("Current balance for each account.")
            .labelNames("account_number") // 同样使用名为"account_number"的标签来标识每个账户的指标
            .register();

    public static void main(String[] args) throws IOException {
        // Start the HTTP server and expose metrics endpoint.
        // 启动一个HTTP服务器，并暴露/metrics端点以便Prometheus从中获取指标数据
        HTTPServer server = new HTTPServer(8080);

        Random random = new Random();
        // 初始化10个不同的银行账户，并对每个账户进行一次初始余额的设置
        for (int i = 1; i <= 10; i++) {
            String accountNumber = "ACCT" + i;
            double initialBalance = random.nextDouble() * 100000;
            accountBalances.put(accountNumber, initialBalance);
            // 使用set方法将每个账户的初始余额设置为Gauge指标的值，同时使用"labelValues"参数传递与之对应的"account_number"标签值
            accountBalance.labels(accountNumber).set(initialBalance);
        }

        // 模拟一些账户活动
        while (true) {
            try {
                Thread.sleep(5000); // 等待5秒钟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 对于每个银行账户，随机从中扣除一些资金，并更新相应的指标数据
            for (Map.Entry<String, Double> entry : accountBalances.entrySet()) {
                String accountNumber = entry.getKey();
                double currentBalance = entry.getValue();
                double withdrawAmount = random.nextDouble() * 100;
                if (withdrawAmount > currentBalance) {
                    withdrawAmount = currentBalance; // 不能透支，如果取款金额超过了当前余额，那么就只能全部取出
                }
                currentBalance -= withdrawAmount;
                accountBalances.put(accountNumber, currentBalance);
                withdrawals.labels(accountNumber).inc(); // 增加该账户的Counter指标值（表示已经从这个账户中取过款）
                accountBalance.labels(accountNumber).set(currentBalance); // 更新该账户的Gauge指标值（表示当前余额）
            }
        }
    }
}

