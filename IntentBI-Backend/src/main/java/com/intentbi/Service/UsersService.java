package com.intentbi.Service;

import com.intentbi.DTO.LoginDTO;
import com.intentbi.Entity.Users;

public interface UsersService {
	
	public Users addUser(Users user);
	public String login(LoginDTO logindto);
}
