package com.nttdata.bootcointransactionservice.infrestructure.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDto {

    private static final long serialVersionUID = 1L;

    private String id;
    private String originNumberPhone;
    private String destinyNumberPhone;
    private Double amount;
    private State state;
    private LocalDateTime createAt;

    public enum State {
        PENDING,
        REJECTED,
        SUCCESSFUL
    }


}
