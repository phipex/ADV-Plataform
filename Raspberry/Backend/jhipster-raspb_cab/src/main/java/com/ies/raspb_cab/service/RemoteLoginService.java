package com.ies.raspb_cab.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

/**
 * Created by root on 1/08/17.
 */

/**
 * Servicio encargado de crear el login a un servicio rest
 * @author root
 */
@Service
@Transactional
public class RemoteLoginService implements IRemoteLogin {

    public static final String ID_TOKEN = "id_token";
    private final Logger log = LoggerFactory.getLogger(RemoteLoginService.class);

    @Override
    public boolean loginRemoto(CredentialsRest credencialesRest, Login login) {
        boolean loginOk = false;
        String resourceUrl = credencialesRest.getUrl();
        String newToken = postRequest(resourceUrl, login);

        if(newToken != null && !"".equals(newToken)){
            credencialesRest.setToken(newToken);
            loginOk = true;
        }

        return loginOk;
    }

    /**
     * crea una peticion post
     * @param resourceUrl url a la piensa logearse
     * @param login datos para el logeo
     * @return Token en caso de login exitoso
     */
    private String postRequest(String resourceUrl,Login login){

        String token = null;
        String urlAutenticate = resourceUrl + "authenticate";
        String tokenObject = requestAccion(urlAutenticate, login);

        ObjectMapper mapper = new ObjectMapper();//objeto para mapear json

        try {
            final JsonNode jsonNode = mapper.readTree(tokenObject);//convierte a un objeto json

            //log.debug("jsonNode.toString(): " + jsonNode.toString());
            if (jsonNode != null) {
                final JsonNode id_token = jsonNode.path(ID_TOKEN);
                //log.debug("id_token: " + id_token);
                token = id_token.asText();
            }
            log.debug("token: " + token);
        } catch (Exception e) {
            log.info("Exception: " + e);
        }

        return token;
    }

    /**
     * ejecuta una peticion post
     * @param resourceUrl url a la solicita el post
     * @param login objeto con los datos para realizar en login
     * @return
     */
    private String requestAccion(String resourceUrl, Login login) {

        RestTemplate restTemplate = new RestTemplate();

        final StringBuilder newResource = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = null;

        try {
            jsonInString = mapper.writeValueAsString(login);
        } catch (JsonProcessingException e) {
            log.info("" + e);
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = null;

            entity = new HttpEntity<String>(jsonInString, headers);

            ResponseEntity<String> stringResponseEntity = restTemplate.exchange(resourceUrl, HttpMethod.POST, entity, String.class);

            HttpStatus statusCode = stringResponseEntity.getStatusCode(); // statusCode = 200; statusCode.name()= OK
            String bodyResponse = stringResponseEntity.getBody(); // {"id_token" : "..."}

            final String responseMsg = "crearRecargaWPlay::status = " + statusCode.name()
                + ", bodyResponse" + bodyResponse;

            //log.info(responseMsg);

            if (HttpStatus.OK.equals(statusCode) || HttpStatus.CREATED.equals(statusCode)) {

                //log.info("request correcto************* " + newResource + " ****");
                newResource.append(bodyResponse);
                log.info("request correcto************* " + newResource + " ****");
            }

        }
        catch (HttpStatusCodeException e) {
            log.info("HttpStatusCodeException: " + e);
        }
        catch (RestClientException e) {
            log.info("RestClientException: " + e);
        }

        //log.info("terminar correcto************* " + newResource + "****");

        return newResource.toString();
    }

    /**
     * Objeto que sera serializado para realizar el login
     *
     */
    public static class Login implements Serializable{
        public String username;
        public String password;

        public Login() {
            // . para compatibilidad
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
     *
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
