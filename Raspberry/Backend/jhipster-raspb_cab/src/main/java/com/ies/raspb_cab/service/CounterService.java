package com.ies.raspb_cab.service;

import com.ies.raspb_cab.domain.Counter;
import com.ies.raspb_cab.repository.CounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

/**
 * Service Implementation for managing Counter.
 */
@Service
@Transactional
public class CounterService {

    private final Logger log = LoggerFactory.getLogger(CounterService.class);

    private final CounterRepository counterRepository;

    private long lastElement;

    private LocalDateTime dateTime;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    /**
     * Save a counter.
     *
     * @param counter the entity to save
     * @return the persisted entity
     */
    public Counter save(Counter counter) {
        log.debug("Request to save Counter : {}", counter);
        return counterRepository.save(counter);
    }

    /**
     *  Get all the counters.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Counter> findAll(Pageable pageable) {
        log.debug("Request to get all Counters");
        this.lastElement = counterRepository.findAll(pageable).getTotalElements();// lastElement = numberOfElements
        return counterRepository.findAll(pageable);
    }

    /**
     *  Get one counter by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Counter findOne(Long id) {
        log.debug("Request to get Counter : {}", id);
        return counterRepository.findOne(id);
    }

    /**
     *  Delete the counter by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Counter : {}", id);
        counterRepository.delete(id);
    }

    /**
     * Update the last created counter.
     *
     *  @param billValue the value of new bill
     */
    public void update(int billValue) {
        //TODO Evaluar el caso en que no halla registros en entidad Counter.
        log.debug("Request to update Counter: {}", lastElement);
        Counter counter = counterRepository.findOne(lastElement);
        counter.setRegistry(getCurrentDate());
        counter.setCounter(counter.getCounter() + 1);
        counter.setTotal(counter.getTotal() + billValue);
        counterRepository.save(counter);
    }

    /**
     * Get the current day
     * @return
     */
    private String getCurrentDate() {
        dateTime = LocalDateTime.now();
        log.debug("Request to get current date: {}", dateTime);
        return dateTime.toString();
    }

}
