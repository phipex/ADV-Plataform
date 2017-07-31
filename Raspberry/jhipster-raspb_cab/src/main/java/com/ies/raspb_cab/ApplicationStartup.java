package com.ies.raspb_cab;

import com.ies.raspb_cab.service.BillControlService;
import com.ies.raspb_cab.service.LectorControlService;
import com.ies.raspb_cab.service.PrinterControlService;
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
public class ApplicationStartup
    implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    BillControlService billControlService;

    @Autowired
    LectorControlService lectorControlService;

    @Autowired
    PrinterControlService printerControlService;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        log.info("onApplicationEvent");

        billControlService.startConnection();
        //lectorControlService.startConnection();//TODO(2) Descomentar lector.
        //printerControlService.startConnection(); //TODO(4) Descomentar printer.

        return;
    }

} // class
