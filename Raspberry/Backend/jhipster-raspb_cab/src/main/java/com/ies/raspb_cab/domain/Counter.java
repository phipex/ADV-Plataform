package com.ies.raspb_cab.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Counter.
 */
@Entity
@Table(name = "counter")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Counter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "registry", nullable = false)
    private String registry;

    @NotNull
    @Column(name = "counter", nullable = false)
    private Integer counter;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistry() {
        return registry;
    }

    public Counter registry(String registry) {
        this.registry = registry;
        return this;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public Integer getCounter() {
        return counter;
    }

    public Counter counter(Integer counter) {
        this.counter = counter;
        return this;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getTotal() {
        return total;
    }

    public Counter total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Counter counter = (Counter) o;
        if (counter.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), counter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Counter{" +
            "id=" + getId() +
            ", registry='" + getRegistry() + "'" +
            ", counter='" + getCounter() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
}
