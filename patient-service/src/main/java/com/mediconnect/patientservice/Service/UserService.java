package com.mediconnect.patientservice.Service;


import com.mediconnect.patientservice.Entity.User;
import com.mediconnect.patientservice.patientdto.AuthRequest;
import com.mediconnect.patientservice.patientdto.AuthResponse;
import com.mediconnect.patientservice.patientdto.UserRequestDTO;
import com.mediconnect.patientservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


 public AuthResponse registerUser(UserRequestDTO userRequestDTO){
     if(userRepository.existsByEmail(userRequestDTO.getEmail())){
         throw new RuntimeException("Email already exists with email" + userRequestDTO.getEmail());
     }

     User user =User.builder()
             .email(userRequestDTO.getEmail())
             .password(passwordEncoder.encode(userRequestDTO.getPassword()))
             .patientId(userRequestDTO.getPatientId())
             .role(userRequestDTO.getRole())
             .build();

     userRepository.save(user);

     //Generate JWT Token
     String token = jwtService.generateToken(user.getEmail());
     return new  AuthResponse(token,"User registered successfully");

 }

 public AuthResponse authenticateUser(AuthRequest authRequest) {
     User user =  userRepository.findByEmail(authRequest.getEmail())
             .orElseThrow(()-> new RuntimeException("User not found"));

     if(!passwordEncoder.matches(authRequest.getPassword(),user.getPassword())){
         throw new RuntimeException("Wrong password");
     }

     String token = jwtService.generateToken(user.getEmail());
     return new AuthResponse(token,"Login successful");
 }
}
