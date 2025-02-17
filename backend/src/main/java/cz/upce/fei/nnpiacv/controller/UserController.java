package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint vracející všechny uživatele (změna z "/users" na "/user")
    @GetMapping
    public Collection<User> findUsers() {
        return userService.findUsers();
    }

    // Endpoint s Query parametrem (/user?id=1)
    @GetMapping(params = "id")
    public Optional<User> getUserByQueryParam(@RequestParam Long id) {
        return userService.findUserById(id);
    }

    // Endpoint s Path parametrem (/user/{id})
    @GetMapping("/{id}")
    public Optional<User> getUserByPathParam(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}

