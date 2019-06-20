package com.doku.koperasitani.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter @Getter @NotNull
public class TransactionDetailsRequest {

    private int idProduk;
    private int id;
    private int qty;
}
