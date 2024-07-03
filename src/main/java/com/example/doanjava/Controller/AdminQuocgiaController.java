package com.example.doanjava.Controller;

import com.example.doanjava.Service.QuocgiaService;
import com.example.doanjava.models.Quocgia;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/quocgias")
public class AdminQuocgiaController {

    @Autowired
    private QuocgiaService quocgiaService;

    @GetMapping
    public String index(Model model) {
        List<Quocgia> quocgias = quocgiaService.getAllQuocgias();
        model.addAttribute("quocgias", quocgias);
        return "admin/quocgias/index";
    }

    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("quocgias", new Quocgia());
        return "admin/quocgias/create";
    }

    @PostMapping("/add")
    public String create(@Valid Quocgia quocgia, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/quocgias/create";
        }
        quocgiaService.addQuocgia(quocgia);
        return "redirect:/admin/quocgias";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Quocgia quocgia = quocgiaService.getQuocgiaById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quocgia Id:"
                        + id));
        model.addAttribute("quocgias", quocgia);
        return "admin/quocgias/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @Valid Quocgia quocgia,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            quocgia.setQuocgiaId(id);
            return "admin/quocgias/edit";
        }
        quocgiaService.updateQuocgia(quocgia);
        model.addAttribute("quocgias", quocgiaService.getAllQuocgias());
        return "redirect:/admin/quocgias";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        try {
            quocgiaService.deleteQuocgiaById(id);
            return "redirect:/admin/quocgias";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Không thể xoá quốc gia này vì nó có liên quan đến bảng phim.");
            return "/admin/quocgias/error";
        }
    }
}
