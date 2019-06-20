package com.doku.koperasitani.controller;

import com.doku.koperasitani.model.TransactionDetailsRequest;
import com.doku.koperasitani.dto.TransactionRest;
import com.doku.koperasitani.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api("Bina Tani Indonesia")
@RestController
@RequestMapping("/transaksi")         // endpoints: http://localhost:8080/transaksi
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @ApiOperation(value = "Transaksi Tani")
    @PostMapping
    public ResponseEntity createTransaction(@Valid @RequestBody TransactionDetailsRequest transactionDetails) {
        TransactionRest returnValue = transactionService.createTransaction(transactionDetails);
        return new ResponseEntity(returnValue, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Tampilkan semua transaksi")
    @GetMapping
    public ResponseEntity getTransactionAll() {
        Collection returnValue = transactionService.getTransactionAll();
        return new ResponseEntity(returnValue, HttpStatus.OK);
    }

}
