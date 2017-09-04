package com.ies.raspb_cab.service;

import com.ies.raspb_cab.config.WebSocketClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Service
@Transactional
public class BillAcepterService extends WebSocketClientManager {

    private final Logger log = LoggerFactory.getLogger(BillAcepterService.class);

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

    /**
     * Listen the handleTextMessage method.
     *
     * @param msg
     */
    @Override
    public void listener(String msg){

        if (msg.substring(0, 1).matches("\\d")) {
            int billValue = Integer.parseInt(msg);
            log.info("->listener: " + billValue);
            updateCounter(billValue);
        }
    }

    /**
     * Update a counter.
     *
     * @param bill
     */
    public void updateCounter(int bill){
        log.info("->updateCounter: " + bill);
        //counter.update(bill);
    }

}
