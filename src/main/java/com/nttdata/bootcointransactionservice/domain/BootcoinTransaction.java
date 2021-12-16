package com.nttdata.bootcointransactionservice.domain;

import com.nttdata.bootcointransactionservice.infrestructure.model.dao.BootcoinTransactionDao;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BootcoinTransaction {

    private static final long serialVersionUID = 1L;

    private String id;
    private String phoneBuyer; //Celular del comprador
    private String phoneSeller; //Celular del vendedor
    private Double amountSoles;
    private Double amountCoins;
    private TypePayment typePayment;
    private State state;
    private LocalDateTime createAt;

    public enum State {
        PENDING,
        ACCEPTED,
        REJECTED,
        SUCCESSFUL
    }
    public enum TypePayment {
        YANKY,
        TRANSFERENCIA
    }

}
