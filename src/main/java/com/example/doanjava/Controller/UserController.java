package com.example.doanjava.Controller;

import com.example.doanjava.Service.UserService;
import com.example.doanjava.models.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/"; // Chuyển hướng về trang chủ nếu đã đăng nhập
        }
        model.addAttribute("user", new User());
        return "user/login";
    }

    @PostMapping("/login")
    public String loginSubmit(@Valid @ModelAttribute("user") User user, // Validate
                              @NotNull BindingResult bindingResult, // Kết quả của quá
                              Model model) {
        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "user/login"; // Trả về lại view "login" nếu có lỗi
        }
        userService.loadUserByUsername(user.getUsername());
        return "redirect:/";
    }
    @GetMapping("/register")
    public String register(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/"; // Chuyển hướng về trang chủ nếu đã đăng nhập
        }
        model.addAttribute("user", new User()); // Thêm một đối tượng User mới vào
        return "user/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, // Validate
                           BindingResult bindingResult, // Kết quả của quá
                           Model model) {
        if (bindingResult.hasErrors()) { // Kiểm tra nếu có lỗi validate
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "user/register"; // Trả về lại view "register" nếu có lỗi
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.user",
                    "Confirm Password does not match the password");
            return "user/register";
        }
        userService.save(user); // Lưu người dùng vào cơ sở dữ liệu
        userService.setDefaultRole(user.getUsername()); // Gán vai trò mặc định cho nguoi dung
        return "redirect:/login"; // Chuyển hướng người dùng tới trang "login"
    }
}
