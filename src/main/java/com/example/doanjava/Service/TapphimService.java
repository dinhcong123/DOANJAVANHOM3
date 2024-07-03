package com.example.doanjava.Service;

import com.example.doanjava.Repository.TapphimRepository;
import com.example.doanjava.models.Tapphim;
import org.springframework.data.domain.Sort;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
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
@Transactional
public class TapphimService {

    private final TapphimRepository tapphimRepository;

    public List<Tapphim> getAllTapphim() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tapphimId");
        return tapphimRepository.findAll(sort);
    }

    public Optional<Tapphim> tapphimdautientheophim(long phimId)
    {
        return tapphimRepository.tapphimtheophim(phimId).stream().findFirst();
    }
    public List<Tapphim> danhsachtapphim(long phimId)
    {
        return tapphimRepository.tapphimtheophim(phimId).stream().toList();
    }
    public long demtapphim(long phimId)
    {
        return tapphimRepository.tapphimtheophim(phimId).stream().count();
    }
    public Optional<Tapphim> getTapphimById(Long id) {
        return tapphimRepository.findById(id);
    }

    public Tapphim addTapphim(Tapphim tapphim) {
        return tapphimRepository.save(tapphim);
    }

    public Tapphim updateTapphim(Tapphim tapphim) {
        Tapphim existingTapphim = tapphimRepository.findById(tapphim.getTapphimId())
                .orElseThrow(() -> new IllegalStateException("Tapphim with ID " +
                        tapphim.getTapphimId() + " does not exist."));
        existingTapphim.setTapphimupdate(tapphim.getTapphimupdate());
        existingTapphim.setTapphimthu(tapphim.getTapphimthu());
        existingTapphim.setPhim(tapphim.getPhim());
        return tapphimRepository.save(existingTapphim);
    }

    public void updatevideo(Tapphim tapphim, MultipartFile movietapphim) {
        if (!movietapphim.isEmpty()) {
            try {
                Path dirVideos = Paths.get("static/movies");
                if (!Files.exists(dirVideos)) {
                    Files.createDirectories(dirVideos);
                }

                // Tên file mới là UUID + tên gốc của file
                String newFileName = generateFileName(movietapphim);

                // Đường dẫn đến file trong thư mục lưu trữ
                Path pathFileUpload = dirVideos.resolve(newFileName);

                // Kiểm tra xem file đã tồn tại hay chưa
                if (!Files.exists(pathFileUpload)) {
                    // Nếu chưa tồn tại, sao chép file vào thư mục lưu trữ
                    Files.copy(movietapphim.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                }

                // Lấy đường dẫn video cũ để xoá
                String oldFilePath = tapphim.getTapphimupdate();

                // Nếu đường dẫn cũ khác đường dẫn mới, tiến hành xoá đường dẫn cũ
                if (oldFilePath != null && !oldFilePath.equals(newFileName)) {
                    Path oldVideoPath = dirVideos.resolve(oldFilePath);
                    Files.deleteIfExists(oldVideoPath);
                }

                // Cập nhật đường dẫn video mới cho tập phim
                tapphim.setTapphimupdate(newFileName);
            } catch (IOException e) {
                e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp
            }
        }
    }
    private String generateFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String newFileName = fileNameWithoutExtension + "_" + UUID.randomUUID().toString() + fileExtension;
        return newFileName;
    }

    public void deleteVideo(Tapphim tapphim) {
        if (tapphim.getTapphimupdate() != null) {
            try {
                Path videoPath = Paths.get("static/movies").resolve(tapphim.getTapphimupdate());
                Files.deleteIfExists(videoPath);
            } catch (IOException e) {
                e.printStackTrace(); // Xử lý ngoại lệ một cách phù hợp
            }
        }
    }

    public void deleteTapphimById(Long id) {
        Tapphim tapphim = tapphimRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Tapphim with ID " + id + " does not exist."));

        // Xoá video từ thư mục lưu trữ trước khi xoá tập phim
        deleteVideo(tapphim);

        // Xoá tập phim từ cơ sở dữ liệu
        tapphimRepository.deleteById(id);
    }
}