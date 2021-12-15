package com.nttdata.bootcointransactionservice.infrestructure.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcointransactionservice.application.model.TasaRepository;
import com.nttdata.bootcointransactionservice.domain.Tasa;
import com.nttdata.bootcointransactionservice.infrestructure.model.dao.TasaDao;
import com.nttdata.bootcointransactionservice.infrestructure.rest.repository.TasaCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TasaCrudService  implements TasaRepository{

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TasaCrudRepository tasaCrudRepository;

    @Override
    public Flux<Tasa> findAll() {
        return tasaCrudRepository.findAll().map(this::toTasa);
    }

    @Override
    public Mono<Tasa> findById(String id) {
        return tasaCrudRepository.findById(id).map(this::toTasa);
    }

    @Override
    public Mono<Tasa> save(Tasa tasa) {
        return tasaCrudRepository.save(this.toTasaDao(tasa)).map(this::toTasa);
    }

    @Override
    public Mono<Tasa> update(String id, Tasa tasa) {
        return tasaCrudRepository.findById(id)
                .flatMap(tasaDao -> {
                    tasa.setId(tasaDao.getId());
                    return tasaCrudRepository.save(this.toTasaDao(tasa)).map(this::toTasa);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return tasaCrudRepository.findById(id)
                .flatMap(tasaDao -> tasaCrudRepository.deleteById(id));

    }


    //Mapper
    public Tasa toTasa(TasaDao tasaDao) {
        return objectMapper.convertValue(tasaDao, Tasa.class);
    }

    public TasaDao toTasaDao(Tasa tasa) {

        return objectMapper.convertValue(tasa, TasaDao.class);
    }
}
