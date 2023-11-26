package com.renan.backfistdecision.domain.service;

import com.renan.backfistdecision.domain.entity.Number;
import com.renan.backfistdecision.domain.exception.EntityNotFoundException;
import com.renan.backfistdecision.domain.repository.NumberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Renan on 23/11/2023.
 */
@Service
public class NumberService implements Serializable {
    
    private NumberRepository numberRepository;
    
    public NumberService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public List<Number> find() {
        return numberRepository.findAll();
    }

    public Number findById(Long id) {
        return numberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(Number number) {
        numberRepository.save(number);
    }

    @Transactional
    public void update(Number number) {
        Number c = numberRepository.findById(number.getId()).orElseThrow(EntityNotFoundException::new);
        c.setNumber(number.getNumber());
        c.setClient_id(number.getClient_id());

        numberRepository.save(c);
    }
    
    @Transactional
    public void remove(Long id) {
        numberRepository.deleteById(id);
    }
}
