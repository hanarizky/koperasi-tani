package com.doku.koperasitani.service;


import com.doku.koperasitani.model.ProductDetailsRequest;
import com.doku.koperasitani.dto.ProductRest;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class ProductService {

    ProductRest returnValue;
    HashMap<Integer,ProductRest> products;

    public ProductService(){
        ProductRest product1 = new ProductRest();
        product1.setNamaProduk("pupuk");
        product1.setIdProduk(1);
        int idProduk = product1.getIdProduk();
        product1.setHargaProduk(10000.00);
        product1.setQtyProduk(50);

        if(products == null){
            products = new HashMap<>();
        }
        products.put(idProduk,product1);

        ProductRest product2 = new ProductRest();
        product2.setNamaProduk("bibit");
        product2.setIdProduk(2);
        int idProduk2 = product2.getIdProduk();
        product2.setHargaProduk(5000.00);
        product2.setQtyProduk(100);
        products.put(idProduk2,product2);
    }

    public ProductRest createProduct(ProductDetailsRequest productDetails) {
        returnValue = new ProductRest();
        returnValue.setNamaProduk(productDetails.getNamaProduk());
        returnValue.setIdProduk(productDetails.getIdProduk());
        int idProduk = returnValue.getIdProduk();
        returnValue.setHargaProduk(productDetails.getHargaProduk());
        returnValue.setQtyProduk(productDetails.getQtyProduk());

        products.put(idProduk,returnValue);
        return returnValue;
    }

    public Collection<ProductRest> getProductAll(){
        return products.values();
    }

    public ProductRest getProductbyId (int idProduk){
        return products.get(idProduk);
    }

    public ProductRest priceUpdate (int idProduk, ProductRest productRest){
        if (products.containsKey(idProduk)) {
            ProductRest updateHarga = products.get(idProduk);
            updateHarga.setHargaProduk(productRest.getHargaProduk());
            products.put(idProduk,updateHarga);
        }
        return products.get(idProduk);
    }

    public ProductRest deleteProduct (int idProduk) {
        return products.remove(idProduk);
    }
}
