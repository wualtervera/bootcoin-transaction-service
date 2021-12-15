package com.nttdata.bootcointransactionservice.application.impl;

import com.nttdata.bootcointransactionservice.application.model.BootcoinTransactionRepository;
import com.nttdata.bootcointransactionservice.application.operations.BootcoinTransactionOperations;
import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BootcoinTransactionOperationsImpl implements BootcoinTransactionOperations {

    @Autowired
    private BootcoinTransactionRepository bootcoinTransactionRepository;

    @Override
    public Flux<BootcoinTransaction> findAll() {
        return bootcoinTransactionRepository.findAll();
    }

    @Override
    public Mono<BootcoinTransaction> findById(String id) {
        return bootcoinTransactionRepository.findById(id);
    }

    @Override
    public Mono<BootcoinTransaction> save(BootcoinTransaction bootcoinTransaction) {
        return bootcoinTransactionRepository.save(bootcoinTransaction);
    }

    @Override
    public Mono<BootcoinTransaction> update(String id, BootcoinTransaction bootcoinTransaction) {
        return bootcoinTransactionRepository.update(id, bootcoinTransaction);
    }

    @Override
    public Mono<Void> delete(String id) {
        return bootcoinTransactionRepository.delete(id);
    }


}
