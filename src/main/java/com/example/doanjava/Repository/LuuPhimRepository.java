package com.example.doanjava.Repository;

import com.example.doanjava.models.Luuphim;
import com.example.doanjava.models.Phim;
import com.example.doanjava.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LuuPhimRepository extends JpaRepository<Luuphim, Long> {
    Optional<Luuphim> findByUserAndPhim(User user, Phim phim);

    Page<Luuphim> findByUser(User user, Pageable pageable);

    void deleteByUserAndPhim(User user, Phim phim);
}
