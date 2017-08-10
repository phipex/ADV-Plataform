package com.ies.raspb_cab.service;

/**
 * Created by root on 1/08/17.
 */

import org.springframework.http.HttpStatus;

/**
 * Interface para el servicio encargado de logearse a un recurso en un servicio rest
 * @author root
 *
 */
public interface IRemoteLogin {

    /**
     * Funcion para logearse en un recurso remoto
     * @param credentialsRest credenciales con las que se piensa logear
     * @param login datos para el login
     * @return true en caso de login exitoso o false en caso fallido
     */
    boolean loginRemoto(RemoteLoginService.CredentialsRest credentialsRest, RemoteLoginService.Login login);

}
