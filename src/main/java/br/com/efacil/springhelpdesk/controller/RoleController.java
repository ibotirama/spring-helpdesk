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
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.efacil.springhelpdesk.models.Role;
import br.com.efacil.springhelpdesk.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("list", this.roleService.findAll());
		return "roles/index";
	}

	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create";
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/roles/create";
		}
		
		role.setName(role.getName().toUpperCase());
		roleService.create(role);
		
		return "redirect:/roles";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable Long id, Model model) {
		this.roleService.delete(id);
		
		return "redirect:/roles";
	}
	
	
}
