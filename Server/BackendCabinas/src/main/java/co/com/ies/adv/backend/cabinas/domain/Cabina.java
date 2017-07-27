package co.com.ies.adv.backend.cabinas.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;

/**
 * A Cabina.
 */
@Entity
@Table(name = "cabina")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cabina implements Serializable,ICabina {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "idem", nullable = false)
    private String idem;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private String responsable;

    @NotNull
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Column(name = "departamento", nullable = false)
    private String departamento;

    @NotNull
    @Column(name = "municipio", nullable = false)
    private String municipio;

    @NotNull
    @Column(name = "cupo", precision=10, scale=2, nullable = false)
    private BigDecimal cupo;

    @NotNull
    @Column(name = "consegutivo", nullable = false)
    private Long consegutivo;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "latitud")
    private Double latitud;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCabina estado;

    @Column(name = "observaciones")
    private String observaciones;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdem() {
        return idem;
    }

    public Cabina idem(String idem) {
        this.idem = idem;
        return this;
    }

    public void setIdem(String idem) {
        this.idem = idem;
    }

    public String getResponsable() {
        return responsable;
    }

    public Cabina responsable(String responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDireccion() {
        return direccion;
    }

    public Cabina direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Cabina departamento(String departamento) {
        this.departamento = departamento;
        return this;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Cabina municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public BigDecimal getCupo() {
        return cupo;
    }

    public Cabina cupo(BigDecimal cupo) {
        this.cupo = cupo;
        return this;
    }

    public void setCupo(BigDecimal cupo) {
        this.cupo = cupo;
    }

    public Long getConsegutivo() {
        return consegutivo;
    }

    public Cabina consegutivo(Long consegutivo) {
        this.consegutivo = consegutivo;
        return this;
    }

    public void setConsegutivo(Long consegutivo) {
        this.consegutivo = consegutivo;
    }

    public Double getLongitud() {
        return longitud;
    }

    public Cabina longitud(Double longitud) {
        this.longitud = longitud;
        return this;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Cabina latitud(Double latitud) {
        this.latitud = latitud;
        return this;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public EstadoCabina getEstado() {
        return estado;
    }

    public Cabina estado(EstadoCabina estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(EstadoCabina estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Cabina observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public User getUser() {
        return user;
    }

    public Cabina user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cabina cabina = (Cabina) o;
        if (cabina.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cabina.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cabina{" +
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
