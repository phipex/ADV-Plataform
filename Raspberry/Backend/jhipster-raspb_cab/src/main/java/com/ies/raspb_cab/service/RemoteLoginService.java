package com.ies.raspb_cab.service;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ies.raspb_cab.service.util.AbstractRestCallback;

/**
 * Servicio encargado de crear el login a un servicio rest
 * @author root
 */
@Service
@Transactional
public class RemoteLoginService implements IRemoteLogin {

    public static final String ID_TOKEN = "id_token";
    private final Logger log = LoggerFactory.getLogger(RemoteLoginService.class);

    public Logger getLog() {
        return log;
    }

    @Override
    public boolean loginRemoto(CredentialsRest credencialesRest, Login login, AbstractRestCallback restCallback) {

        boolean loginOk = false;
        String resourceUrl;
        String newToken;

        if(login != null){
            resourceUrl = credencialesRest.getUrl();
            newToken = postRequest(resourceUrl, login, restCallback);

            if (newToken != null && !"".equals(newToken)) {
                credencialesRest.setToken(newToken);
                loginOk = true;
            }

        }

        return loginOk;
    }

    /**
     * crea una peticion post
     * @param resourceUrl url a la piensa logearse
     * @param login datos para el logeo
     * @return Token en caso de login exitoso
     */
    private String postRequest(String resourceUrl,Login login,AbstractRestCallback restCallback) {

        String token = null;

        try {
            URL url = new URL(resourceUrl + "authenticate");
            String tokenObject = requestAction(url.toString(), login, restCallback);
            ObjectMapper mapper = new ObjectMapper();//objeto para mapear json

            final JsonNode jsonNode = mapper.readTree(tokenObject);//convierte a un objeto json

            if (jsonNode != null) {
                final JsonNode tokenNode = jsonNode.path(ID_TOKEN);

                if (!tokenNode.isMissingNode()) {
                    token = tokenNode.asText();
                }
            }

        } catch (JsonProcessingException e) {
            //TODO Qué hacer cuando jsonNode = null?
            getLog().debug("postRequest::JsonProcessingException: {}", e.getMessage());
        } catch (IOException e) {
            getLog().debug("postRequest::IOException {}", e.getMessage());
        }

        return token;
    }

    /**
     * ejecuta una peticion post
     * @param resourceUrl url a la solicita el post
     * @param login objeto con los datos para realizar en login
     * @return
     */
    private String requestAction(String resourceUrl, Login login,AbstractRestCallback restCallback) {

        RestTemplate restTemplate = new RestTemplate();
        final StringBuilder newResource = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;

        try {
            jsonInString = mapper.writeValueAsString(login);

        } catch (JsonProcessingException e) {
            getLog().debug("requestAccion::JsonProcessingException: {}", e);

        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(jsonInString, headers);

            ResponseEntity<String> stringResponseEntity = restTemplate
                .exchange(resourceUrl, HttpMethod.POST, entity, String.class);

            HttpStatus statusCode = stringResponseEntity.getStatusCode(); // statusCode = 200; statusCode.name()= OK

            String bodyResponse = stringResponseEntity.getBody(); // json {"id_token" : "..."}

            if (HttpStatus.OK.equals(statusCode) || HttpStatus.CREATED.equals(statusCode)) {
                newResource.append(bodyResponse); // getLog().info("request OK*** " + newResource + " ***");

                restCallback.callOnSucces(stringResponseEntity);

            }
        }
        catch (HttpStatusCodeException e) {
            restCallback.callOnFailStatus(e);
        }
        catch (RestClientException e) {
            restCallback.callOnFailException(e);
        }

        return newResource.toString();
    }

    /**
     * Objeto que sera serializado para realizar el login
     *
     */
    public static class Login implements Serializable {

        private String username;
        private String password;

        public Login() {
            /**
             *
             */
        }

        public Login(String username, String password) {
            this.username = username;
            this.password = password;

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    /**
     * Credenciales para login de cabina...
     */

    public static class CredentialsRest {

        private String url;
        private String token;
        private String resourceName;

        public CredentialsRest(String url, String token, String resourceName) {
            this.url = url;
            this.token = token;
            this.resourceName = resourceName;

        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getResourceName() {
            return resourceName;
        }

        public void setResourceName(String resourceName) {
            this.resourceName = resourceName;
        }

    }

}
