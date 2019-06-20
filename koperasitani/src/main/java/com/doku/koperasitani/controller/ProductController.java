package com.doku.koperasitani.controller;

import com.doku.koperasitani.model.ProductDetailsRequest;
import com.doku.koperasitani.exception.ProductNotFoundException;
import com.doku.koperasitani.dto.ProductRest;
import com.doku.koperasitani.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/produk")
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation("Tambah produk")
    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductDetailsRequest productDetails) {
        ProductRest returnValue = productService.createProduct(productDetails);
        return new ResponseEntity(returnValue, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Tampilkan semua anggota")
    @GetMapping
    public ResponseEntity getProductAll(){
        Collection returnValue = productService.getProductAll();
        return new ResponseEntity(returnValue,HttpStatus.OK);
    }

    @ApiOperation(value = "Tampilkan produk berdasarkan id produk")
    @GetMapping(value = "/{idProduk}")
    public ResponseEntity getProductById(@PathVariable int idProduk){
        ProductRest returnValue = productService.getProductbyId(idProduk);

        if (returnValue != null) {
            return new ResponseEntity(returnValue,HttpStatus.OK);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @ApiOperation(value = "Ubah harga produk")
    @PatchMapping(value = "{idProduk}/harga")
    public ResponseEntity priceUpdate(@PathVariable int idProduk, @Valid @RequestBody ProductRest productRest){
        ProductRest returnValue = productService.priceUpdate(idProduk,productRest);

        if (returnValue != null) {
            return new ResponseEntity(returnValue,HttpStatus.CREATED);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @ApiOperation(value = "Hapus Produk")
    @DeleteMapping(value = "/{idProduk}")
    public ResponseEntity deleteProduct(@PathVariable int idProduk) {
        ProductRest returnValue = productService.deleteProduct(idProduk);

        if (returnValue != null) {
            return new ResponseEntity(returnValue,HttpStatus.OK);
        } else {
            throw new ProductNotFoundException();
        }
    }

}