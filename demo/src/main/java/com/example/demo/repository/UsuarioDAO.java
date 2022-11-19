package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;

import com.example.demo.models.Usuario;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
}
