package com.nttdata.bootcointransactionservice.infrestructure.rest.repository;

import com.nttdata.bootcointransactionservice.infrestructure.model.dao.BootcoinTransactionDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootcoinTransactionCrudRepository extends ReactiveCrudRepository<BootcoinTransactionDao, String> {

}
