package com.nttdata.bootcointransactionservice.infrestructure.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("typeTransaction")
public class TasaDao {
    @Id
    private String id;
    @NonNull
    private  String type;
    @NonNull
    private String taza;
}
