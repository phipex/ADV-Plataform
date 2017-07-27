package co.com.ies.adv.backend.cabinas.repository;

import co.com.ies.adv.backend.cabinas.domain.Cabina;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Cabina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CabinaRepository extends JpaRepository<Cabina,Long> {
    
}
