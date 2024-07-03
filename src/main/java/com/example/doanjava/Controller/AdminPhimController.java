package com.example.doanjava.Controller;

import com.example.doanjava.Service.PhimService;
import com.example.doanjava.Service.QuocgiaService;
import com.example.doanjava.Service.TheloaiService;
import com.example.doanjava.models.Phim;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/phims")
public class AdminPhimController {

    @Autowired
    private PhimService phimService;

    @Autowired
    private TheloaiService theloaiService;

    @Autowired
    private QuocgiaService quocgiaService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("phims", phimService.getAllPhims());
        return "/admin/phims/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("phims", new Phim());
        model.addAttribute("theloais", theloaiService.getAllTheloais());
        model.addAttribute("quocgias", quocgiaService.getAllQuocgias());
        return "/admin/phims/create";
    }

    @PostMapping("/create")
    public String addPhim(@Valid Phim phim, @RequestParam MultipartFile imagePhim,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phims", phim);
            return "/admin/phims/create";
        }
        phimService.updateImage(phim, imagePhim);
        phimService.addPhim(phim);
        return "redirect:/admin/phims";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Phim phim = phimService.getPhimById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phim Id:" + id));
        model.addAttribute("phims", phim);
        model.addAttribute("theloais", theloaiService.getAllTheloais());
        model.addAttribute("quocgias", quocgiaService.getAllQuocgias());
        return "/admin/phims/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Valid Phim phim,
                       @RequestParam("imagePhim") MultipartFile imagePhim, BindingResult result, Model model) {
        if (result.hasErrors()) {
            phim.setPhimId(id);
            return "/admin/phims/edit";
        }
        phimService.updateImage(phim, imagePhim);
        phimService.updatePhim(phim);
        return "redirect:/admin/phims";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        try {
            boolean isDeleted = phimService.deletePhimById(id);
            if (isDeleted) {
                return "redirect:/admin/phims";
            } else {
                model.addAttribute("errorMessage", "Không thể xoá Phim này vì nó có liên quan đến bảng tập phim.");
                return "/admin/phims/error";
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Không thể xoá Phim này vì nó có liên quan đến bảng tập phim.");
            return "/admin/phims/error";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/admin/phims/error";
        }
    }

}
