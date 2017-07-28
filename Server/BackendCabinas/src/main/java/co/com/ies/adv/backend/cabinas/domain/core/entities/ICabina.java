package co.com.ies.adv.backend.cabinas.domain.core.entities;

import java.math.BigDecimal;

import co.com.ies.adv.backend.cabinas.domain.User;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;

public interface ICabina {

	Long getId();

	String getIdem();

	String getResponsable();

	String getDireccion();

	String getDepartamento();

	String getMunicipio();

	BigDecimal getCupo();

	Long getConsegutivo();

	Double getLongitud();

	Double getLatitud();

	EstadoCabina getEstado();

	String getObservaciones();

	User getUser();

}