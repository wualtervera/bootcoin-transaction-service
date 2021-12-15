package com.nttdata.bootcointransactionservice.infrestructure.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcointransactionservice.application.model.BootcoinTransactionRepository;
import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import com.nttdata.bootcointransactionservice.infrestructure.model.dao.BootcoinTransactionDao;
import com.nttdata.bootcointransactionservice.infrestructure.rest.repository.BootcoinTransactionCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BootcoinTransactionCrudService implements BootcoinTransactionRepository {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BootcoinTransactionCrudRepository bootcoinTransactionCrudRepository;


    @Override
    public Flux<BootcoinTransaction> findAll() {
        return bootcoinTransactionCrudRepository.findAll().map(this::toWalletTransaction);
    }

    @Override
    public Mono<BootcoinTransaction> findById(String id) {
        return bootcoinTransactionCrudRepository.findById(id).map(this::toWalletTransaction);
    }

    @Override
    public Mono<BootcoinTransaction> save(BootcoinTransaction bootcoinTransaction) {

        return bootcoinTransactionCrudRepository.save(this.toWalletTransactionDao(bootcoinTransaction)).map(this::toWalletTransaction);
    }

    @Override
    public Mono<BootcoinTransaction> update(String id, BootcoinTransaction bootcoinTransaction) {
        return bootcoinTransactionCrudRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(walletDao -> {
                    bootcoinTransaction.setId(walletDao.getId());
                    bootcoinTransaction.setCreateAt(walletDao.getCreateAt());
                    return bootcoinTransactionCrudRepository.save(this.toWalletTransactionDao(bootcoinTransaction)).map(this::toWalletTransaction);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return bootcoinTransactionCrudRepository.findById(id)
                .flatMap(tasaDao -> bootcoinTransactionCrudRepository.deleteById(id));
    }


    public BootcoinTransaction toWalletTransaction(BootcoinTransactionDao bootcoinTransactionDao) {
        return objectMapper.convertValue(bootcoinTransactionDao, BootcoinTransaction.class);
    }

    public BootcoinTransactionDao toWalletTransactionDao(BootcoinTransaction bootcoinTransaction) {

        return objectMapper.convertValue(bootcoinTransaction, BootcoinTransactionDao.class);
    }
}
