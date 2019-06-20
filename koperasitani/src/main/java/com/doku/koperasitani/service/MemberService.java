package com.doku.koperasitani.service;

import com.doku.koperasitani.exception.MemberNotFoundException;
import com.doku.koperasitani.model.MemberDetailsRequest;
import com.doku.koperasitani.dto.MemberRest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class MemberService {

    MemberRest returnValue;
    HashMap<Integer,MemberRest> members;

    public MemberService(){
        MemberRest member1 = new MemberRest();
        member1.setId(1);
        int idAnggota = member1.getId();
        member1.setNama("Asih");
        member1.setAlamat("Sukabumi");
        member1.setJumlahSimpanan(400000.00);

        if(members == null){
            members = new HashMap<>();
        }
        members.put(idAnggota,member1);

        MemberRest member2 = new MemberRest();
        member2.setId(2);
        int idAnggota2 = member2.getId();
        member2.setNama("Joko");
        member2.setAlamat("Bekasi");
        member2.setJumlahSimpanan(200000.00);
        members.put(idAnggota2,member2);

    }

    public MemberRest createMember(MemberDetailsRequest memberDetails) {
        returnValue = new MemberRest();
        returnValue.setId(memberDetails.getId());
        int idAnggota = returnValue.getId();
        returnValue.setNama(memberDetails.getNama());
        returnValue.setAlamat(memberDetails.getAlamat());
        returnValue.setJumlahSimpanan(memberDetails.getJumlahSimpanan());

        members.put(idAnggota,returnValue);
        return returnValue;
    }

    public Collection<MemberRest> getMemberAll(){
        return members.values();
    }

    public MemberRest getMemberById(Integer idAnggota){
        return members.get(idAnggota);
    }

    public MemberRest addressUpdate (Integer idAnggota, MemberRest memberRest){

        if (members.containsKey(idAnggota)) {
            MemberRest updateAlamat = members.get(idAnggota);
            updateAlamat.setAlamat(memberRest.getAlamat());
            members.put(idAnggota,updateAlamat);
        } else {
            throw new MemberNotFoundException("ID anggota salah/tidak ada!");
        }
        return members.get(idAnggota);
    }

    public MemberRest savingUpdate(Integer idAnggota, MemberRest memberRest){

        if (members.containsKey(idAnggota)) {
            MemberRest updateSimpanan = members.get(idAnggota);
            updateSimpanan.setJumlahSimpanan(memberRest.getJumlahSimpanan());
            members.put(idAnggota,updateSimpanan);
        } else {
            throw new MemberNotFoundException("ID anggota salah/tidak ada!");
        }
        return members.get(idAnggota);
    }

    public MemberRest deleteMember(Integer idAnggota) {
        return members.remove(idAnggota);
    }

}