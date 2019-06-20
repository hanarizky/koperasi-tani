package com.doku.koperasitani.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter @Getter @NotNull
public class ProductDetailsRequest {

    private int idProduk;
    @Size(min = 2, message = "produk harus lebih dari 2 karakter")
    private String namaProduk;
    private Double hargaProduk;
    private int qtyProduk;

}
