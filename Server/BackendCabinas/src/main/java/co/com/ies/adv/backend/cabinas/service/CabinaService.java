package co.com.ies.adv.backend.cabinas.service;

import co.com.ies.adv.backend.cabinas.domain.core.usecases.ICabinaDomainService;
import co.com.ies.adv.backend.cabinas.service.dto.CabinaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Cabina.
 */
public interface CabinaService extends ICabinaDomainService {

    /**
     * Save a cabina.
     *
     * @param cabinaDTO the entity to save
     * @return the persisted entity
     */
    CabinaDTO save(CabinaDTO cabinaDTO);

    /**
     *  Get all the cabinas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CabinaDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" cabina.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CabinaDTO findOne(Long id);

    /**
     *  Delete the "id" cabina.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
}
