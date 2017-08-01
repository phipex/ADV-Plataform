package com.ies.raspb_cab.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

/**
 * Created by betancur343 on 28/07/17.
 */
public class WebSocketHandler extends TextWebSocketHandler {

    private final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.info("->afterConnectionEstablished");
        this.session = session;
    }

    public void sendMsg(String msj){

        try {
            this.session.sendMessage(new TextMessage(msj));
        } catch (IOException e) {
            log.info("IOException: " + e);
        }

    }

}
