package com.example.doanjava.Controller;

import com.example.doanjava.Service.TheloaiService;
import com.example.doanjava.models.Theloai;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/theloais")
public class AdminTheloaiController {

    @Autowired
    private TheloaiService theloaiService;

    @GetMapping
    public String index(Model model) {
        List<Theloai> theloais = theloaiService.getAllTheloais();
        model.addAttribute("theloais", theloais);
        return "admin/theloais/index";
    }

    @GetMapping("/add")
    public String createForm(Model model) {
        model.addAttribute("theloai", new Theloai());
        return "admin/theloais/create";
    }

    @PostMapping("/add")
    public String create(@Valid Theloai theloai, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/theloais/create";
        }
        theloaiService.addTheloai(theloai);
        return "redirect:/admin/theloais";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Theloai theloai = theloaiService.getTheloaiById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid theloai Id:"
                        + id));
        model.addAttribute("theloais", theloai);
        return "admin/theloais/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @Valid Theloai theloai,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            theloai.setTheloaiId(id);
            return "admin/theloais/edit";
        }
        theloaiService.updateTheloai(theloai);
        model.addAttribute("theloais", theloaiService.getAllTheloais());
        return "redirect:/admin/theloais";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        try {
            theloaiService.deleteTheloaiById(id);
            return "redirect:/admin/theloais";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Không thể xoá thể loại này vì nó có liên quan đến bảng phim.");
            return "/admin/theloais/error";
        }
    }
}
