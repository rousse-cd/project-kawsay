package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Catalogo;
import com.example.demo.serviceinterface.ICatalogoService;

@Controller
@RequestMapping("/catalogo")
public class CatalogoControler {
	
	@Autowired
	private ICatalogoService service;
//	private ICatalogo catalagoDao;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("catalogos", service.listar());
		return "catalogo";
	}
	@GetMapping("/listar/{id}")
	public String listarId(@PathVariable int id,Model model) {
		model.addAttribute("catalogo", service.listarId(id));
		return "form";
	}
	
//	a
	@GetMapping("/new")
	public String nuevo(Model model) {
		model.addAttribute("catalogo", new Catalogo());
		return "form";
	}
	
	@PostMapping("/save")
	public String save(@Valid Catalogo p,Model model,@RequestParam(name = "file", required = false) MultipartFile foto, RedirectAttributes flash) {
		int id=service.save(p);
		if(id==0) {
			return "form";
		}
		
		if(!foto.isEmpty()) {
			String ruta = "src//main//resources//static/uploads";
			
			try {
				byte[] bytes = foto.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				p.setImage(foto.getOriginalFilename());
			}catch(Exception e) {
				
			}
			service.save(p);
			flash.addFlashAttribute("Success", "Foto subida!!");
		}
		
		return "redirect:/catalogo/listar";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable int id,Model model) {
		service.delete(id);
		return "redirect:/catalogo/listar";
	}
	
}
