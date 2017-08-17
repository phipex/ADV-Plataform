package com.ies.raspb_cab.domain;

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

	private final transient EnumError enumError;//TODO Revisar.

	public DomainException(String message, EnumError enumError) {
		super(message);
            this.enumError = enumError;
    }

	public DomainException(EnumError enumError){
		super(enumError.code()+":"+enumError.description());
		this.enumError = enumError;

	}

	public EnumError getEnumError() {
		return enumError;
	}

}
