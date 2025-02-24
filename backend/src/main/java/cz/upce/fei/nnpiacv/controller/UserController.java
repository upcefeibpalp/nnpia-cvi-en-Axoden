package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public Collection<User> findUsers(@RequestParam(required = false) String email) {
        if (email == null) {
            return userService.findUsers();
        } else {
            User user = userService.findByEmail(email);

            if (user == null)
                return Collections.emptyList();
            else
                return Collections.singletonList(user);
        }
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

