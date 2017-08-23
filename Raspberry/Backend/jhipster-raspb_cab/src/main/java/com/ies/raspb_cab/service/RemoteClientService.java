package com.ies.raspb_cab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ies.raspb_cab.config.ApplicationProperties;
import com.ies.raspb_cab.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import com.ies.raspb_cab.service.util.AbstractRestCallback;
import java.io.IOException;

@Service
@Transactional
public class RemoteClientService {

    private final Logger log = LoggerFactory.getLogger(RemoteClientService.class);
    private RemoteLoginService.CredentialsRest credentialsRest;
    private RemoteLoginService.Login login;
    private AbstractRestCallback restCallback;


    public static final String CABINAEXCEPTION = "CabinaException";
    public static final String MESSAGE = "message";
    public static final String DESCRIPTION = "description";

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private IRemoteLogin iRemoteLogin;

    public Logger getLog() {
        return log;
    }

    /**
     * Obtiene credenciales, incluso al inicio del sistema
     * @return
     */
    public RemoteLoginService.CredentialsRest getCredentials() {

        if(this.credentialsRest == null) {
            validateCredentials();
        }

        return this.credentialsRest;
    }

    /**
     * Valida credenciales url, token y resourceName
     */
    public boolean validateCredentials() {

        boolean isLoginOK = false;

        this.credentialsRest =
            new RemoteLoginService.CredentialsRest(applicationProperties.getUrl(),null,null);
        login =
            new RemoteLoginService.Login(applicationProperties.getUser(),applicationProperties.getPassword());
        restCallback = getLoginCallBack();

        if (credentialsRest != null) {
            isLoginOK = iRemoteLogin.loginRemoto(this.credentialsRest, login, restCallback);
        }

        return isLoginOK;
    }

    /**
     * Realizar login tras vencimiento de token
     */
    public void refreshCredentials() {
        getLog().info("refreshCredentials::invalidToken: " + credentialsRest.getToken());
        validateCredentials();
        /*if (tokenProvider.validateToken(credentialsRest.getToken())) {
            getLog().info("refreshCredentials::Token: true");
        } else {
            getLog().info("refreshCredentials::Token: false");
            //validateCredentials();
        }*/

    }

    /**
     * Obtener el callback del login
     * @return
     */
	private AbstractRestCallback getLoginCallBack() {

        return new AbstractRestCallback() {
			@Override
			public void onSuccess(ResponseEntity<String> stringResponseEntity) {
                getLog().info("getLoginCallBack::onSuccess: {}", stringResponseEntity);
			}
			@Override
			public void onFailStatus(HttpStatusCodeException e) {
                getLog().info("getLoginCallBack::onFailStatus: {}", e.getMessage());
                if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                    readBodyResponse(e);
                }
                else if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                     getLog().info("getLoginCallBack::onFailStatus: {}", e.getMessage());
                }
            }
			@Override
			public void onFailException(Exception e) {
                getLog().info("RemoteClientService.java::onFailException:: {}", e);
			}
		};
	}

    /**
     * Lee el nodo "message" del JSON que responde el request
     * @param ex
     * @return
     */
	public void readBodyResponse(HttpStatusCodeException ex) {

        ObjectMapper mapper = new ObjectMapper();//Objeto para mapear JSON.
        JsonNode jsonNode;
        JsonNode root;

        try {
            root = mapper.readTree(ex.getResponseBodyAsString());//Convierte String a un objeto JSON.

            if (root != null) {
                jsonNode = root.path(CABINAEXCEPTION);
                sortCabinaException(jsonNode);
            }
        } catch (JsonProcessingException e) {
            getLog().debug("readBodyResponse::JsonProcessingException: {}", e);
        } catch (IOException e) {
            getLog().debug("readBodyResponse::IOException: {}", e);
        }

    }

    /**
     * Clasifica el tipo de CabinaException
     * @param jsonNode
     * @return
     */
    public void sortCabinaException(JsonNode jsonNode){
        String msg;
        String desc;

        if (!jsonNode.isMissingNode()) {
            msg = jsonNode.path(MESSAGE).asText();
            desc = jsonNode.path(DESCRIPTION).asText();

            switch (msg) {
                case "C1":
                    getLog().info("sortCabException::C1: {}", desc);
                    break;
                case "C2":
                    getLog().info("sortCabException::C2: {}", desc);
                    break;
                case "C3":
                    getLog().info("sortCabException::C3: {}", desc);
                    break;
                case "C4":
                    getLog().info("sortCabException::C4: {}", desc);
                    break;
                case "C5":
                    getLog().info("sortCabException::C5: {}", desc);
                    break;
                default:
                    getLog().info("sortCabException::C? Unknow");
                    break;
            }

        }

    }

}
