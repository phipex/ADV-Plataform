package co.com.ies.adv.backend.cabinas.domain.core.entities;

import java.math.BigDecimal;

import co.com.ies.adv.backend.cabinas.domain.User;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;

public interface ICabina {

	Long getId();

	void setId(Long id);

	String getIdem();

	void setIdem(String idem);

	String getResponsable();

	void setResponsable(String responsable);

	String getDireccion();

	void setDireccion(String direccion);

	String getDepartamento();

	void setDepartamento(String departamento);

	String getMunicipio();

	void setMunicipio(String municipio);

	BigDecimal getCupo();

	void setCupo(BigDecimal cupo);

	Long getConsegutivo();

	void setConsegutivo(Long consegutivo);

	Double getLongitud();

	void setLongitud(Double longitud);

	Double getLatitud();

	void setLatitud(Double latitud);

	EstadoCabina getEstado();

	void setEstado(EstadoCabina estado);

	String getObservaciones();

	void setObservaciones(String observaciones);

	User getUser();

	void setUser(User user);

}