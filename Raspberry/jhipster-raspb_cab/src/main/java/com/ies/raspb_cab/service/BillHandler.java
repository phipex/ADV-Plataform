package com.ies.raspb_cab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * Created by betancur343 on 28/07/17.
 */
public class BillHandler extends TextWebSocketHandler {

    private final Logger log = LoggerFactory.getLogger(BillHandler.class);

    WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.info("->afterConnectionEstablished");
        this.session = session;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        log.info("->handleTextMessage:" + message);

    }

    public void sendMsg(String msj){

        try {
            this.session.sendMessage(new TextMessage(msj));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
