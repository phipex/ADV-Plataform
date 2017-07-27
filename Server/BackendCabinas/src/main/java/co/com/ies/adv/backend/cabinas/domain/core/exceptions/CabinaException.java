package co.com.ies.adv.backend.cabinas.domain.core.exceptions;

/**
 * Excepcion aplicable a las cabinas
 * 
 * @author Andres Felipe Montoya
 *
 */
public class CabinaException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private final String message;

	public CabinaException(String message) {
		this.message = message;
	}
	
	
}
