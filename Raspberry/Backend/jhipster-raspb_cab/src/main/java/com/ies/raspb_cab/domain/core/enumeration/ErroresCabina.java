package com.ies.raspb_cab.domain.core.enumeration;

import com.ies.raspb_cab.domain.EnumError;

public enum ErroresCabina implements EnumError {

	CABINA_INVALIDA("C1", "Cabina es invalida"),
	ERROR_AL_INGRESAR_LA_CABINA_AL_SISTEMA("C2","Error al ingresar la cabina al sistema"),
	LA_CABINA_NO_TIENE_CUPO("C3","La cabina no tiene cupo"),
	CABINA_SE_ENCUENTRA_INACTIVA("C4","Cabina se encuentra inactiva"),
	NO_HAY_CABINA_ASOCIADA_AL_USUARIO("C5","No hay cabina asociada al usuario");


	private String code;
	private String description;

	private ErroresCabina(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String code() {

		return this.code;
	}

	@Override
	public String description() {

		return this.description;
	}

}
