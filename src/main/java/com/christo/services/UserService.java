package com.christo.services;

import java.util.Collection;

import com.christo.entities.Users;

public interface UserService {
	
	public abstract Users addUser(Users user);
	public abstract Users updateUser(Integer id, Users user);
	public abstract void deleteUser(Integer id);
	public abstract Users getUser(Integer id);
	public abstract Collection<Users> getUsers();

}
