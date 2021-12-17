package com.nttdata.bootcointransactionservice.infrestructure.rest.service;

import com.nttdata.bootcointransactionservice.infrestructure.model.dto.WalletTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WalletWebClient {

    WebClient webClient = WebClient.create("http://localhost:8080/api/v1");

    public Mono<WalletTransactionDto> getWlletTransactionId(String id) {
        return webClient.get()
                .uri("/walletTransaction/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WalletTransactionDto.class);
    }


    public Mono<WalletTransactionDto> saveWalletTransaction(WalletTransactionDto walletTransactionDto) {
        return webClient.post()
                .uri("/walletTransaction")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(walletTransactionDto), WalletTransactionDto.class)
                .retrieve()
                .bodyToMono(WalletTransactionDto.class);
    }

}
