package co.com.ies.adv.backend.cabinas.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.com.ies.adv.backend.cabinas.service.CabinaService;
import co.com.ies.adv.backend.cabinas.web.rest.util.HeaderUtil;
import co.com.ies.adv.backend.cabinas.web.rest.util.PaginationUtil;
import co.com.ies.adv.backend.cabinas.service.dto.CabinaDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Cabina.
 */
@RestController
@RequestMapping("/api")
public class CabinaResource {

    private final Logger log = LoggerFactory.getLogger(CabinaResource.class);

    private static final String ENTITY_NAME = "cabina";

    private final CabinaService cabinaService;

    public CabinaResource(CabinaService cabinaService) {
        this.cabinaService = cabinaService;
    }

    /**
     * POST  /cabinas : Create a new cabina.
     *
     * @param cabinaDTO the cabinaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cabinaDTO, or with status 400 (Bad Request) if the cabina has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cabinas")
    @Timed
    public ResponseEntity<CabinaDTO> createCabina(@Valid @RequestBody CabinaDTO cabinaDTO) throws URISyntaxException {
        log.debug("REST request to save Cabina : {}", cabinaDTO);
        if (cabinaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new cabina cannot already have an ID")).body(null);
        }
        CabinaDTO result = cabinaService.save(cabinaDTO);
        return ResponseEntity.created(new URI("/api/cabinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cabinas : Updates an existing cabina.
     *
     * @param cabinaDTO the cabinaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cabinaDTO,
     * or with status 400 (Bad Request) if the cabinaDTO is not valid,
     * or with status 500 (Internal Server Error) if the cabinaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cabinas")
    @Timed
    public ResponseEntity<CabinaDTO> updateCabina(@Valid @RequestBody CabinaDTO cabinaDTO) throws URISyntaxException {
        log.debug("REST request to update Cabina : {}", cabinaDTO);
        if (cabinaDTO.getId() == null) {
            return createCabina(cabinaDTO);
        }
        CabinaDTO result = cabinaService.save(cabinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cabinaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cabinas : get all the cabinas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cabinas in body
     */
    @GetMapping("/cabinas")
    @Timed
    public ResponseEntity<List<CabinaDTO>> getAllCabinas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Cabinas");
        Page<CabinaDTO> page = cabinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cabinas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cabinas/:id : get the "id" cabina.
     *
     * @param id the id of the cabinaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cabinaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cabinas/{id}")
    @Timed
    public ResponseEntity<CabinaDTO> getCabina(@PathVariable Long id) {
        log.debug("REST request to get Cabina : {}", id);
        CabinaDTO cabinaDTO = cabinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cabinaDTO));
    }

    /**
     * DELETE  /cabinas/:id : delete the "id" cabina.
     *
     * @param id the id of the cabinaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cabinas/{id}")
    @Timed
    public ResponseEntity<Void> deleteCabina(@PathVariable Long id) {
        log.debug("REST request to delete Cabina : {}", id);
        cabinaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
