package OneToMany.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        response.put("successRegister", users.getEmail() + " Registrado com sucesso!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Users user) {
        Map<String, String> response = new HashMap<>();

        try {
            Users authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());

            if (authenticatedUser != null) {
                response.put("message", "Login feito com sucesso!");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Usuário ou senha inválidos");
                return ResponseEntity.status(401).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace(); // <- VER NO CONSOLE DO BACKEND
            response.put("message", "Erro interno no servidor");
            return ResponseEntity.status(500).body(response);
        }
    }

}
