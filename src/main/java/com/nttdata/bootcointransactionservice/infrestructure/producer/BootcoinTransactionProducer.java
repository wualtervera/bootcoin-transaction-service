package com.nttdata.bootcointransactionservice.infrestructure.producer;

import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import com.nttdata.bootcointransactionservice.domain.Tasa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BootcoinTransactionProducer {

    @Value("${custom.kafka.topic-name-transaction}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, BootcoinTransaction> walletTransactionKafkaTemplate;

    public void producer(BootcoinTransaction bootcoinTransactionDto) {
        Tasa tasa = new Tasa();
        /*//Compra
        if (bootcoinTransactionDto.getTypeOperation() == 1){
            bootcoinTransactionDto.setAmountCoins(bootcoinTransactionDto.getAmountCoins());
        }
        //Venta
        if (bootcoinTransactionDto.getTypeOperation() == 2){
            bootcoinTransactionDto.setAmountCoins(bootcoinTransactionDto.getAmountCoins());
        }*/

        log.info("Sending message...");
        Message<BootcoinTransaction> message = MessageBuilder
                .withPayload(bootcoinTransactionDto)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        this.walletTransactionKafkaTemplate.send(message);
        log.info("Sent message!");
    }


}