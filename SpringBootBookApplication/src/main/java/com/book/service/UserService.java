package com.book.service;

import java.util.List;
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
	
	public List<User> findAllUsers(){
		List<User> userList = userRepository.findAll();
		return userList;
	}
	
	public void delete(Integer id) throws UserNotFoundException {
		Long countById = userRepository.countById(id);
		if(countById == null || countById == 0) {
			throw new UserNotFoundException("Could not found any User with ID "+id);
		} 
		userRepository.deleteById(id);
	}
	
	public User getById(Integer id) throws UserNotFoundException {
		try {
			return userRepository.findById(id).get();
		} catch(NoSuchElementException ex) {
			throw new UserNotFoundException("Could not found User with ID "+ id);
		}
	}
}
