package com.example.demo.modelsDAO;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.models.Usuario2;


public interface IUsuario extends JpaRepository<Usuario2, Integer>{
	
	Usuario2 findByNombre(String nombre);

}
