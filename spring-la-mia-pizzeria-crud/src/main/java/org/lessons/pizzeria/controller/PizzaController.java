package org.lessons.pizzeria.controller;

import java.util.ArrayList;
import java.util.List;

import org.lessons.pizzeria.model.Pizza;
import org.lessons.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaRepository repository;
	
	@GetMapping
	public String index(Model model) {
		
		List<Pizza> listaPizze = new ArrayList();
		
		model.addAttribute("lista", listaPizze);
		
		return "/pizze/index";
	}

}
