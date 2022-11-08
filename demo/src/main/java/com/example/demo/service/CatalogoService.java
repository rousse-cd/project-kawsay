package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Catalogo;
import com.example.demo.modelsDAO.ICatalogo;
import com.example.demo.serviceinterface.ICatalogoService;

@Service
public class CatalogoService implements ICatalogoService {

	@Autowired
	private ICatalogo dao;
	
	@Override
	public List<Catalogo> listar() {		
		return (List<Catalogo>) dao.findAll();
	}

	@Override
	public Optional<Catalogo> listarId(int id) {		
		return dao.findById(id);
	}

	@Override
	public int save(Catalogo p) {
		int res=0;
		Catalogo per=dao.save(p);
		if(!per.equals(null)) {
			res=1;
		}
		return res;
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}
