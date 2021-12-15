package com.nttdata.bootcointransactionservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasa {
    private String id;
    private  String type; //VENTA COMPRA
    private String taza;
}
