package com.nttdata.bootcointransactionservice.infrestructure.rest.handler;

import com.nttdata.bootcointransactionservice.application.operations.TasaOperations;
import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import com.nttdata.bootcointransactionservice.domain.Tasa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
@Slf4j
@Component
public class TasaHandler {

    @Autowired
    private TasaOperations tasaOperations;

    public Mono<ServerResponse> getall(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(tasaOperations.findAll(), BootcoinTransaction.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getOne(ServerRequest serverRequest) {
        return tasaOperations.findById(serverRequest.pathVariable("id"))
                .flatMap(walletTransaction -> ServerResponse
                        .ok().contentType(APPLICATION_JSON)
                        .body(fromValue(walletTransaction))
                        .switchIfEmpty(ServerResponse.notFound().build())
                );
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Tasa> tasaMono = serverRequest.bodyToMono(Tasa.class);
        return tasaMono.flatMap(tasa -> tasaOperations.save(tasa))
                .flatMap(tasadb -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(tasadb))
                ).switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        Mono<Tasa> tasaMono = serverRequest.bodyToMono(Tasa.class);
        String id = serverRequest.pathVariable("id");
        return tasaMono.flatMap(tasa -> tasaOperations.update(id, tasa))
                .flatMap(tasadb -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(APPLICATION_JSON)
                        .body(fromValue(tasadb))
                ).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return tasaOperations.findById(id)
                .flatMap(tasa -> tasaOperations.delete(id).then(ServerResponse.noContent().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
