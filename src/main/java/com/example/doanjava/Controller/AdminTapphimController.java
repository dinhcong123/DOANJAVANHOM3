package com.example.doanjava.Controller;

import com.example.doanjava.Service.PhimService;
import com.example.doanjava.Service.TapphimService;
import com.example.doanjava.models.Tapphim;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/tapphims")
public class AdminTapphimController {

    @Autowired
    private TapphimService tapphimService;

    @Autowired
    private PhimService phimService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("tapphims", tapphimService.getAllTapphim());
        return "/admin/tapphims/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("tapphims", new Tapphim());
        model.addAttribute("phims", phimService.getAllPhims());
        return "/admin/tapphims/create";
    }

    @PostMapping("/create")
    public String addTapphim(Tapphim tapphim, @RequestParam MultipartFile movietapphim,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tapphims", tapphim);
            model.addAttribute("phims", phimService.getAllPhims());
            return "/admin/tapphims/create";
        }

        // Tiến hành xử lý logic lưu tập phim
        tapphimService.updatevideo(tapphim, movietapphim);
        tapphimService.addTapphim(tapphim);

        return "redirect:/admin/tapphims";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Tapphim tapphim = tapphimService.getTapphimById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tapphim Id:" + id));
        model.addAttribute("tapphims", tapphim);
        model.addAttribute("phims", phimService.getAllPhims());
        return "/admin/tapphims/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Valid Tapphim tapphim,
                       @RequestParam("movietapphim") MultipartFile movietapphim,BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            tapphim.setTapphimId(id);
            model.addAttribute("phims", phimService.getAllPhims());
            return "/admin/tapphims/edit";
        }
        tapphimService.updatevideo(tapphim,movietapphim);
        tapphimService.updateTapphim(tapphim);
        return "redirect:/admin/tapphims";
    }

    @GetMapping("/delete/{id}")
    public String deleteTapphim(@PathVariable Long id) {
        tapphimService.deleteTapphimById(id);
        return "redirect:/admin/tapphims";
    }
}
