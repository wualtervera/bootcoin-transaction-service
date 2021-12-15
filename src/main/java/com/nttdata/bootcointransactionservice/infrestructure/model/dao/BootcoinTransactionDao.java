package com.nttdata.bootcointransactionservice.infrestructure.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("bootcointTransaction")
public class BootcoinTransactionDao {
    @Id
    private String id;
    private int TypeOperation;  //(1)COMPRA, (2)VENTA
    private String idComprador;
    private String idVendedor;
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
