package com.ies.raspb_cab.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.ies.raspb_cab.service.util.AbstractRestCallback;

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
            .CredentialsRest("http://localhost:8088/api/",null,null);//TODO colocar en el archivo de propiedades

        this.login = new RemoteLoginService
            .Login("admin","admin");//TODO colocar en el archivo de propiedades

        AbstractRestCallback restCallback = getLoginCallBack();
        
        
        final boolean loginRemoto = iRemoteLogin.loginRemoto(this.credentialsRest, login, restCallback);
		log.info("login OK? " + loginRemoto );

    }

	private AbstractRestCallback getLoginCallBack() {
		AbstractRestCallback restCallback = new AbstractRestCallback() {
			
			@Override
			public void onSucces(ResponseEntity<String> stringResponseEntity) {
				// TODO Auto-generated method stub
				System.out.println("RemoteClientService.java::onSucces::stringResponseEntity");
			}
			
			@Override
			public void onFailStatus(HttpStatusCodeException e) {
				// TODO Auto-generated method stub
				System.out.println("RemoteClientService.java::onFailStatus::e");
				
			}
			
			@Override
			public void onFailException(Exception e) {
				// TODO Auto-generated method stub
				System.out.println("RemoteClientService.java::onFailException::e");
				
				
			}
		};
		return restCallback;
	}

    /**
     *
     */


}
