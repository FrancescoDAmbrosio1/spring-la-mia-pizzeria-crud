package org.lessons.pizzeria.controller;

import java.util.ArrayList;
import java.util.List;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	public String store(@Valid @ModelAttribute("pizza") Pizza pizza, 
			BindingResult bindingResult, Model model) {
		
		if(pizza.getPrice() <= 0) {
			
			bindingResult.addError(new ObjectError("pizza.price", "Il prezzo della pizza è obbligatorio e maggiore di 0"));
		
		}
		
		if(bindingResult.hasErrors()) {
			
			return "pizze/create";
		}
		
		repository.save(pizza);
		
		return "redirect:/pizze";
	}
	
	@GetMapping("/search")
	public String search(@Param("input") String input, Model model) {

		List<Pizza> list = new ArrayList<Pizza>();
		
		if(!input.isEmpty()) {
			
			list = repository.search(input);
			
		} 
			
		model.addAttribute("list", list);	
		
		return "/pizze/search";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("pizza", repository.getReferenceById(id));
		
		return "/pizze/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza pizza,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			return "/pizze/edit";
		}
		
		repository.save(pizza);
		
		return "redirect:/pizze";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		repository.deleteById(id);
		
		return "redirect:/pizze";
	}
}
