package com.michael.test.controller;

import com.michael.test.controller.dto.request.LoginRequest;
import com.michael.test.controller.dto.request.SignupRequest;
import com.michael.test.controller.dto.response.BaseResponse;
import com.michael.test.controller.dto.response.LoginResponse;
import com.michael.test.model.Authentications;
import com.michael.test.model.Users;
import com.michael.test.repository.AuthenticationRepository;
import com.michael.test.repository.UserRepository;
import com.michael.test.security.JwtService;
import com.michael.test.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login (@RequestBody LoginRequest loginRequest) {
//        try {
            Authentications authentication = authenticationService.authenticate(loginRequest);

            String jwtToken = jwtService.generateToken(authentication);

            LoginResponse responseData = new LoginResponse(jwtToken, jwtService.getExpirationTime());

            return ResponseEntity.ok().body(new BaseResponse(true, "Success Login", responseData));

//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
//        }
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup (@RequestBody SignupRequest signupRequest) {
        try {
            Users userEmail = userRepository.findByEmail(signupRequest.getEmail()).orElse(null);
            if (userEmail != null) {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Email already exist", null));
            }
            Authentications userName = authenticationRepository.findByUsername(signupRequest.getUsername()).orElse(null);
            if (userName != null) {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Username already exist", null));
            }

            Users userSave = authenticationService.signup(signupRequest);

            if (userSave != null) {
                return ResponseEntity.ok().body(new BaseResponse(true, "Success Signup", userSave));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Failed to Signup", null));
            }



        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, e.getMessage(), null));
        }
    }
}
