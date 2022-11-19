package com.example.demo.controller;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.models.Usuario;
import com.example.demo.models.ResponseUsuario;
import com.example.demo.service.IUsuarioService;


@Controller
@RequestMapping("/app")
public class UsuarioController {

	@Value("${title.generic}")
	private String titlePage;

	@Value("${mensaje}")
	private String mensaje;

	@Autowired
	@Qualifier("servicio1")
	private IUsuarioService InterfaceUsuario1;
	

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
	@GetMapping({"/catalogo"})
	public String Catalogo( Model model) {
		return "catalogo";
	}
	@GetMapping({"/usuario"})
	public String usuario2( Model model) {
		return "usuario";
	}

	
	

	
	
	
	
	

	
	@GetMapping("/listar")
	public String ListarUsuario(Model model) {

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "Formulario Usuario");
		ResponseUsuario<Usuario> rspta = InterfaceUsuario1.listarUsuario();

		if (rspta.getEstado()) {
			model.addAttribute("Mensaje", rspta.getMensaje());
			model.addAttribute("listita", rspta.getListData());
			return "ListaUsuario";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "ErroresUsuario";
		}
	}

	@GetMapping("/crear")
	public String usuario(Model model) {
		Usuario usuario = new Usuario();

		model.addAttribute("TituloPagina", titlePage);
		model.addAttribute("titulo", "Formulario - Crear Usuario");
		model.addAttribute("personaje", usuario);

		return "usuario";
	}

	@GetMapping("/Editar/{idUsuario}")
	public String EditarUsuario(@PathVariable int idUsuario, Model model) {

		model.addAttribute("TituloPagina", titlePage);

		ResponseUsuario<Usuario> rspta = InterfaceUsuario1.editarUsuario(idUsuario);

		model.addAttribute("titulo", "KAWSAY - Editando el usuario " + rspta.getData().getNombres());

		model.addAttribute("usuario", rspta.getData());

		return "usuario";
	}

	@GetMapping("/Elimnar/{idUsuario}")
	public String ElimnarUsuario(@PathVariable int idUsuario, Model model) {

		ResponseUsuario<Usuario> rspta = InterfaceUsuario1.eliminarUsuario(idUsuario);

		if (rspta.getEstado()) {
			return "redirect:/app/listar";
		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());

			return "ErroresUsuario";
		}
	}

	@PostMapping("/usuario")
	public String creaUsuario(@Valid Usuario Luffy, BindingResult result, Model model,
			@RequestParam("ImagenDelFormulario") MultipartFile fileRecibido, SessionStatus sStatus) {

		if (result.hasErrors()) {
			return "usuario";
		}

		ResponseUsuario<Usuario> rspta = InterfaceUsuario1.crearUsuario(Luffy, fileRecibido);

		if (rspta.getEstado()) {

			sStatus.setComplete();
			return "redirect:/app/listar";

		} else {
			model.addAttribute("mensaje", rspta.getMensaje());
			model.addAttribute("mensajeError", rspta.getMensajeError());
			return "ErroresEncontrados";
		}

	}

}
