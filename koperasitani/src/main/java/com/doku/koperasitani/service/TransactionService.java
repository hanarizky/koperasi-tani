package com.doku.koperasitani.service;

import com.doku.koperasitani.dto.MemberRest;
import com.doku.koperasitani.dto.ProductRest;
import com.doku.koperasitani.dto.TransactionRest;
import com.doku.koperasitani.exception.MemberNotFoundException;
import com.doku.koperasitani.exception.ProductNotFoundException;
import com.doku.koperasitani.model.TransactionDetailsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    TransactionRest returnValue;
    HashMap<Integer,TransactionRest> transactions;
    int nomorTransaksi;

    public TransactionRest createTransaction(TransactionDetailsRequest transactionDetails) {
        returnValue = new TransactionRest();
        returnValue.setId(transactionDetails.getId());
        int idAnggota = returnValue.getId();
        returnValue.setIdProduk(transactionDetails.getIdProduk());
        int idProduk = returnValue.getIdProduk();
        returnValue.setQty(transactionDetails.getQty());
        int qtyBeli = returnValue.getQty();

        MemberRest memberRest = memberService.getMemberById(idAnggota);

        if (memberRest == null){
            throw new MemberNotFoundException("ID anggota salah/tidak ada!");
        } else {

            if (transactions == null) {
                transactions = new HashMap<>();
                nomorTransaksi = 1;
            } else {
                nomorTransaksi += 1;
            }
            returnValue.setNomorTransaksi(nomorTransaksi);
            returnValue.setJumlahSimpanan(memberRest.getJumlahSimpanan());
        }
        ProductRest productRest = productService.getProductbyId(idProduk);

        if (productRest == null) {
            throw new ProductNotFoundException("Nama produk salah/tidak tersedia");
        } else {
            returnValue.setBeliProduk(productRest.getNamaProduk());
            returnValue.setHargaProduk(productRest.getHargaProduk());
            int qtyProduk = productRest.getQtyProduk();
            Double hargaProduk = productRest.getHargaProduk();

                if (qtyProduk >= qtyBeli) {
                    Double total = hargaProduk * qtyBeli + (hargaProduk * qtyBeli * 0.1);
                    Double simpanan = returnValue.getJumlahSimpanan();
                    returnValue.setTotal(total);
                    Double sisaSimpanan;

                    if (simpanan >= total) {
                        sisaSimpanan = simpanan - total;
                    } else {
                        throw new MemberNotFoundException("Simpanan anggota kurang!");
                    }

                    returnValue.setSisaSimpanan(sisaSimpanan);
                    memberRest.setJumlahSimpanan(returnValue.getSisaSimpanan());
                    qtyProduk = qtyProduk - qtyBeli;
                    productRest.setQtyProduk(qtyProduk);
                } else {
                    throw new ProductNotFoundException("Stok produk kurang!");
                }
        }
        transactions.put(nomorTransaksi,returnValue);
        return returnValue;
    }

    public Collection<TransactionRest> getTransactionAll(){
        return transactions.values();
    }

}