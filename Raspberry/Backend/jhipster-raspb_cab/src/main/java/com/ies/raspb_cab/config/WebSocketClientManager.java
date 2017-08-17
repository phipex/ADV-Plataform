package com.ies.raspb_cab.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

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

        if(getHandler() == null){
            this.handler = new WebSocketHandler();
        }

        return getHandler();
    }

    /**
     * This method will be override on bill, lector and printer service
     *
     * @param msg
     */
    public void listener(String msg){
        /**
         * Override method
         */
    }

    /**
     * Class: WebSocketHandler
     *
     */
    public class WebSocketHandler extends TextWebSocketHandler {

        private final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

        private WebSocketSession session;

        public Logger getLog() {
            return log;
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            this.session = session;

        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
            listener(message.getPayload());
        }

        /**
         * Send message through WebSocket
         * @param msj
         */
        public void sendMsg(String msj){
            try {
                this.session.sendMessage(new TextMessage(msj));

            } catch (IOException e) {
                getLog().debug("sendMsg::IOException: " + e);

            }

        }

    }

}
