package co.com.ies.adv.backend.cabinas.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;

/**
 * A DTO for the Cabina entity.
 */
public class CabinaDTO implements Serializable {

    private Long id;

    @NotNull
    private String idem;

    @NotNull
    private String responsable;

    @NotNull
    private String direccion;

    @NotNull
    private String departamento;

    @NotNull
    private String municipio;

    @NotNull
    private BigDecimal cupo;

    @NotNull
    private Long consegutivo;

    private Double longitud;

    private Double latitud;

    @NotNull
    private EstadoCabina estado;

    private String observaciones;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdem() {
        return idem;
    }

    public void setIdem(String idem) {
        this.idem = idem;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public BigDecimal getCupo() {
        return cupo;
    }

    public void setCupo(BigDecimal cupo) {
        this.cupo = cupo;
    }

    public Long getConsegutivo() {
        return consegutivo;
    }

    public void setConsegutivo(Long consegutivo) {
        this.consegutivo = consegutivo;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public EstadoCabina getEstado() {
        return estado;
    }

    public void setEstado(EstadoCabina estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CabinaDTO cabinaDTO = (CabinaDTO) o;
        if(cabinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cabinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CabinaDTO{" +
            "id=" + getId() +
            ", idem='" + getIdem() + "'" +
            ", responsable='" + getResponsable() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", departamento='" + getDepartamento() + "'" +
            ", municipio='" + getMunicipio() + "'" +
            ", cupo='" + getCupo() + "'" +
            ", consegutivo='" + getConsegutivo() + "'" +
            ", longitud='" + getLongitud() + "'" +
            ", latitud='" + getLatitud() + "'" +
            ", estado='" + getEstado() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            "}";
    }
}
