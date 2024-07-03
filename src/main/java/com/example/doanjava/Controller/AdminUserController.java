package com.example.doanjava.Controller;

import com.example.doanjava.Service.UserService;
import com.example.doanjava.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/users/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        try {
            userService.deleteUserById(id);
            return "redirect:/admin/users";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "Không thể xoá người dùng này vì có liên quan đến bảng khác.");
            return "/admin/users/error";
        }
    }
}
