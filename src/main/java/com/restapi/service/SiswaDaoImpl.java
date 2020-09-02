package com.restapi.service;

import com.restapi.entity.Siswa;
import com.restapi.repository.SiswaRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SiswaDaoImpl implements SiswaDao {

    @Autowired
    private SiswaRepo siswaRepo;

    @Override
    public List<Siswa> getAllSiswa() throws Exception {
        List<Siswa> listSiswa = new ArrayList<Siswa>();
        try {
            listSiswa = siswaRepo.findAll();
            if (listSiswa.isEmpty()) {
                throw new Exception("Siswa List Empty");
            }
        } catch (Exception e) {
            throw e;
        }
        return listSiswa;
    }

    @Override
    public Siswa addSiswa(Siswa siswa) throws Exception {
        try {
            Optional<Siswa> exist = siswaRepo.findById(siswa.getId());
            if (exist.isPresent()) {
                throw new Exception("Data Already Exist");
            }
        } catch (Exception e) {
            throw e;
        }
        return siswaRepo.save(siswa);
    }

    @Override
    public Siswa updateSiswa(String id, Siswa updateSiswa) throws Exception {
        Optional<Siswa> siswaOpt;
        Siswa siswa = new Siswa();
        try {
            siswaOpt = siswaRepo.findById(id);
            if (!siswaOpt.isPresent()) {
                throw new Exception("No Siswa Found By ID " + id);
            }
            siswa = siswaOpt.get();
            siswa.setNama(updateSiswa.getNama());
            siswa.setNpm(updateSiswa.getNpm());
        } catch (Exception e) {
            throw e;
        }
        return siswaRepo.save(siswa);
    }

    @Override
    public String deleteSiswaById(String id) throws Exception {
        Optional<Siswa> siswaOpt;
        String response = "Siswa Deleted";
        try {
            siswaOpt = siswaRepo.findById(id);
            if (!siswaOpt.isPresent()) {
                throw new Exception("No Student Found By ID " + id);
            }
            siswaRepo.delete(siswaOpt.get());
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public Siswa getSiswaById(String id) throws Exception {
        Optional<Siswa> siswaOpt;
        Siswa siswa = new Siswa();
        try {
            siswaOpt = siswaRepo.findById(id);
            if (!siswaOpt.isPresent()) {
                throw new Exception("No Student Found by ID " + id);

            }
            siswa = siswaOpt.get();
        } catch (Exception e) {
            throw e;
        }
        return siswa;
    }
}
