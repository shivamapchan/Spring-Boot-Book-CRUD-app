package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.book.exception.UserNotFoundException;
import com.book.model.User;
import com.book.security.BookUserDetails;
import com.book.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping()
	public String userForm(User user, Model model) {
		model.addAttribute("pageTitle", "User Registration Form");
		return "register";
	}
	
	@PostMapping("/add")
	public String addUser(@ModelAttribute("user") User user,RedirectAttributes redirectAttributes) {
		userService.insertUser(user);
		redirectAttributes.addFlashAttribute("message", "The user has been added successfully");
		return "redirect:/user?success";
	}
	
	@GetMapping("/view_profile")
	public String viewProfile(@AuthenticationPrincipal BookUserDetails logedInUser, Model model) {
		String email = logedInUser.getUsername();
		User user = userService.findUserByEmail(email);
		model.addAttribute("userObject",user);
		model.addAttribute("pageTitle", "My Profile");
		return "/view_profile";
	}
	
}
