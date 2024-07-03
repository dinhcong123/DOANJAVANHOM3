package com.example.doanjava.Controller;

import com.example.doanjava.Service.*;
import com.example.doanjava.models.Luuphim;
import com.example.doanjava.models.Phim;
import com.example.doanjava.models.Tapphim;
import com.example.doanjava.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private PhimService phimService;

    @Autowired
    private TapphimService tapphimService;

    @Autowired
    private QuocgiaService quocGiaService;

    @Autowired
    private TheloaiService theLoaiService;

    @Autowired
    private LuuphimService luuphimService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("phims", phimService.getAllPhims());
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());
        return "user/phims/index";
    }

    @GetMapping("/listphim")
    public String listPhim(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        int pageSize = 4;
        Page<Phim> phimsPage = phimService.getAllPhims(PageRequest.of(page - 1, pageSize));
        if (page > phimsPage.getTotalPages()) {
            return "redirect:/listphim" + "?page=1";
        }
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());
        model.addAttribute("phims", phimsPage);
        return "user/phims/listphim";
    }

    @GetMapping("/phimtheotheloai/{id}")
    public String phimTheoTheloai(@PathVariable("id") Long id,
                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        int pageSize = 4;
        Page<Phim> phimsPage = phimService.getAllPhimsByTheloaiId(id, PageRequest.of(page - 1, pageSize));

        // Nếu không có phim nào thuộc thể loại này
        if (phimsPage.getTotalElements() == 0) {
            redirectAttributes.addFlashAttribute("message", "Không có phim nào thuộc thể loại này.");
            return "redirect:/";
        }

        // Nếu page truyền vào lớn hơn tổng số trang, điều hướng về trang đầu tiên
        if (page > phimsPage.getTotalPages()) {
            return "redirect:/phimtheotheloai/" + id + "?page=1";
        }

        model.addAttribute("phims", phimsPage);
        model.addAttribute("theloaiId", id);
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());
        return "user/phims/phimtheotheloai";
    }

    @GetMapping("/phimtheoquocgia/{id}")
    public String phimTheoQuocgia(@PathVariable("id") Long id,
                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        int pageSize = 4;
        Page<Phim> phimsPage = phimService.getAllPhimsByQuocgiaId(id, PageRequest.of(page - 1, pageSize));

        // Nếu không có phim nào thuộc quốc gia này
        if (phimsPage.getTotalElements() == 0) {
            redirectAttributes.addFlashAttribute("message", "Không có phim nào thuộc quốc gia này.");
            return "redirect:/";
        }

        // Nếu page truyền vào lớn hơn tổng số trang, điều hướng về trang đầu tiên
        if (page > phimsPage.getTotalPages()) {
            return "redirect:/phimtheoquocgia/" + id + "?page=1";
        }

        model.addAttribute("phims", phimsPage);
        model.addAttribute("quocgiaId", id);
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());
        return "user/phims/phimtheoquocgia";
    }

    @GetMapping("/chitietphim/{id}")
    public String chitietphim(@PathVariable("id") Long id,
                              @AuthenticationPrincipal UserDetails currentUser,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        Phim phim = phimService.getPhimById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phim Id:" + id));
        Tapphim tapphim = tapphimService.tapphimdautientheophim(id).orElse(null);
        model.addAttribute("tapphim",tapphim);
        model.addAttribute("demtapphim", tapphimService.demtapphim(id));
        model.addAttribute("phim", phim);
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());

        if (currentUser != null) {
            User user = userService.findByUsername(currentUser.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<Luuphim> luuphim = luuphimService.findByUserAndPhim(user, phim);
            model.addAttribute("luuphim", luuphim.orElse(null));
        } else {
            model.addAttribute("luuphim", null);
        }

        return "user/phims/chitietphim";
    }

    @GetMapping("/xemphim/{phimId}/{tapphimId}")
    public String xemphim(@PathVariable("phimId") Long phimId, @PathVariable("tapphimId") Long tapphimId, Model model) {
        Phim phim = phimService.getPhimById(phimId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phim Id:" + phimId));
        var danhsachtapphim = tapphimService.danhsachtapphim(phimId);
        var tapphim = danhsachtapphim.stream().filter(p -> p.getTapphimId() == tapphimId).findFirst().orElse(null);

        model.addAttribute("phim", phim);
        model.addAttribute("danhsachtapphim", danhsachtapphim);
        model.addAttribute("tapphim", tapphim);
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());

        return "user/phims/xemphim";
    }

    @PostMapping("/luuphim/{phimId}")
    public ResponseEntity<String> luuPhim(@PathVariable("phimId") Long phimId,
                                          @AuthenticationPrincipal UserDetails currentUser) {

        if (currentUser == null) {
            return ResponseEntity.badRequest().body("Bạn cần đăng nhập để lưu phim.");
        }

        User user = userService.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Phim phim = phimService.getPhimById(phimId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phim Id:" + phimId));

        Optional<Luuphim> existingLuuphim = luuphimService.findByUserAndPhim(user, phim);

        if (existingLuuphim.isPresent()) {
            return ResponseEntity.badRequest().body("Phim này đã được lưu trước đó.");
        } else {
            Luuphim luuphim = new Luuphim();
            luuphim.setUser(user);
            luuphim.setPhim(phim);
            luuphimService.save(luuphim);
            return ResponseEntity.ok("Phim đã được lưu thành công.");
        }
    }

    @GetMapping("/danhsachphim")
    public String viewSavedMovies(@AuthenticationPrincipal UserDetails currentUser,
                                  Model model,
                                  @RequestParam(value = "page", defaultValue = "0") int page) {
        if (currentUser == null) {
            return "redirect:/login";
        }

        User user = userService.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Pageable pageable = PageRequest.of(page, 4);
        Page<Luuphim> bangluuphim = luuphimService.findByUser(user, pageable);
        model.addAttribute("bangluuphim", bangluuphim);
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());

        return "user/phims/danhsachphimdaluu";
    }
    @PostMapping("/xoaluuphim/{phimId}")
    public ResponseEntity<String> xoaLuuPhim(@PathVariable("phimId") Long phimId,
                                             @AuthenticationPrincipal UserDetails currentUser) {

        if (currentUser == null) {
            return ResponseEntity.badRequest().body("Bạn cần đăng nhập để xóa phim.");
        }

        User user = userService.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Phim phim = phimService.getPhimById(phimId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phim Id:" + phimId));

        Optional<Luuphim> existingLuuphim = luuphimService.findByUserAndPhim(user, phim);

        if (existingLuuphim.isPresent()) {
            luuphimService.deleteByUserAndPhim(user, phim);
            return ResponseEntity.ok("Phim đã được xóa khỏi danh sách lưu.");
        } else {
            return ResponseEntity.badRequest().body("Phim không có trong danh sách lưu.");
        }
    }
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, @RequestParam("page") int page, Model model) {
        Page<Phim> phims = phimService.searchPhimByKeyword(keyword, PageRequest.of(page - 1, 2));
        model.addAttribute("phims", phims);
        model.addAttribute("keyword", keyword);
        model.addAttribute("theloais", theLoaiService.getAllTheloais());
        model.addAttribute("quocgias", quocGiaService.getAllQuocgias());
        return "/user/phims/search_results";
    }

    @GetMapping("/random")
    public String getRandomMovie(RedirectAttributes redirectAttributes) {
        // Lấy danh sách tất cả phim
        List<Phim> allMovies = phimService.getAllPhims();

        // Kiểm tra nếu không có phim nào trong cơ sở dữ liệu
        if (allMovies.isEmpty()) {
            return "redirect:/"; // Chuyển hướng về trang chủ nếu không có phim nào
        }

        // Chọn ngẫu nhiên một phim
        Random random = new Random();
        Phim randomMovie = allMovies.get(random.nextInt(allMovies.size()));

        // Chuyển hướng đến trang chi tiết phim với ID của phim ngẫu nhiên
        return "redirect:/chitietphim/" + randomMovie.getPhimId();
    }
}
