package com.spring.security.controller;

import com.spring.security.dto.AuthenticationRequest;
import com.spring.security.dto.AuthenticationResponse;
import com.spring.security.entity.AppUser;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final UserServices userServices;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        for (AppUser users : userServices.findAll()) {
            if (users.getUsername().equals(user.getUsername())) {
                throw new RuntimeException("This username ( " + user.getUsername() + " ) is exist");
            } else if (users.getEmail().equals(user.getEmail()))
                throw new RuntimeException("This email ( " + user.getEmail() + " ) is exist");
        }
        userServices.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/active")
    public ResponseEntity<?> active(@RequestParam Long id) {
        userServices.activeAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unactive")
    public ResponseEntity<?> nonActive(@RequestParam Long id) {
        userServices.unActive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
