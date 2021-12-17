package com.nttdata.bootcointransactionservice.infrestructure.consumer;

import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import com.nttdata.bootcointransactionservice.infrestructure.model.dto.WalletTransactionDto;
import com.nttdata.bootcointransactionservice.infrestructure.rest.service.BootcoinTransactionCrudService;
import com.nttdata.bootcointransactionservice.infrestructure.rest.service.WalletWebClient;
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
    private WalletWebClient walletWebClient;

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

                //Add wallet-transaction and Update mobile-wallet
                WalletTransactionDto wt = new WalletTransactionDto();
                wt.setOriginNumberPhone(bootcoinTran.getPhoneSeller()); //seller - origen
                wt.setDestinyNumberPhone(bootcoinTran.getPhoneBuyer()); //buyer - destino
                wt.setAmount(bootcoinTran.getAmountSoles());

                walletWebClient.saveWalletTransaction(wt);
                log.info("SUCCESSFUL Wallet Trnsaction! [{}]", bootcoinTran);
            });
        } else {
            log.error("REJECTED Transaction [{}]", bootcoinTransaction);
        }
    }


}