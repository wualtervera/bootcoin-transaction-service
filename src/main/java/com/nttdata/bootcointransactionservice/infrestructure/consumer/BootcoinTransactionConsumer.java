package com.nttdata.bootcointransactionservice.infrestructure.consumer;

import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import com.nttdata.bootcointransactionservice.infrestructure.rest.service.BootcoinTransactionCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BootcoinTransactionConsumer {

    @Autowired
    private BootcoinTransactionCrudService bootcoinTransactionCrudService;

    @KafkaListener(
            topics = "${custom.kafka.topic-name-bootcoin}",
            groupId = "${custom.kafka.group-id}",
            containerFactory = "walletConcurrentKafkaListenerContainerFactory")
    public void consumer(@Payload BootcoinTransaction bootcoinTransaction, @Headers MessageHeaders headers) {
        if (bootcoinTransaction.getState().name().equals("ACCEPTED")) {

            bootcoinTransactionCrudService.save(bootcoinTransaction).subscribe(bootcoinTran -> {
                log.info("SUCCESSFUL Trnsaction! [{}]", bootcoinTran);
                bootcoinTransaction.setState(BootcoinTransaction.State.SUCCESSFUL);
                bootcoinTransactionCrudService.update(bootcoinTran.getId(), bootcoinTran);
            });
        } else {
            log.error("REJECTED Transaction [{}]", bootcoinTransaction);
        }
    }


}