package com.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.book.model.User;
import com.book.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testSelectBook(){
		Iterable<User> i = userRepository.findAll();
		i.forEach(book->System.out.println(book));
	}

}
