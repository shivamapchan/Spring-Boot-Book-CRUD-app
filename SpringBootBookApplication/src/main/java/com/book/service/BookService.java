package com.book.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.exception.BookNotFoundException;
import com.book.model.Book;
import com.book.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public void insertBook(Book book) {
		bookRepository.save(book);
	}
	
	public List<Book> selectBook(){
		List<Book> bookList = bookRepository.findAll();
		return bookList;
	}
	
	public void delete(Integer id) throws BookNotFoundException {
		Long countById = bookRepository.countByBkid(id);
		if(countById == null || countById == 0) {
			throw new BookNotFoundException("Could not found any book with [ID: " +id+"]");
		}
		bookRepository.deleteById(id);
	}
	
	public Book getById(Integer id)throws BookNotFoundException {	
		try {
			return bookRepository.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new BookNotFoundException("Could not find any book with ID " + id);
		}
		
	}
}
