package com.example.doanjava.Service;

import com.example.doanjava.Repository.QuocgiaRepository;
import com.example.doanjava.models.Quocgia;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuocgiaService {

    private final QuocgiaRepository quocgiaRepository;

    public String getTenQuocGia(Long id) {
        return quocgiaRepository.findById(id).map(Quocgia::getTenquocgia).orElse("Không xác định");
    }

    public List<Quocgia> getAllQuocgias() {
        return quocgiaRepository.findAll();
    }

    public Optional<Quocgia> getQuocgiaById(Long id) {
        return quocgiaRepository.findById(id);
    }

    public Quocgia addQuocgia(Quocgia quocgia) {
        return quocgiaRepository.save(quocgia);
    }
    public void updateQuocgia(@NotNull Quocgia quocgia) {
        Quocgia existingQuocgia = quocgiaRepository.findById(quocgia.getQuocgiaId())
                .orElseThrow(() -> new IllegalStateException("Quocgia with ID " +
                        quocgia.getQuocgiaId() + " does not exist."));
        existingQuocgia.setTenquocgia(quocgia.getTenquocgia());
        existingQuocgia.setDescription(quocgia.getDescription());
        quocgiaRepository.save(existingQuocgia);
    }

    public void deleteQuocgiaById(Long id) {
        if (!quocgiaRepository.existsById(id)) {
            throw new IllegalStateException("Quocgia with ID " + id + " does not exist.");
        }
        quocgiaRepository.deleteById(id);
    }
}
