package com.doku.koperasitani.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter @Getter @NotNull
public class MemberDetailsRequest {

    private int id;
    @Size(min = 2, message = "harus lebih dari 2 karakter")
    private String nama;
    @Size(min = 2, message = "harus lebih dari 2 karakter")
    private String alamat;
    private Double jumlahSimpanan;

}
