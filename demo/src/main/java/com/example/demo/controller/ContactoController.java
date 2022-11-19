package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Contacto;
import com.example.demo.serviceinterface.IcontactoService;

@Controller
@RequestMapping
public class ContactoController {
	
	
	@Autowired
	private IcontactoService service;
	
	@GetMapping("/listar")
	public String listarContacto(Model model) {
		List<Contacto> contactos = service.listar();
		model.addAttribute("contactos",contactos);
		return "contacto";
	}
	
	@GetMapping("/new")
	public String agregarContacto(Model model) {
		
		model.addAttribute("contacto",new Contacto ());
		return "formcontacto";
	}
	@PostMapping("/save")
	public String saveContacto (@Valid Contacto c, Model model) {
		service.save(c);
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarContacto(@PathVariable int id, Model model) {
		
		Optional <Contacto> contacto = service.listarId(id);
		model.addAttribute("contacto", contacto);
				
		return "formcontacto";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarContacto(Model model,@PathVariable int id) {
		
		Optional <Contacto> contacto = service.listarId(id);
		model.addAttribute("contacto", contacto);
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
	@GetMapping({"/contacto"})
	public String Contacto( Model model) {
		return "contacto";
	}
	
}
