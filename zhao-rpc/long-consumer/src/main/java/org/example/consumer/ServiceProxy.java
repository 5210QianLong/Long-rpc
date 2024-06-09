package org.example.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.example.model.RpcRequest;
import org.example.model.RpcResponse;
import org.example.serializer.SerializerImpl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //拿到序列化器
        SerializerImpl serializer = new SerializerImpl();
        //模拟消费者 去 发post请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        try {
            //序列化
            byte[] bytes = serializer.serializer(rpcRequest);
            //发送请求
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bytes)
                    .execute()) {
                byte[] bytes1 = httpResponse.bodyBytes();
                //反序列化
                RpcResponse rpcResponse = serializer.deSerializer(bytes1, RpcResponse.class);
                return rpcResponse.getResult();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
