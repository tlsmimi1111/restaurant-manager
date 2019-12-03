package axon.tls.restaurant.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import axon.tls.restaurant.config.ApiConfig;
import axon.tls.restaurant.jwt.JwtTokenProvider;
import axon.tls.restaurant.models.CustomUserDetails;
import axon.tls.restaurant.models.LoginRequest;
import axon.tls.restaurant.models.LoginResponse; 

@RestController
@RequestMapping(ApiConfig.PREFIX)
@CrossOrigin
public class AuthController {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@PostMapping(ApiConfig.LOGIN)
	public LoginResponse authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
		
	}
	
	
}
