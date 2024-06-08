package org.example.provider;

import org.example.UserService.UserService;
import org.example.register.LocalRegister;
import org.example.server.verTxServer;

public class ProviderExample {
    public static void main(String[] args) {
        //先注册服务到 注册中心，便于consumer访问
        LocalRegister.register(UserService.class.getName(), UserServiceImpl.class);
        //启动 web服务
        verTxServer verTxServer = new verTxServer();
        verTxServer.doStart(8080);
    }
}
