package com.ies.raspb_cab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Service
@Transactional
public class BillControlService {

    private final Logger log = LoggerFactory.getLogger(BillControlService.class);

    private String url = "ws://localhost:9876";

    public BillHandler billHandler;

    public WebSocketConnectionManager connectionManager() {

        WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), url);
        manager.setAutoStartup(true);

        return manager;
    }

    public StandardWebSocketClient client() {

        return new StandardWebSocketClient();
    }

    public BillHandler handler(){

        if(this.billHandler == null){
            this.billHandler = new BillHandler();
        }
        return this.billHandler;
    }

}
