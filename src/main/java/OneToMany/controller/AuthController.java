package OneToMany.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import OneToMany.entity.Users;
import OneToMany.service.UserService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8081", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Users users) {

        userService.registerUser(users);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário registrado com sucesso.");
        return ResponseEntity.ok(response);
    }

    // TODO refazer em REST
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String senha) {
        Users user = userService.authenticateUser(email, senha);
        if (user != null) {

            return "redirect:/home";
        }
        return "login";
    }
}
