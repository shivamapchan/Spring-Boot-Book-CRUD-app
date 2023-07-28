package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.exception.BookNotFoundException;
import com.book.model.Book;
import com.book.service.BookService;

@Controller
@RequestMapping("/book/")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/add_book")
	public String bookForm(Book book, Model model) {
		model.addAttribute("book", book);
		model.addAttribute("pageTitle", "Add New Book");
		return "add_book";
	}
	
	@PostMapping("/add")
	public String addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
		bookService.insertBook(book);
		redirectAttributes.addFlashAttribute("message", "The book has been added successfully!");
		return "redirect:view_book";
	}
	
	@GetMapping("/view_book")
	public String viewBook(@ModelAttribute("book") Book book, Model model){
		List<Book> bookList = bookService.selectBook();
		model.addAttribute("bookList",bookList);
		model.addAttribute("pageTitle", "List of Books");
		return "/view_book";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			bookService.delete(id);
			redirectAttributes.addFlashAttribute("message", "The Book ID "+ id + " has been deleted!");
		} catch (BookNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/book/view_book";
	}
	
	@GetMapping("/update/{id}")
	public String updateBook(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Book book = bookService.getById(id);
			model.addAttribute("book", book);
			model.addAttribute("pageTitle", "Edit Book [ID: "+id+" ]");
			return "add_book";
		} catch (BookNotFoundException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/book/view_book";
	}
}
