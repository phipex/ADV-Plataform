package com.ies.raspb_cab;

import com.ies.raspb_cab.service.RemoteClientService;
import com.ies.raspb_cab.service.BillAcepterService;
import com.ies.raspb_cab.service.RemoteLoginService;
import groovy.lang.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by betancur343 on 28/07/17.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    BillAcepterService billAcepterService;

    /*@Autowired
    CodeLectorService lector;

    @Autowired
    CodePrinterService printer;*/

    @Autowired
    RemoteClientService remoteClientService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        /**
         * Start connection with bill acepter, lector and printer.
         */
        billAcepterService.startConnection();
        /*lector.startConnection();
        printer.startConnection();*/

        /**
         * Get or validate credentials/token.
         */
        remoteClientService.getCredentials();
    }

} // class
