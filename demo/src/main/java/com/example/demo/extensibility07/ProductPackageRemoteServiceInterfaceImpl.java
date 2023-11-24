package com.example.demo.extensibility07;

/**
 * @author caozhixin
 * @date 2023/11/17 22:45
 */
public class ProductPackageRemoteServiceInterfaceImpl implements ProductPackageRemoteServiceInterface {
    @Override
    public String getProductPackageRemoteService() {
        System.out.println("test");
        return "google";
    }
}
