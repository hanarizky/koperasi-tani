package com.doku.koperasitani.controller;

import com.doku.koperasitani.model.MemberDetailsRequest;
import com.doku.koperasitani.exception.MemberNotFoundException;
import com.doku.koperasitani.dto.MemberRest;
import com.doku.koperasitani.service.MemberService;

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
@RequestMapping("/anggota")         // endpoints: http://localhost:8080/anggota
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "Tambah anggota")
    @PostMapping
    public ResponseEntity createMember(@Valid @RequestBody MemberDetailsRequest memberDetails) {
        MemberRest returnValue = memberService.createMember(memberDetails);
        return new ResponseEntity(returnValue, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Tampilkan semua anggota")
    @GetMapping
    public ResponseEntity getMemberAll(){
        Collection returnValue = memberService.getMemberAll();
            return new ResponseEntity(returnValue,HttpStatus.OK);
    }

    @ApiOperation(value = "Tampilkan anggota berdasarkan ID")
    @GetMapping(value = "/{idAnggota}")
    public ResponseEntity getMemberbyId(@PathVariable Integer idAnggota){
        MemberRest returnValue = memberService.getMemberById(idAnggota);

        if (returnValue != null) {
            return new ResponseEntity(returnValue,HttpStatus.OK);
        } else {
            throw new MemberNotFoundException("ID anggota salah/tidak ada!");
        }
    }

    @ApiOperation(value = "Ubah alamat anggota")
    @PatchMapping(value = "{idAnggota}/alamat")
    public ResponseEntity addressUpdate(@PathVariable Integer idAnggota, @Valid @RequestBody MemberRest memberRest){
        MemberRest returnValue = memberService.addressUpdate(idAnggota,memberRest);
        return new ResponseEntity(returnValue,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Ubah jumlah simpanan anggota")
    @PatchMapping(value = "{idAnggota}/simpanan")
    public ResponseEntity savingUpdate(@PathVariable Integer idAnggota, @Valid @RequestBody MemberRest memberRest){
        MemberRest returnValue = memberService.savingUpdate(idAnggota,memberRest);
        return new ResponseEntity(returnValue,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Hapus anggota")
    @DeleteMapping(value = "/{idAnggota}")
    public ResponseEntity deleteMember(@PathVariable Integer idAnggota) {
        MemberRest returnValue = memberService.deleteMember(idAnggota);

        if (returnValue != null) {
            return new ResponseEntity(returnValue,HttpStatus.OK);
        } else {
            throw new MemberNotFoundException("ID anggota salah/tidak ada!");
        }
    }

}