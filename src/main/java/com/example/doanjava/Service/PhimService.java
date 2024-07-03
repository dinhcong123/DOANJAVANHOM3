package com.example.doanjava.Service;

import com.example.doanjava.Repository.PhimRepository;
import com.example.doanjava.Repository.TapphimRepository;
import com.example.doanjava.models.Phim;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhimService {

    private final PhimRepository phimRepository;

    private final TapphimRepository tapphimRepository;

    public List<Phim> getAllPhims() {
        Sort sort = Sort.by(Sort.Direction.DESC, "year");
        return phimRepository.findAll(sort);
    }

    public Page<Phim> getAllPhims(Pageable pageable) {
        Pageable sortedByYearDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "year"));
        return phimRepository.findAll(sortedByYearDesc);
    }

    public Optional<Phim> getPhimById(Long id) {
        return phimRepository.findById(id);
    }

    public Page<Phim> getAllPhimsByTheloaiId(Long theloaiId, Pageable pageable) {
        Pageable sortedByYearDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "year"));
        return phimRepository.findByTheloaiId(theloaiId, sortedByYearDesc);
    }

    public Page<Phim> getAllPhimsByQuocgiaId(Long quocgiaId, Pageable pageable) {
        Pageable sortedByYearDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "year"));
        return phimRepository.findByQuocgiaId(quocgiaId, sortedByYearDesc);
    }

    public Page<Phim> searchPhimByKeyword(String keyword, PageRequest pageRequest) {
        PageRequest sortedByYearDesc = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), Sort.by(Sort.Direction.DESC, "year"));
        return phimRepository.findByTenphimContainingIgnoreCase(keyword, sortedByYearDesc);
    }

    public Phim addPhim(Phim phim) {
        return phimRepository.save(phim);
    }

    public Phim updatePhim(Phim phim) {
        Phim existingPhim = phimRepository.findById(phim.getPhimId())
                .orElseThrow(() -> new IllegalStateException("Phim with ID " +
                        phim.getPhimId() + " does not exist."));
        existingPhim.setTenphim(phim.getTenphim());
        existingPhim.setDescription(phim.getDescription());
        existingPhim.setImageurl(phim.getImageurl());
        existingPhim.setYear(phim.getYear());
        existingPhim.setSotapphimhientai(phim.getSotapphimhientai());
        existingPhim.setSotapphim(phim.getSotapphim());
        existingPhim.setTheloai(phim.getTheloai());
        existingPhim.setQuocgia(phim.getQuocgia());
        return phimRepository.save(existingPhim);
    }

    public void updateImage(Phim phim, MultipartFile imagePhim) {
        if (!imagePhim.isEmpty()) {
            try {
                Path dirImages = Paths.get("static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }

                String newFileName = generateFileName(imagePhim);
                Path pathFileUpload = dirImages.resolve(newFileName);

                if (!Files.exists(pathFileUpload)) {
                    Files.copy(imagePhim.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                }

                deleteOldImageIfNeeded(phim, newFileName);
                phim.setImageurl(newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteOldImageIfNeeded(Phim phim, String newFileName) {
        if (phim.getImageurl() != null && !phim.getImageurl().equals(newFileName)) {
            try {
                Path oldImagePath = Paths.get("static/images").resolve(phim.getImageurl());
                Files.deleteIfExists(oldImagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        return fileNameWithoutExtension + "_" + UUID.randomUUID().toString() + fileExtension;
    }

    @Transactional
    public boolean deletePhimById(Long id) {
        Optional<Phim> phimOptional = phimRepository.findById(id);
        if (!phimOptional.isPresent()) {
            throw new IllegalStateException("Phim with ID " + id + " does not exist.");
        }

        Phim phim = phimOptional.get();
        try {
            phimRepository.deleteById(id);

            // Kiểm tra xem phim đã bị xóa chưa
            boolean isDeleted = !phimRepository.existsById(id);
            if (isDeleted && phim.getImageurl() != null) {
                Path imagePath = Paths.get("static/images").resolve(phim.getImageurl());
                Files.deleteIfExists(imagePath);
            }
            return isDeleted;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Cannot delete phim with ID " + id + " due to integrity violation.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error occurred while deleting the image.");
        }
    }

}