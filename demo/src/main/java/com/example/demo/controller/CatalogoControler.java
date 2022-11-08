package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Catalogo;
import com.example.demo.serviceinterface.ICatalogoService;

@Controller
@RequestMapping
public class CatalogoControler {
	
	@Autowired
	private ICatalogoService service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("catalogos", service.listar());
		return "catalogo2";
	}
	@GetMapping("/listar/{id}")
	public String listarId(@PathVariable int id,Model model) {
		model.addAttribute("catalogo", service.listarId(id));
		return "form";
	}
	
	@GetMapping("/new")
	public String nuevo(Model model) {
		model.addAttribute("catalogo", new Catalogo());
		return "form";
	}
	
	@PostMapping("/save")
	public String save(@Valid Catalogo p,Model model) {
		int id=service.save(p);
		if(id==0) {
			return "form";
		}
		return "redirect:/listar";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id,Model model) {
		service.delete(id);
		return "redirect:/listar";
	}
	
//	rutas controller
	@GetMapping({"/home" })
	public String home( Model model) {
		return "Home";
	}
	@GetMapping({"/registro" })
	public String registro( Model model) {
		return "registro";
	}
	
	@GetMapping({"/nosotros" })
	public String nosotros( Model model) {
		return "nosotros";
	}

	@GetMapping({"/integrantes"})
	public String integrantes( Model model) {
		return "integrantes";
	}
	@GetMapping({"/login"})
	public String Login( Model model) {
		return "login";
	}
}
