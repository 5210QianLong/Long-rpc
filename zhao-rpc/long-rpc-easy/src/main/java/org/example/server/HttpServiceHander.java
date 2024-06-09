package org.example.server;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import org.example.model.RpcRequest;
import org.example.model.RpcResponse;
import org.example.register.LocalRegister;
import org.example.serializer.Serializer;
import org.example.serializer.SerializerImpl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 *
 * 请求处理
 */
public class HttpServiceHander implements Handler<HttpServerRequest> {
    //从vertx那拿到网络请求
    @Override
    public void handle(HttpServerRequest request) {
        //将请求对象反序列化
        final Serializer serializer = new SerializerImpl();
        //记录日志
        System.out.println("received request: " + request.method() + " " + request.uri());
        //异步处理http请求
        request.bodyHandler(body->{
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                 rpcRequest = serializer.deSerializer(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            //如果请求为null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest1 为null");
                doResponce(request, rpcResponse, serializer);
                return;
            }
            try {
            //通过注册中心获得 实现类 (反射)
            Class<?> implClass = LocalRegister.get(rpcRequest.getServiceName());
            Method methods = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParamsTypes());
            Object invoke = methods.invoke(implClass.newInstance(), rpcRequest.getParams());
            //封装返回结果
                rpcResponse.setResult(invoke);
                rpcResponse.setResultType(methods.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            //响应
            doResponce(request,rpcResponse, serializer);
        });
    }

    /**
     *  响应
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponce(HttpServerRequest request, RpcResponse rpcResponse,Serializer serializer) {
        HttpServerResponse response = request.response().putHeader("Content-Type", "application/json");
        //序列化
        try {
            byte[] bytes = serializer.serializer(rpcResponse);
            response.end(Buffer.buffer(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            response.end(Buffer.buffer());
        }

    }

}
