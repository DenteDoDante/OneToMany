package OneToMany.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import OneToMany.entity.Users;
import OneToMany.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/registerUser")
    public Users CreateUser(@RequestBody @Valid Users user) {
        return userService.SaveUser(user);
    }

}
