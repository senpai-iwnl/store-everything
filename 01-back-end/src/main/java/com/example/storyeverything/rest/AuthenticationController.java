package com.example.storyeverything.rest;

import com.example.storyeverything.security.AuthenticationRequest;
import com.example.storyeverything.security.AuthenticationResponse;
import com.example.storyeverything.security.jwt.JwtTokenBlacklist;
import com.example.storyeverything.security.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final JwtTokenBlacklist jwtTokenBlacklist;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService, JwtTokenBlacklist jwtTokenBlacklist) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.jwtTokenBlacklist = jwtTokenBlacklist;
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate user", description = "Authenticate user and return JWT token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user", description = "Invalidate the JWT token")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            jwtTokenBlacklist.add(token);
        }
        return ResponseEntity.ok("Logout successful");
    }
}
