package com.ies.raspb_cab.config;

import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

public class WebSocketClientManager {

    private WebSocketHandler handler;

    public WebSocketHandler getHandler() {

        return this.handler;
    }

    public WebSocketConnectionManager connectionManager(String urlBill) {

        WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), urlBill);
        manager.setAutoStartup(true);

        return manager;
    }

    private StandardWebSocketClient client() {

        return new StandardWebSocketClient();
    }

    private WebSocketHandler handler(){

        if(this.handler == null){
            this.handler = new WebSocketHandler();
        }
        return this.handler;
    }

}
