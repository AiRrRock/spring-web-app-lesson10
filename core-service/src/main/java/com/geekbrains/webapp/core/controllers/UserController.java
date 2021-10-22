package com.geekbrains.webapp.core.controllers;

import com.geekbrains.webapp.api.dtos.ProfileDto;
import com.geekbrains.webapp.api.exceptions.ResourceNotFoundException;
import com.geekbrains.webapp.core.model.User;
import com.geekbrains.webapp.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + principal.getName()));
        return new ProfileDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
