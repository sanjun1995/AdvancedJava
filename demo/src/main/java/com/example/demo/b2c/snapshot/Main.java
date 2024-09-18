package com.example.demo.b2c.snapshot;

/**
 * @author caozhixin
 * @date 2024/9/18 12:59
 */
public class Main {
    public static void main(String[] args) {
        Product product = new Product(1, "OLD_STATUS");
        ProductService service = new ProductService();

        // 执行持久化操作，changedRequired为true
        ProductPO po = service.productToPO(product, true);

        // 打印结果
        System.out.println("ID: " + po.getId());
        System.out.println("State: " + po.getState());
        System.out.println("Lock Event: " + po.getLockEvent());
    }
}
