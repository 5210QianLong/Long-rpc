package org.example.server;

import io.vertx.core.Vertx;

public class verTxServer implements HttpServer{
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(req -> {
            System.out.println("received request"+req.method()+" "+req.uri());
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World");
        });
        httpServer.listen(port,result->{
            if(result.succeeded()){
                System.out.println("server started on port "+port);
            }else {
                System.out.println("server start failed"+result.cause());
            }
        });
    }
}
