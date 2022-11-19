package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Contacto;
import com.example.demo.modelsDAO.IContacto;
import com.example.demo.serviceinterface.IcontactoService;


@Service
public class ContactoService implements IcontactoService{
	@Autowired
	private IContacto data;

	@Override
	public List<Contacto> listar() {
		// TODO Auto-generated method stub
		return (List<Contacto>)data.findAll();
	}

	@Override
	public Optional<Contacto> listarId(int id) {
		return data.findById(id);
		
	}

	@Override
	public int save(Contacto c) {
		int res = 0;
		Contacto contacto = data.save(c);
		
		if (!contacto.equals(null)) {
			res=1;
		}
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
		
	}

}
