package com.ies.raspb_cab;

import com.ies.raspb_cab.service.RemoteClientService;
import com.ies.raspb_cab.service.BillAcepterService;
import com.ies.raspb_cab.service.CodeLectorService;
import com.ies.raspb_cab.service.CodePrinterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by betancur343 on 28/07/17.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    BillAcepterService bill;
    @Autowired
    CodeLectorService lector;
    @Autowired
    CodePrinterService printer;
    @Autowired
    RemoteClientService remoteClient;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        log.info("onApplicationEvent");

        /**
         * Start connection with bill acepter, lector and printer.
         */
        bill.startConnection();
        //lector.startConnection();
        //printer.startConnection();

        /**
         * Get or validate credentials/token.
         */
        remoteClient.getCredentials();

    }

} // class
