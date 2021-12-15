package com.nttdata.bootcointransactionservice.infrestructure.router;

import com.nttdata.bootcointransactionservice.infrestructure.rest.handler.TasaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//@Configuration
public class RouterTasaHandler {
    String uri2 = "api/v1/tasa";
    @Bean
    public RouterFunction<ServerResponse> routes2(TasaHandler tasaHandler) {
        return route(GET(uri2), tasaHandler::getall)
                .andRoute(GET(uri2.concat("/{id}")), tasaHandler::getOne)
                .andRoute(POST(uri2), tasaHandler::save)
                .andRoute(PUT(uri2.concat("/{id}")), tasaHandler::update)
                .andRoute(DELETE(uri2.concat("/{id}")), tasaHandler::delete);


    }
}
