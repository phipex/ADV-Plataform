package com.ies.raspb_cab.service;

import com.ies.raspb_cab.config.WebSocketClientManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Service
@Transactional
public class CodePrinterService extends WebSocketClientManager {

    public void startConnection(){

        WebSocketConnectionManager connectionManager = connectionManager("ws://localhost:3210");
        connectionManager.start();
    }

    public void enablePrinter(){

        getHandler().sendMsg("on");
    }

    public void disablePrinter(){

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
