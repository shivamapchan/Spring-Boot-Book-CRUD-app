package com.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.book.model.Book;
import com.book.repository.BookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testInsertFirstBook() {
		Book book = new Book(1, "Html", 25.36, "John Castle", "Persion Inc");
		Book savedBook = bookRepository.save(book);
		System.out.println(savedBook.getBook_name());
		assertThat(savedBook.getBkid()).isGreaterThan(0);
	}
	
	@Test
	public void testSelectBook(){
		Iterable<Book> i = bookRepository.findAll();
		i.forEach(book->System.out.println(book));
	}

}
