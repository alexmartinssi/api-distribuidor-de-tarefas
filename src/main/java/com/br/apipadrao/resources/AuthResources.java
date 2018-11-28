package com.br.apipadrao.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.apipadrao.dto.EmailDTO;
import com.br.apipadrao.security.JWTUtil;
import com.br.apipadrao.security.UserSS;
import com.br.apipadrao.services.AuthService;
import com.br.apipadrao.services.UserService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthResources {

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/v1/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = userService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());

		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/v1/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
		authService.sandNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}
}
