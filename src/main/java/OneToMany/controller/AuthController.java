package OneToMany.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OneToMany.entity.Users;
import OneToMany.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private UserService userService;

    // TODO APLICAR React!!!!
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String password) {
        userService.registerUser(email, password);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String senha) {
        Users user = userService.authenticateUser(email, senha);
        if (user != null) {

            return "redirect:/home";
        }
        return "login";
    }
}
