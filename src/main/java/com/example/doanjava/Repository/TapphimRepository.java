package com.example.doanjava.Repository;

import com.example.doanjava.models.Tapphim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TapphimRepository extends JpaRepository<Tapphim, Long> {
    @Query("SELECT p FROM Tapphim p WHERE p.phim.phimId = :phimId")
    List<Tapphim> tapphimtheophim(long phimId);
}