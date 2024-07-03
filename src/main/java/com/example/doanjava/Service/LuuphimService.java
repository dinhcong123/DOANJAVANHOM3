package com.example.doanjava.Service;


import com.example.doanjava.Repository.LuuPhimRepository;
import com.example.doanjava.models.Luuphim;
import com.example.doanjava.models.Phim;
import com.example.doanjava.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LuuphimService {

    private final LuuPhimRepository luuPhimRepository;

    public Optional<Luuphim> findByUserAndPhim(User user, Phim phim) {
        return luuPhimRepository.findByUserAndPhim(user, phim);
    }

    public void save(Luuphim luuphim) {
        luuPhimRepository.save(luuphim);
    }

    public Page<Luuphim> findByUser(User user, Pageable pageable) {
        return luuPhimRepository.findByUser(user, pageable);
    }
    @Transactional
    public void deleteByUserAndPhim(User user, Phim phim) {
        luuPhimRepository.deleteByUserAndPhim(user, phim);
    }
}
