package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Comment;
import com.example.demo.responsitory.CommentRepository;

@Controller
public class CommentController {
	private final CommentRepository repository;
	
	public CommentController(CommentRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public String getALLComments(@ModelAttribute Comment comment,Model model) {
		model.addAllAttributes(repository.findAll());//AddAttribute("comments",repository.findAll())が使えない
		return "list";
	}
	
	@PostMapping
	public String addComment(@Validated @ModelAttribute Comment comment,BindingResult result,Model model){
		
		model.addAttribute(repository.findAll());
		
		if(result.hasErrors()) {
			return "list";
		}
		repository.save(comment);
		
		//ルートパスにリダイレクト	
		return "redirect:/";
	}
	
}
