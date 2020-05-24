package com.christo.servicesImpl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.christo.entities.Users;
import com.christo.exceptions.ResourceNotFoundException;
import com.christo.repositories.UserRepository;
import com.christo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Users addUser(Users user) {
		Users savedUser = userRepository.save(user);

		return savedUser;
	}

	@Override
	public Users updateUser(Integer id, Users user) {
		Optional<Users> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("User id :- " + id);
		
		Users userRetreived = userOptional.get();
		
		userRetreived.setName(user.getName());
		userRetreived.setBirthDate(user.getBirthDate());
		
		Users updatedUser = userRepository.save(userRetreived);

		return updatedUser;

	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public Users getUser(Integer id) {
		Optional<Users> userOptional = userRepository.findById(id);

		if (!userOptional.isPresent())
			throw new ResourceNotFoundException("User id :- " + id);

		return userOptional.get();
	}

	@Override
	public Collection<Users> getUsers() {
		return userRepository.findAll();
	}

}
