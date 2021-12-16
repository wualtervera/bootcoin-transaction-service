package com.nttdata.bootcointransactionservice.infrestructure.rest.handler;

import com.nttdata.bootcointransactionservice.application.operations.BootcoinTransactionOperations;
import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import com.nttdata.bootcointransactionservice.domain.Tasa;
import com.nttdata.bootcointransactionservice.infrestructure.model.dao.BootcoinTransactionDao;
import com.nttdata.bootcointransactionservice.infrestructure.producer.BootcoinTransactionProducer;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Component
public class BootcoinTransactionHandler {

    @Autowired
    private BootcoinTransactionOperations bootcoinTransactionOperations;

    @Autowired
    private BootcoinTransactionProducer BootcoinTransactionProducer;

    @Autowired
    private Validator validator;

    public Mono<ServerResponse> getall(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(bootcoinTransactionOperations.findAll(), BootcoinTransaction.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getOne(ServerRequest serverRequest) {
        return bootcoinTransactionOperations.findById(serverRequest.pathVariable("id"))
                .flatMap(walletTransaction -> ServerResponse
                        .ok().contentType(APPLICATION_JSON)
                        .body(fromValue(walletTransaction))
                        .switchIfEmpty(ServerResponse.notFound().build())
                );
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<BootcoinTransaction> walletTransactionMono = serverRequest.bodyToMono(BootcoinTransaction.class);
        Tasa tasa = new Tasa();

        return walletTransactionMono.flatMap(walletTransaction -> {
            Errors errors = new BeanPropertyBindingResult(walletTransaction, BootcoinTransaction.class.getName());
            validator.validate(walletTransaction, errors);

            if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(listString -> ServerResponse.badRequest().body(fromValue(listString)));
            } else {

                if (walletTransaction.getCreateAt() == null)
                    walletTransaction.setCreateAt(LocalDateTime.now());
                walletTransaction.setState(BootcoinTransaction.State.PENDING);
                log.info("Sending message...");
                BootcoinTransactionProducer.producer(walletTransaction);
                log.info("Sent Message!");

                return ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(walletTransaction));
            }
        });

    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Mono<BootcoinTransaction> walletMono = serverRequest.bodyToMono(BootcoinTransaction.class);
        String id = serverRequest.pathVariable("id");
        return walletMono.flatMap(wallet -> bootcoinTransactionOperations.update(id, wallet))
                .flatMap(walletCreated -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(walletCreated))
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return bootcoinTransactionOperations.findById(id)
                .flatMap(wallet -> bootcoinTransactionOperations.delete(id).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


}
