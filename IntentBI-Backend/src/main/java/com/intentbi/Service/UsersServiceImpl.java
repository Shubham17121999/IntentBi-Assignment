package com.intentbi.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.intentbi.Config.JwtTokenProvider;
import com.intentbi.DTO.LoginDTO;
import com.intentbi.Entity.Users;
import com.intentbi.Repository.UsersRepo;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepo usersRepo;
	
	@Autowired
	private AuthenticationManager authmanager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
		
	@Override
	public Users addUser(Users user) {
		Optional<Users> op = usersRepo.findByUsername(user.getUsername());
		
		if(op.isPresent()) {
			throw new NullPointerException("User already present");
		}
		return usersRepo.save(user);
	}

	
	@Override
	public String login(LoginDTO logindto) {
		Optional<Users> op = usersRepo.findByUsername(logindto.getUsername());
		
		if(!op.isPresent()) {
			throw new NullPointerException("Incorrect Username");
		}
		
		if(op.isPresent()) {
			Users u = op.get();
			
			if(!encoder.matches(logindto.getPassword(), u.getPassword())) {
				throw new NullPointerException("Incorrect Password");
			}
		}
		
		Authentication authentication = authmanager
				.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);
		return token;
	}

	
	
	
}
