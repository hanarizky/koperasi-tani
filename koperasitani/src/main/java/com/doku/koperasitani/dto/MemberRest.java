package com.doku.koperasitani.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Setter @Getter
public class MemberRest {

    private int id;
    private String nama;
    @Size(min = 2, message = "harus lebih dari 2 karakter")
    private String alamat;
    private Double jumlahSimpanan;

}
