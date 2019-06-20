package com.doku.koperasitani.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(){
        super("Nama produk salah/tidak tersedia");
    }
}
