package org.example.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 */
public class LocalRegister {
    //注册信息存贮
   private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();
   //consumer  到注册中心 找userService的实现类 调用 userService.getUser(user) 去获得 User
    // provider 提供 userService的实现类 UserServiceImpl 到注册中心

    /**
     *  注册服务
     * @param serviceName
     * @param implClazz
     */
    public static void register(String serviceName, Class<?> implClazz) {
        map.put(serviceName, implClazz);
    }
    /**
     *  获得服务
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     *  移除服务
     * @param serviceName
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
