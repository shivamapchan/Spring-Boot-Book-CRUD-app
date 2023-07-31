package com.book.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.exception.UserNotFoundException;
import com.book.model.User;
import com.book.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insertUser(User user) {
		encodePassword(user);
		userRepository.save(user);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	}
	
	public User getById(Integer id) throws UserNotFoundException {
		try {
			return userRepository.findById(id).get();
		} catch(NoSuchElementException ex) {
			throw new UserNotFoundException("Could not found User with ID "+ id);
		}
	}
}
