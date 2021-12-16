package com.nttdata.bootcointransactionservice.infrestructure.router;

import com.nttdata.bootcointransactionservice.infrestructure.rest.handler.BootcoinTransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {
    String uri = "api/v1/bootcoinTransaction";

    @Bean
    public RouterFunction<ServerResponse> routes(BootcoinTransactionHandler bootcoinTransactionHandler) {
        return route(GET(uri), bootcoinTransactionHandler::getall)
                .andRoute(GET(uri.concat("/{id}")), bootcoinTransactionHandler::getOne)
                .andRoute(POST(uri), bootcoinTransactionHandler::save)
                .andRoute(PUT(uri.concat("/{id}")), bootcoinTransactionHandler::update)
                .andRoute(DELETE(uri.concat("/{id}")), bootcoinTransactionHandler::delete);
    }


}
