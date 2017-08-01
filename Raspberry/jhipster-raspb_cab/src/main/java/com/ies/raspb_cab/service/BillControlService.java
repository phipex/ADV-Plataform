package com.ies.raspb_cab.service;

import com.ies.raspb_cab.config.WebSocketClientManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Service
@Transactional
public class BillControlService extends WebSocketClientManager {

    public void startConnection(){

        WebSocketConnectionManager connectionManager = connectionManager("ws://localhost:9876");
        connectionManager.start();
    }

    public void enableBill(){
        getHandler().sendMsg("on");
    }

    public void disableBill(){

        getHandler().sendMsg("off");
    }

}
