package com.doku.koperasitani;

import com.doku.koperasitani.controller.MemberController;
import com.doku.koperasitani.controller.ProductController;
import com.doku.koperasitani.controller.TransactionController;
import com.doku.koperasitani.dto.MemberRest;
import com.doku.koperasitani.dto.ProductRest;
import com.doku.koperasitani.dto.TransactionRest;
import com.doku.koperasitani.exception.AppExceptionHandler;
import com.doku.koperasitani.exception.MemberNotFoundException;
import com.doku.koperasitani.exception.ProductNotFoundException;
import com.doku.koperasitani.model.MemberDetailsRequest;
import com.doku.koperasitani.model.ProductDetailsRequest;
import com.doku.koperasitani.model.TransactionDetailsRequest;
import com.doku.koperasitani.service.MemberService;
import com.doku.koperasitani.service.ProductService;
import com.doku.koperasitani.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class KoperasitaniApplicationTests {

    @Autowired
    MemberService memberService;

    @Autowired
    ProductService productService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    MemberController memberController;

    @Autowired
    ProductController productController;

    @Autowired
    TransactionController transactionController;

    @Autowired
    AppExceptionHandler appExceptionHandler;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void MemberTest() {

        MemberDetailsRequest member = new MemberDetailsRequest();
        member.setId(3);
        member.setNama("Hana");
        member.setAlamat("Jakarta");
        member.setJumlahSimpanan(100000.00);

        MemberRest returnValue = memberService.createMember(member);

        assertThat(returnValue.getId()).isEqualTo(member.getId());
        assertThat(returnValue.getNama()).isEqualTo(member.getNama());
        assertThat(returnValue.getAlamat()).isEqualTo(member.getAlamat());
        assertThat(returnValue.getJumlahSimpanan()).isEqualTo(member.getJumlahSimpanan());

        ResponseEntity responseEntity = memberController.createMember(member);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void MemberGetTest() {

        MemberTest();
        int userId = 3;

        MemberRest result = memberService.getMemberById(userId);
        assertThat(result.getNama()).isEqualTo("Hana");
        assertThat(result.getAlamat()).isEqualTo("Jakarta");
        assertThat(result.getJumlahSimpanan()).isEqualTo(100000.00);

        ResponseEntity responseEntity = memberController.getMemberbyId(userId);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void MemberGetAllTest() {

        MemberTest();

        Collection result = memberService.getMemberAll();
        assertThat(result.size()).isEqualTo(3);

        ResponseEntity responseEntity = memberController.getMemberAll();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void MemberAddressUpdateTest() {

        MemberTest();
        int id = 3;

        MemberRest create = memberService.getMemberById(id);
        MemberRest updateMember = memberService.addressUpdate(id, create);

        updateMember.setAlamat("Subang");
        String alamat = create.getAlamat();

        assertThat(updateMember.getId()).isEqualTo(id);
        assertThat(updateMember.getAlamat()).isEqualTo(alamat);

        ResponseEntity responseEntity = memberController.addressUpdate(id, updateMember);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void MemberSavingUpdateTest() {

        MemberTest();
        int id = 3;

        MemberRest create = memberService.getMemberById(id);
        MemberRest updateMember = memberService.savingUpdate(id, create);

        updateMember.setJumlahSimpanan(100000.00);
        Double simpanan = create.getJumlahSimpanan();

        assertThat(updateMember.getId()).isEqualTo(id);
        assertThat(updateMember.getJumlahSimpanan()).isEqualTo(simpanan);

        ResponseEntity responseEntity = memberController.savingUpdate(id, updateMember);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);


    }

    @Test
    public void MemberDeleteTest() {

        MemberTest();

        ResponseEntity responseEntity = memberController.deleteMember(1);
        assertThat(memberService.getMemberById(1)).isNull();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void ProductTest() {

        ProductDetailsRequest products = new ProductDetailsRequest();
        products.setIdProduk(3);
        products.setNamaProduk("tanah");
        products.setHargaProduk(20000.00);
        products.setQtyProduk(10);

        ProductRest create = productService.createProduct(products);

        assertThat(create.getNamaProduk()).isEqualTo(products.getNamaProduk());
        assertThat(create.getHargaProduk()).isEqualTo(products.getHargaProduk());
        assertThat(create.getQtyProduk()).isEqualTo(products.getQtyProduk());

        ResponseEntity responseEntity = productController.createProduct(products);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void ProductGetTest() {

        ProductTest();
        int idProduk = 3;

        ProductRest result = productService.getProductbyId(idProduk);
        assertThat(result.getNamaProduk()).isEqualTo("tanah");
        assertThat(result.getIdProduk()).isEqualTo(3);
        assertThat(result.getHargaProduk()).isEqualTo(20000.00);
        assertThat(result.getQtyProduk()).isEqualTo(10);

        ResponseEntity responseEntity = productController.getProductById(idProduk);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);


    }

    @Test
    public void ProductGetAllTest() {

        ProductTest();

        Collection result = productService.getProductAll();
        assertThat(result.size()).isEqualTo(3);

        ResponseEntity responseEntity = productController.getProductAll();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);


    }

    @Test
    public void ProductUpdateTest() {

        ProductTest();
        int idProduk = 2;

        ProductRest product = productService.getProductbyId(idProduk);
        ProductRest updateProduct = productService.priceUpdate(idProduk, product);

        updateProduct.setHargaProduk(10000.00);
        Double harga = updateProduct.getHargaProduk();
        assertThat(updateProduct.getHargaProduk()).isEqualTo(harga);

        ResponseEntity responseEntity = productController.priceUpdate(idProduk, product);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void ProductDeleteTest() {

        ProductTest();

        ResponseEntity responseEntity = productController.deleteProduct(1);

        assertThat(productService.getProductbyId(1)).isNull();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void TransactionTest() {

        MemberTest();
        ProductTest();

        TransactionDetailsRequest transactions = new TransactionDetailsRequest();
        transactions.setId(3);
        transactions.setIdProduk(3);
        transactions.setQty(1);

        MemberRest member = memberService.getMemberById(transactions.getId());
        ProductRest product = productService.getProductbyId(transactions.getIdProduk());
        TransactionRest create = transactionService.createTransaction(transactions);


        assertThat(create.getNomorTransaksi()).isEqualTo(1);
        assertThat(create.getId()).isEqualTo(transactions.getId());
        assertThat(create.getIdProduk()).isEqualTo(transactions.getIdProduk());
        assertThat(create.getBeliProduk()).isEqualTo(product.getNamaProduk());
        assertThat(create.getQty()).isEqualTo(transactions.getQty());
        assertThat(create.getJumlahSimpanan()).isEqualTo(member.getJumlahSimpanan() + create.getTotal());
        assertThat(create.getHargaProduk()).isEqualTo(product.getHargaProduk());
        assertThat(create.getPpn()).isEqualTo("10 %");
        assertThat(create.getTotal()).isEqualTo(product.getHargaProduk() + (product.getHargaProduk() * 0.1));
        assertThat(create.getSisaSimpanan()).isEqualTo(member.getJumlahSimpanan());

        ResponseEntity responseEntity = transactionController.createTransaction(transactions);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test
    public void TransactionGetAll() {

        Collection result = transactionService.getTransactionAll();
        assertThat(result.size()).isEqualTo(2);

        ResponseEntity responseEntity = transactionController.getTransactionAll();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test(expected = MemberNotFoundException.class)
    public void MemberGetNotFoundException() {
        int id = 5;
        ResponseEntity create = memberController.getMemberbyId(id);

    }

    @Test(expected = MemberNotFoundException.class)
    public void MemberDeleteNotFoundException() {
        int id = 5;
        ResponseEntity create = memberController.deleteMember(id);
    }

    @Test(expected = MemberNotFoundException.class)
    public void MemberAddressUpdateNotFoundException() {

        int id = 5;
        MemberRest memberRest = memberService.getMemberById(id);
        ResponseEntity create = memberController.addressUpdate(id, memberRest);
    }

    @Test(expected = MemberNotFoundException.class)
    public void MemberSavingUpdateNotFoundException() {

        int id = 5;
        MemberRest memberRest = memberService.getMemberById(id);
        ResponseEntity create = memberController.savingUpdate(id, memberRest);
    }


    @Test(expected = ProductNotFoundException.class)
    public void ProductGetNotFoundException() {

        int idProduk = 4;
        ResponseEntity create = productController.getProductById(idProduk);
    }

    @Test(expected = ProductNotFoundException.class)
    public void ProductDeleteNotFoundException() {

        int idProduk = 4;
        ResponseEntity create = productController.deleteProduct(idProduk);
    }

    @Test(expected = ProductNotFoundException.class)
    public void ProductUpdateNotFoundException() {

        int idProduk = 4;
        ProductRest productRest = productService.getProductbyId(idProduk);
        ResponseEntity create = productController.priceUpdate(idProduk, productRest);
    }

    @Test(expected = MemberNotFoundException.class)
    public void MemberNotFoundException() {

        TransactionDetailsRequest transactions = new TransactionDetailsRequest();
        transactions.setId(5);
        transactions.setIdProduk(3);
        transactions.setQty(1);

        ResponseEntity create = transactionController.createTransaction(transactions);
    }

    @Test(expected = ProductNotFoundException.class)
    public void ProductNotFoundException() {

        MemberTest();
        ProductTest();

        TransactionDetailsRequest transactions = new TransactionDetailsRequest();
        transactions.setId(3);
        transactions.setIdProduk(4);
        transactions.setQty(11);

        ResponseEntity create = transactionController.createTransaction(transactions);
    }

    @Test(expected = MemberNotFoundException.class)
    public void SavingNotEnoughException() {

        MemberTest();
        ProductTest();

        TransactionDetailsRequest transactions = new TransactionDetailsRequest();
        transactions.setId(3);
        transactions.setIdProduk(3);
        transactions.setQty(6);

        ResponseEntity create = transactionController.createTransaction(transactions);
        exceptionRule.expectMessage("Simpanan anggota kurang!");

    }

    @Test(expected = ProductNotFoundException.class)
    public void StockNotEnoughException() {

        MemberTest();
        ProductTest();

        TransactionDetailsRequest transactions = new TransactionDetailsRequest();
        transactions.setId(3);
        transactions.setIdProduk(3);
        transactions.setQty(11);

        ResponseEntity create = transactionController.createTransaction(transactions);
        exceptionRule.expectMessage("Stok produk kurang!");

    }

    @Test
    public void MainTest() {
        KoperasitaniApplication.main(new String[] {});
    }

}

