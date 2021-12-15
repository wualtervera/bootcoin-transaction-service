package com.nttdata.bootcointransactionservice.application.model;

import com.nttdata.bootcointransactionservice.application.CRUD.CRUD;
import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinTransactionRepository  extends CRUD<BootcoinTransaction> {

    /*Flux<BootcoinTransaction> findAll();

    Mono<BootcoinTransaction> findById(String id);

    Mono<BootcoinTransaction> save(BootcoinTransaction bootcoinTransaction);

    Mono<BootcoinTransaction> update(String id, BootcoinTransaction bootcoinTransaction);

    Mono<Void> delete(String id);*/


}
