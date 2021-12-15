package com.nttdata.bootcointransactionservice.application.impl;

import com.nttdata.bootcointransactionservice.application.operations.TasaOperations;
import com.nttdata.bootcointransactionservice.domain.Tasa;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TasaOperationsImpl implements TasaOperations {

    @Override
    public Flux<Tasa> findAll() {
        return null;
    }

    @Override
    public Mono<Tasa> findById(String id) {
        return null;
    }

    @Override
    public Mono<Tasa> save(Tasa tasa) {
        return null;
    }

    @Override
    public Mono<Tasa> update(String id, Tasa tasa) {
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return null;
    }
}
