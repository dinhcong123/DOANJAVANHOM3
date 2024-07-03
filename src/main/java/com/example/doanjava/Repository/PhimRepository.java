package com.example.doanjava.Repository;

import com.example.doanjava.models.Phim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PhimRepository extends JpaRepository<Phim, Long> {
    @Query("SELECT p FROM Phim p WHERE p.theloai.theloaiId = :theloaiId")
    Page<Phim> findByTheloaiId(Long theloaiId, Pageable pageable);
    @Query("SELECT p FROM Phim p WHERE p.quocgia.quocgiaId = :quocgiaId")
    Page<Phim> findByQuocgiaId(Long quocgiaId, Pageable pageable);

    Page<Phim> findByTenphimContainingIgnoreCase(String tenphim, PageRequest pageRequest);
}
