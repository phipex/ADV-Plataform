package com.ies.raspb_cab.service;

import com.ies.raspb_cab.config.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

/**
 * Created by betancur343 on 29/07/17.
 */
@Service
@Transactional
public class LectorControlService {

    private final Logger log = LoggerFactory.getLogger(LectorControlService.class);

    private String url = "ws://localhost:9876";//TODO(1) Definir puerto de websocket.

    public Handler handler;

    public void startConnection(){

        WebSocketConnectionManager connectionManager = connectionManager();
        connectionManager.start();

    }

    private WebSocketConnectionManager connectionManager() {

        WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), url);
        manager.setAutoStartup(true);

        return manager;
    }

    private StandardWebSocketClient client() {

        return new StandardWebSocketClient();
    }

    private Handler handler(){

        if(this.handler == null){
            this.handler = new Handler();
        }
        return this.handler;
    }

    public void enableLector(){

        this.handler.sendMsg("on");
    }

    public void disableLector(){

        this.handler.sendMsg("off");
    }

}
