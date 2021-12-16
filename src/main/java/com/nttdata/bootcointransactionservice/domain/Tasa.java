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
    private  double tasaCompra;
    private double tasaVenta;
    private LocalDateTime createAt;
}
