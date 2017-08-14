package co.com.ies.adv.backend.cabinas.domain;

/**
 * 
 * @author root
 *
 */
public class DomainException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EnumError enumError;
	
	public DomainException(String message) {
		super(message);
	}
	
	public DomainException(EnumError enumError){
		super(enumError.code()+":"+enumError.description());
		this.enumError = enumError;
		
	}
	
	public EnumError getEnumError() {
		return enumError;
	}

}
