package com.ies.raspb_cab.service.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Created by root on 1/07/17.
 */
public interface IRestCallback {


    /**
     * evento que entrega un string con el body de la peticion
     * cuando la peticion tiene http status code 200
     * @param stringResponseEntity
     */
    void onSuccess(ResponseEntity<String> stringResponseEntity);

    /**
     * evento que entrega la exception en caso de que la peticion no
     * responda con http status code 200
     * @param e
     */
    void onFailStatus(HttpStatusCodeException e);

    /**
     * evento que entrega la exception en casos no referentes a peticion
     * @param e
     */
    void onFailException(Exception e);

}
