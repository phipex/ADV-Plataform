package com.ies.raspb_cab.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ies.raspb_cab.domain.Counter;
import com.ies.raspb_cab.service.CounterService;
import com.ies.raspb_cab.web.rest.util.HeaderUtil;
import com.ies.raspb_cab.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
 * REST controller for managing Counter.
 */
@RestController
@RequestMapping("/api")
public class CounterResource {

    private final Logger log = LoggerFactory.getLogger(CounterResource.class);

    private static final String ENTITY_NAME = "counter";

    private final CounterService counterService;

    public CounterResource(CounterService counterService) {
        this.counterService = counterService;
    }

    /**
     * POST  /counters : Create a new counter.
     *
     * @param counter the counter to create
     * @return the ResponseEntity with status 201 (Created) and with body the new counter, or with status 400 (Bad Request) if the counter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/counters")
    @Timed
    public ResponseEntity<Counter> createCounter(@Valid @RequestBody Counter counter) throws URISyntaxException {
        log.debug("REST request to save Counter : {}", counter);
        if (counter.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new counter cannot already have an ID")).body(null);
        }
        Counter result = counterService.save(counter);
        return ResponseEntity.created(new URI("/api/counters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /counters : Updates an existing counter.
     *
     * @param counter the counter to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated counter,
     * or with status 400 (Bad Request) if the counter is not valid,
     * or with status 500 (Internal Server Error) if the counter couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/counters")
    @Timed
    public ResponseEntity<Counter> updateCounter(@Valid @RequestBody Counter counter) throws URISyntaxException {
        log.debug("REST request to update Counter : {}", counter);
        if (counter.getId() == null) {
            return createCounter(counter);
        }
        Counter result = counterService.save(counter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, counter.getId().toString()))
            .body(result);
    }

    /**
     * GET  /counters : get all the counters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of counters in body
     */
    @GetMapping("/counters")
    @Timed
    public ResponseEntity<List<Counter>> getAllCounters(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Counters");
        pageable = new PageRequest(0, 20, Sort.Direction.DESC, "id");
        Page<Counter> page = counterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/counters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /counters/:id : get the "id" counter.
     *
     * @param id the id of the counter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the counter, or with status 404 (Not Found)
     */
    @GetMapping("/counters/{id}")
    @Timed
    public ResponseEntity<Counter> getCounter(@PathVariable Long id) {
        log.debug("REST request to get Counter : {}", id);
        Counter counter = counterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(counter));
    }

    /**
     * DELETE  /counters/:id : delete the "id" counter.
     *
     * @param id the id of the counter to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/counters/{id}")
    @Timed
    public ResponseEntity<Void> deleteCounter(@PathVariable Long id) {
        log.debug("REST request to delete Counter : {}", id);
        counterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
