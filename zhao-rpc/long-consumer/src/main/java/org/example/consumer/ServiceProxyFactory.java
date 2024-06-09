package org.example.consumer;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂
 */
public class ServiceProxyFactory {
    /**
     * 根据服务类 动态的获取 代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }
}
