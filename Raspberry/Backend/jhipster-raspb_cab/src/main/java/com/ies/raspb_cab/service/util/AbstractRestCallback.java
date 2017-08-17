package com.ies.raspb_cab.service.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Created by root on 1/07/17.
 */
public abstract class AbstractRestCallback implements IRestCallback{


    /**
     * evento que entrega un string con el body de la peticion
     * cuando la peticion tiene http status code 200
     * @param stringResponseEntity
     */
    public void callOnSucces(ResponseEntity<String> stringResponseEntity){
        onSuccess(stringResponseEntity);
    }

    /**
     * evento que entrega la exception en caso de que la peticion no
     * responda con http status code 200
     * @param e
     */
    public void callOnFailStatus(HttpStatusCodeException e){
        onFailStatus(e);
    }

    /**
     * evento que entrega la exception en casos no referentes a peticion
     * @param e
     */
    public void callOnFailException(Exception e){
        onFailException(e);
    }

}
