package com.example.demo.b2c.snapshot;

/**
 * @author caozhixin
 * @date 2024/9/18 13:14
 */
public class ProductService {
    public ProductPO productToPO(Product product, boolean changedRequired) {
        // 创建快照
        Snapshots.snapshot(product);

        // 假设我们更新了一些状态
        product.setStatus("NEW_STATUS");

        // 获取快照
        Product snapshot = Snapshots.snapshot();

        // 创建持久化对象
        ProductPO po = new ProductPO();
        po.setId(product.getId());

        // 比较并持久化状态
        if (product.persistent(product.getStatus(), snapshot.getStatus(), changedRequired)) {
            po.setState(product.getStatus());
        }

        // 假设我们处理锁定事件
        if (product.persistent(product.lockBy(), snapshot.lockBy(), changedRequired)) {
            po.setLockEvent(product.lockBy());
        }

        // 清除快照
        Snapshots.clearSnapshot();

        return po;
    }
}

