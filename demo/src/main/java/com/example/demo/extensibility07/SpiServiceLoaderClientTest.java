package com.example.demo.extensibility07;

/**
 * @author caozhixin
 * @date 2023/11/17 22:40
 */
public class SpiServiceLoaderClientTest {
    public static void main(String[] args) {
        ProductPackageRemoteServiceInterface productPackageRemoteServiceInterface = SpiServiceLoaderHelper.getProductPackageRemoteServiceInterface();
    }
}
