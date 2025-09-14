package com.mediconnect.patientservice.Controller;

import com.mediconnect.patientservice.Service.UserService;
import com.mediconnect.patientservice.patientdto.AuthRequest;
import com.mediconnect.patientservice.patientdto.AuthResponse;
import com.mediconnect.patientservice.patientdto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequestDTO userRequestDTO){
        AuthResponse authResponse = userService.registerUser(userRequestDTO);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        AuthResponse response = userService.authenticateUser(authRequest);
        return ResponseEntity.ok(response);
    }

}
