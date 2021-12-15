package com.nttdata.bootcointransactionservice.infrestructure.spring.config;

import com.nttdata.bootcointransactionservice.application.model.BootcoinTransactionRepository;
import com.nttdata.bootcointransactionservice.infrestructure.rest.service.BootcoinTransactionCrudService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public BootcoinTransactionRepository bootcoinTransactionRepository(){
        return new BootcoinTransactionCrudService();
    }
}
