package org.k21copay.refreshersession.controller;

import lombok.RequiredArgsConstructor;
import org.k21copay.refreshersession.entity.User;
import org.k21copay.refreshersession.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .orElse(null);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.save(user);
    }
}
