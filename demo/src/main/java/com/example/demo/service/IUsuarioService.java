package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;


import com.example.demo.models.Usuario;
import com.example.demo.models.ResponseUsuario;

public interface IUsuarioService {
	
	public ResponseUsuario<Usuario> crearUsuario(Usuario p,  MultipartFile fileRecibido);
	
	public ResponseUsuario<Usuario> editarUsuario(Integer ID);	
	
	public ResponseUsuario<Usuario> eliminarUsuario(Integer ID);	
	
	public ResponseUsuario<Usuario> listarUsuario();

}
