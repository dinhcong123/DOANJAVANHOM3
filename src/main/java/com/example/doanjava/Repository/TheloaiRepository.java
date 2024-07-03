package com.example.doanjava.Repository;

import com.example.doanjava.models.Theloai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheloaiRepository extends JpaRepository<Theloai, Long> {
}
