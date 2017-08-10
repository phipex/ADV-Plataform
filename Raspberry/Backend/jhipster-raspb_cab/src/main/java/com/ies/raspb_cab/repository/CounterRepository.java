package com.ies.raspb_cab.repository;

import com.ies.raspb_cab.domain.Counter;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Counter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CounterRepository extends JpaRepository<Counter,Long> {

}
