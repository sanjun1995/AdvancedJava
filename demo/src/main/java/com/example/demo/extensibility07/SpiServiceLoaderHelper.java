//package com.example.demo.extensibility07;
//
//import java.util.Iterator;
//import java.util.Objects;
//import java.util.ServiceLoader;
//
///**
// * @author caozhixin
// * @date 2023/11/17 22:38
// */
//public class SpiServiceLoaderHelper {
//    public static ProductPackageRemoteServiceInterface getProductPackageRemoteServiceInterface() {
//        // 先从缓存中加载
//        Object serviceCache = DependServiceRegistryHelper.getDependObject(ProductPackageRemoteServiceInterface.class);
//        if (serviceCache != null) {
//            return (ProductPackageRemoteServiceInterface) serviceCache;
//        }
//        // spi 方式加载
//        ProductPackageRemoteServiceInterface serviceInterface = loadSpiImpl(ProductPackageRemoteServiceInterface.class);
//        // 防止注入的bean为空 提前进行判断 以免业务执行出现问题
//        boolean isExist = true;
//        if (Objects.isNull(serviceInterface)) {
//            isExist = false;
//        } else if (Objects.isNull(serviceInterface.getProductPackageRemoteService())) {
//            isExist = false;
//        }
//        if (!isExist) {
//            throw new RuntimeException("getProductPackageRemoteService load impl failed,please check spi service");
//        }
//        // 添加进统一的依赖管理
//        DependServiceRegistryHelper.registry(ProductPackageRemoteServiceInterface.class, serviceInterface);
//        return serviceInterface;
//    }
//
//    /**
//     * 以spi的方式加载实现类
//     *
//     * @param cls
//     * @param <P>
//     * @return
//     */
//    private static <P> P loadSpiImpl(Class<P> cls) {
//        ServiceLoader<P> spiLoader = ServiceLoader.load(cls);
//        Iterator<P> iaIterator = spiLoader.iterator();
//        if (iaIterator.hasNext()) {
//            return iaIterator.next();
//        }
//        return null;
//    }
//}
