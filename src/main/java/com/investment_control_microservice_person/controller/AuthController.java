package com.investment_control_microservice_person.controller;

import com.investment_control_microservice_person.dto.AuthRequest;
import com.investment_control_microservice_person.dto.AuthResponse;
import com.investment_control_microservice_person.dto.RegisterRequest;
import com.investment_control_microservice_person.security.JwtService;
import com.investment_control_microservice_person.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PersonService personService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          PersonService personService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.personService = personService;
    }

    @PostMapping(
            value = "/login",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        personService.register(request);
        return ResponseEntity.ok("User successfully registered.\n");
    }

}


