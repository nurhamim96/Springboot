package com.restapi.service;

import com.restapi.entity.Siswa;

import java.util.List;

public interface SiswaDao {

    List<Siswa> getAllSiswa() throws Exception;
    Siswa addSiswa (Siswa siswa) throws Exception;
    Siswa updateSiswa (String id, Siswa updateSiswa) throws Exception;
    String deleteSiswaById (String id) throws Exception;
    Siswa getSiswaById (String id) throws Exception;
}
