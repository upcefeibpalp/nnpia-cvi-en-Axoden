package cz.upce.fei.nnpiacv.controller;

import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // Endpoint pro zobrazení uživatelů (existující)
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

    //POUZIVAT DTo
    // NOVÝ endpoint pro přidání uživatele
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String email) {
        if (email == null) {
            return ResponseEntity.ok(userService.findUsers());
        } else {
            User user = userService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.notFound().build();
            } else {
                // I když se vyhledává jeden uživatel, vracíme seznam, abychom zachovali konzistenci
                return ResponseEntity.ok(Collections.singletonList(user));
            }
        }
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}