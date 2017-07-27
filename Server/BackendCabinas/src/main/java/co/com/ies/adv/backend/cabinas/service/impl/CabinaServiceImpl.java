package co.com.ies.adv.backend.cabinas.service.impl;

import co.com.ies.adv.backend.cabinas.service.CabinaService;
import co.com.ies.adv.backend.cabinas.domain.Cabina;
import co.com.ies.adv.backend.cabinas.repository.CabinaRepository;
import co.com.ies.adv.backend.cabinas.service.dto.CabinaDTO;
import co.com.ies.adv.backend.cabinas.service.mapper.CabinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Cabina.
 */
@Service
@Transactional
public class CabinaServiceImpl implements CabinaService{

    private final Logger log = LoggerFactory.getLogger(CabinaServiceImpl.class);

    private final CabinaRepository cabinaRepository;

    private final CabinaMapper cabinaMapper;

    public CabinaServiceImpl(CabinaRepository cabinaRepository, CabinaMapper cabinaMapper) {
        this.cabinaRepository = cabinaRepository;
        this.cabinaMapper = cabinaMapper;
    }

    /**
     * Save a cabina.
     *
     * @param cabinaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CabinaDTO save(CabinaDTO cabinaDTO) {
        log.debug("Request to save Cabina : {}", cabinaDTO);
        Cabina cabina = cabinaMapper.toEntity(cabinaDTO);
        cabina = cabinaRepository.save(cabina);
        return cabinaMapper.toDto(cabina);
    }

    /**
     *  Get all the cabinas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CabinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cabinas");
        return cabinaRepository.findAll(pageable)
            .map(cabinaMapper::toDto);
    }

    /**
     *  Get one cabina by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CabinaDTO findOne(Long id) {
        log.debug("Request to get Cabina : {}", id);
        Cabina cabina = cabinaRepository.findOne(id);
        return cabinaMapper.toDto(cabina);
    }

    /**
     *  Delete the  cabina by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cabina : {}", id);
        cabinaRepository.delete(id);
    }
}
