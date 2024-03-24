package com.intentbi.Service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.intentbi.Entity.Users;
import com.intentbi.Exception.NoRecordsFoundException;
import com.intentbi.Repository.UsersRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsersRepo usersRepo;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> op = usersRepo.findByUsername(username);
		
		if(op.isPresent()) {
			return op.get();
		}
		else {
			throw new NoRecordsFoundException("No user found with username : "+username);
		}
	}

}
