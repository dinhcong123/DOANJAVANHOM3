package com.example.doanjava.Controller;

import com.example.doanjava.Service.RoleService;
import com.example.doanjava.models.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listRoles(Model model) {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "/admin/roles/index";
    }

    @GetMapping("/add")
    public String showAddForm(Role role) {
        return "/admin/roles/create";
    }

    @PostMapping("/add")
    public String addRole(@Valid Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/admin/roles/create";
        }
        roleService.save(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
        model.addAttribute("role", role);
        return "/admin/roles/edit";
    }

    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable("id") Long id, @Valid Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            role.setId(id);
            return "/admin/roles/edit";
        }
        roleService.save(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, Model model) {
        Role role = roleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
        roleService.delete(role);
        return "redirect:/admin/roles";
    }
}
