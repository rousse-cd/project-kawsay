package com.example.demo.service;

import java.io.File;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.MvcConfig;
import com.example.demo.models.Usuario;
import com.example.demo.models.ResponseUsuario;
import com.example.demo.repository.UsuarioDAO;
import com.example.demo.service.UsuarioService;

@Component("servicio1")
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	UsuarioDAO usuarioRepository;
	
	@Autowired
	MvcConfig config;

	@Override
	public ResponseUsuario<Usuario> crearUsuario(Usuario p,  MultipartFile fileRecibido) {		
		
		ResponseUsuario<Usuario> responseusuario = new ResponseUsuario<>();		
		try {
			
			if (!fileRecibido.isEmpty()) {
				try {
					
					if (p.getUriImagen() != null) {
						
						Path enlaceGuardado = Paths.get(config.pathImage()+"\\"+p.getUriImagen());
						File fileEliminar = enlaceGuardado.toFile();
						
						if (fileEliminar.exists()) {
							fileEliminar.delete();
						}
					}
					
					String NewExtention = StringUtils.getFilenameExtension(fileRecibido.getOriginalFilename());
					String newName = UUID.randomUUID().toString() + "." + NewExtention;
					
					
					byte [] bytesFile = fileRecibido.getBytes();
					Path enlaceAGuardar = Paths.get(config.pathImage()+"//"+newName);
					Files.write(enlaceAGuardar, bytesFile);
					
					p.setUriImagen(newName);
					
				} catch (IOException e) {
					responseusuario.setEstado(false);
					responseusuario.setMensaje("Error al ingresar un nuevo usuario "+p.getNombres());
					responseusuario.setMensajeError(e.getStackTrace().toString());	
					return responseusuario;
				}
				
			}
			
			
			
			Usuario usuario = usuarioRepository.save(p);			
			responseusuario.setEstado(true);
			responseusuario.setMensaje("El Usuario "+usuario.getNombres()+" ha sido creado correctamente");
			
		} catch (Exception e) {
			responseusuario.setEstado(false);
			responseusuario.setMensaje("Error al agregar un usuario "+p.getNombres());
			responseusuario.setMensajeError(e.getStackTrace().toString());
		}		
		return responseusuario;
	}

	
	@Override
	public ResponseUsuario<Usuario> editarUsuario( Integer ID) {

		ResponseUsuario<Usuario> responseusuario = new ResponseUsuario<>();
		
		try {
			Optional<Usuario> p = usuarioRepository.findById(ID);
			responseusuario.setEstado(true);
			responseusuario.setData(p.get());
			
		} catch (Exception e) {
			responseusuario.setEstado(false);
			responseusuario.setMensajeError(e.getStackTrace().toString());
		}
		
		return responseusuario;
	}
	
	
	@Override
	public ResponseUsuario<Usuario> eliminarUsuario(Integer ID) {
		
		ResponseUsuario<Usuario> responseusuario = new ResponseUsuario<>();
		
		try {
			Optional<Usuario> p = usuarioRepository.findById(ID);
			
			
			Path rutaElimarFile = Paths.get(config.pathImage()+"/"+p.get().getUriImagen());			
			File fileEliminar = rutaElimarFile.toFile();
			if (fileEliminar.exists()) {
				fileEliminar.delete();
			}
			
			usuarioRepository.deleteById(ID);
			responseusuario.setEstado(true);
			responseusuario.setMensaje("El usuario "+p.get().getNombres()+" ha sido eliminado");
			
		} catch (Exception e) {
			responseusuario.setEstado(false);
			responseusuario.setMensaje("Error al eliminar al usuario");
			responseusuario.setMensajeError(e.getStackTrace().toString());
		}
				
		return responseusuario;
	}




	@Override
	public ResponseUsuario<Usuario> listarUsuario() {
		
		ResponseUsuario<Usuario> responseusuario = new ResponseUsuario<>();
		
		try {
			
			responseusuario.setListData((List<Usuario>) usuarioRepository.findAll());
			responseusuario.setEstado(true);
			responseusuario.setMensaje("Usuarios obtenidos correctamente");
			
		} catch (Exception e) {			
			responseusuario.setEstado(false);
			responseusuario.setMensaje("Error al obtener los usuarios");
			responseusuario.setMensajeError(e.getStackTrace().toString());
		}
		return responseusuario;
	}




	

}
