package com.restapi.repository;

import com.restapi.entity.Siswa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SiswaRepo extends JpaRepository<Siswa, String> {

    @Query(value = "SELECT u FROM Siswa u", countQuery = "SELECT COUNT(u) FROM Siswa u")
    Page<Siswa> findPage(Pageable pageable);
}
