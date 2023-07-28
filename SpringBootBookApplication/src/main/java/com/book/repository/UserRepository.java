package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// login & view profile
	@Query("SELECT u FROM User u WHERE u.email= :email")
	public User getUserByEmail(@Param("email") String email);
	
	// needed for delete functionality
	public Long countById(Integer id);

}
