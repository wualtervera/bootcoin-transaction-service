package com.nttdata.bootcointransactionservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasa {
    private String id;
    private  double tasaCompra = 50;
    private double tasaVenta = 80;
    private LocalDateTime createAt;
}
