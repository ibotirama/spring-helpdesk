package br.com.efacil.springhelpdesk.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.efacil.springhelpdesk.models.User;
import br.com.efacil.springhelpdesk.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", userService.findAll());
		return "users/index";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("user", new User());
		
		return "users/create";
	}
	
	@GetMapping("/edit/{id}")
	public String create(@PathVariable Long id, Model model) {
		User user = this.userService.findById(id);
		model.addAttribute("user", user);
		return "users/edit";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "users/edit";
		}
		
		this.userService.update(id, user);
		
		return "redirect:/users";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "users/create";
		}
		this.userService.create(user);
		
		return "redirect:/users";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id) {
		this.userService.delete(id);
		return "redirect:/users";
	}

}
