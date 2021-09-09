package com.carlease.service.carleaseservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlease.service.carleaseservice.authservice.JWTTokenProvider;
import com.carlease.service.carleaseservice.model.AuthenticateRequest;
import com.carlease.service.carleaseservice.model.JwtAuthenticationResponse;
import com.carlease.service.carleaseservice.model.UserPrincipal;

@RestController
@RequestMapping("/authenticate")
public class AuthResource {

	private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    /**
     * Authenticating user and creating the token and returning a reponse with
     * created token
     * @param authenticateRequest
     * @return token
     */
    @PostMapping
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody AuthenticateRequest authenticateRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUserName(), authenticateRequest.getPassword()));
        String token =jwtTokenProvider.generateToken((UserPrincipal)authentication.getPrincipal());
        logger.info("Token Created {}",token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }


}
