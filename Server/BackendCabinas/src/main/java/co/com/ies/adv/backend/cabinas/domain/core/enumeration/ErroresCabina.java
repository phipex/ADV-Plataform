package co.com.ies.adv.backend.cabinas.domain.core.enumeration;

import co.com.ies.adv.backend.cabinas.domain.EnumError;

public enum ErroresCabina implements EnumError {
	
	CABINA_INVALIDA("C1", "Cabina es invalida");
	
	
	private String code;
	private String description;
	
	private ErroresCabina(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String code() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return this.description;
	}

}
