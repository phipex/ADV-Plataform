package com.ies.raspb_cab.service;

import com.ies.raspb_cab.service.util.AbstractRestCallback;


/**
 * Interface para el servicio encargado de logearse a un recurso en un servicio rest
 * @author root
 *
 */
@FunctionalInterface
public interface IRemoteLogin {

    /**
     * Funcion para logearse en un recurso remoto
     * @param credentialsRest credenciales con las que se piensa logear
     * @param login datos para el login
     * @return true en caso de login exitoso o false en caso fallido
     */
    boolean loginRemoto(RemoteLoginService.CredentialsRest credentialsRest, RemoteLoginService.Login login, AbstractRestCallback restCallback);

}
