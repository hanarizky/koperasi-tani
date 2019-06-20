package com.doku.koperasitani.dto;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class TransactionRest {

    private int nomorTransaksi;
    private int id;
    private int idProduk;
    private Double jumlahSimpanan;
    private String beliProduk;
    private Double hargaProduk;
    private int qty;
    private String ppn = "10 %";
    private Double total;
    private Double sisaSimpanan;

}
