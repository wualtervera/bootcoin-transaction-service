package com.nttdata.bootcointransactionservice.infrestructure.rest.repository;

import com.nttdata.bootcointransactionservice.infrestructure.model.dao.TasaDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TasaCrudRepository extends ReactiveCrudRepository<TasaDao, String> {
}
