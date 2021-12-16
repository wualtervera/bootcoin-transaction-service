package com.nttdata.bootcointransactionservice.infrestructure.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("tasaTransaction")
public class TasaDao {
    @Id
    private String id;
    @NonNull
    private  double tasaCompra = 50;
    @NonNull
    private double tasaVenta = 80;
    @NonNull
    private LocalDateTime createAt;
}
