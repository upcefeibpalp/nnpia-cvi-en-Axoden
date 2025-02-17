package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String findUsers() {
        return userService.findUsers().toString();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String sayGreeting() {
        return userService.findUser();
    }
}
