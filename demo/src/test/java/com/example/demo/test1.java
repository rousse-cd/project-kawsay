package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.models.Usuario2;
import com.example.demo.modelsDAO.IUsuario;

@SpringBootTest
class test1 {
	
	@Autowired
	private IUsuario repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void test() {
		Usuario2 us = new Usuario2();
		us.setId(2);
		us.setNombre("tyrone");
		us.setClave(encoder.encode("1234"));
		
		Usuario2 retorno = repo.save(us);
		
		assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));
	}

}
