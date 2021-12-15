package com.nttdata.bootcointransactionservice.application.impl;

import com.nttdata.bootcointransactionservice.application.model.TasaRepository;
import com.nttdata.bootcointransactionservice.application.operations.TasaOperations;
import com.nttdata.bootcointransactionservice.domain.Tasa;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TasaOperationsImpl implements TasaOperations {

    @Autowired
    private TasaRepository tasaRepository;

    @Override
    public Flux<Tasa> findAll() {
        return tasaRepository.findAll();
    }

    @Override
    public Mono<Tasa> findById(String id) {
        return tasaRepository.findById(id);
    }

    @Override
    public Mono<Tasa> save(Tasa tasa) {
        return tasaRepository.save(tasa);
    }

    @Override
    public Mono<Tasa> update(String id, Tasa tasa) {
        return tasaRepository.update(id, tasa);
    }

    @Override
    public Mono<Void> delete(String id) {
        return tasaRepository.delete(id);
    }
}
