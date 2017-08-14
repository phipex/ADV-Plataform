package co.com.ies.adv.backend.cabinas.domain.core.exceptions;

import co.com.ies.adv.backend.cabinas.domain.DomainException;
import co.com.ies.adv.backend.cabinas.domain.EnumError;

/**
 * Excepcion aplicable a las cabinas
 * 
 * @author Andres Felipe Montoya
 *
 */
public class CabinaException extends DomainException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CabinaException(String mensaje){
		super(mensaje);
	}
	
	public CabinaException(EnumError enumError) {
		super(enumError);
		
	}

	
		
}
