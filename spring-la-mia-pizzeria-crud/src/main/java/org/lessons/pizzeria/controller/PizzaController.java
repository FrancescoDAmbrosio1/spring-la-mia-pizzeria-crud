package org.lessons.pizzeria.controller;

import java.util.ArrayList;
import java.util.List;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaRepository repository;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> listaPizze = new ArrayList<Pizza>();
		
		listaPizze = repository.findAll();
		
		model.addAttribute("list", listaPizze);
		
		return "/pizze/index";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer pizzaId, Model model) {
		
		model.addAttribute("pizza", repository.getReferenceById(pizzaId));
		
		return "/pizze/show";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "/pizze/create";
	}
	
	@PostMapping("create")
	public String store(@Valid @ModelAttribute("pizza") Pizza pizzaInput, 
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "pizze/create";
		}
		repository.save(pizzaInput);
		
		return "redirect:/pizze";
	}
	
//	  @PostMapping("/search")
//	    public String search(Pizza pizza, Model model, String input) {
//	        if (input != null) {
//	            List<Pizza> list = repository.findByKeyword(input);
//	            model.addAttribute("list", list);
//	        } else {
//	            List<Pizza> list = repository.findAll();
//	            model.addAttribute("list", list);
//	        }
//	        return "redirect;/pizze";
//	    }
}
