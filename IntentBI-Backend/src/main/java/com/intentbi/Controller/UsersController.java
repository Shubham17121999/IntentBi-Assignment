package com.intentbi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intentbi.DTO.JWTAuthResponse;
import com.intentbi.DTO.LoginDTO;
import com.intentbi.Entity.Users;
import com.intentbi.Service.UsersService;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private BCryptPasswordEncoder passencode;
	
	@PostMapping("/register")
	public ResponseEntity<Users> adduserhandler(@RequestBody Users user) {
		String hashpass = passencode.encode(user.getPassword());
		user.setPassword(hashpass);
		
		Users u = usersService.addUser(user);
		return new ResponseEntity<Users>(u, HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO logindto) {

		String token = usersService.login(logindto);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return new ResponseEntity<JWTAuthResponse>(jwtAuthResponse, HttpStatus.ACCEPTED);

	}
	
}
