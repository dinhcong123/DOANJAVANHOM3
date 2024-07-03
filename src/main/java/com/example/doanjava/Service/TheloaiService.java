package com.example.doanjava.Service;

import com.example.doanjava.Repository.PhimRepository;
import com.example.doanjava.Repository.TheloaiRepository;
import com.example.doanjava.models.Theloai;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TheloaiService {

    private final TheloaiRepository theloaiRepository;
    private final PhimRepository phimRepository;

    public String getTenTheLoai(Long id) {
        return theloaiRepository.findById(id).map(Theloai::getTentheloai).orElse("Không xác định");
    }

    public List<Theloai> getAllTheloais() {
        return theloaiRepository.findAll();
    }

    public Optional<Theloai> getTheloaiById(Long id) {
        return theloaiRepository.findById(id);
    }

    public Theloai addTheloai(Theloai theloai) {
        return theloaiRepository.save(theloai);
    }

    public void updateTheloai(@NotNull Theloai theloai) {
        Theloai existingTheloai = theloaiRepository.findById(theloai.getTheloaiId())
                .orElseThrow(() -> new IllegalStateException("Theloai with ID " +
                        theloai.getTheloaiId() + " does not exist."));
        existingTheloai.setTentheloai(theloai.getTentheloai());
        existingTheloai.setDescription(theloai.getDescription());
        theloaiRepository.save(existingTheloai);
    }

    public void deleteTheloaiById(Long id) {
        if (!theloaiRepository.existsById(id)) {
            throw new IllegalStateException("Theloai with ID " + id + " does not exist.");
        }
        theloaiRepository.deleteById(id);
    }
}
