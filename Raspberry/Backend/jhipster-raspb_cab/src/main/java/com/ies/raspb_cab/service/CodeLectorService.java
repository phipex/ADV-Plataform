package com.ies.raspb_cab.service;

import com.ies.raspb_cab.config.WebSocketClientManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Service
@Transactional
public class CodeLectorService extends WebSocketClientManager {

    public void startConnection(){

        WebSocketConnectionManager connectionManager = connectionManager("ws://localhost:6543");
        connectionManager.start();
    }

    public void enableLector(){

        getHandler().sendMsg("on");
    }

    public void disableLector(){

        getHandler().sendMsg("off");
    }

    /**
     * Listen the handleTextMessage method.
     * @param msg
     */
    @Override
    public void listener(String msg) {
        /**
         *
         */
    }
}
