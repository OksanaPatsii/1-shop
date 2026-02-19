package de.oks.g52shop.controller;

import de.oks.g52shop.domain.entity.User;
import de.oks.g52shop.exception_handling.Response;
import de.oks.g52shop.service.interfaces.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final UserService service;

    public RegistrationController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public Response register(@RequestBody User user) {
        service.register(user);
        return new Response("Die Registrierung war erfolgreich. Bitte überprüfen Sie Ihre E-Mails, um Ihre Registrierung zu bestätigen.");
    }
}
