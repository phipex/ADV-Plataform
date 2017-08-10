package com.ies.raspb_cab.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by root on 1/08/17.
 */

@Service
public class RemoteClientService {

    private final Logger log = LoggerFactory.getLogger(RemoteClientService.class);
    private RemoteLoginService.CredentialsRest credentialsRest = null;
    private RemoteLoginService.Login login = null;

    @Autowired
    private IRemoteLogin iRemoteLogin;

    public RemoteLoginService.CredentialsRest getCredentials() {

        if(this.credentialsRest == null) {
            validateCredentials();
        }

        return this.credentialsRest;
    }

    /**
     * Validate credentials. Token update/refresh.
     */
    public void validateCredentials() {

        log.info("validateCredentials");

        this.credentialsRest = new RemoteLoginService
            .CredentialsRest("http://localhost:8088/api/",null,null);

        this.login = new RemoteLoginService
            .Login("admin","admin");

        log.info("login OK? " + iRemoteLogin.loginRemoto(this.credentialsRest, login) );

    }

    /**
     *
     */


}
