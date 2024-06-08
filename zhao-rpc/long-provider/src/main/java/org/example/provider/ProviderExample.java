package org.example.provider;

import org.example.server.verTxServer;

public class ProviderExample {
    public static void main(String[] args) {
        verTxServer verTxServer = new verTxServer();
        verTxServer.doStart(8080);
    }
}
