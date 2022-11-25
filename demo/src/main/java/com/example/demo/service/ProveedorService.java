package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Proveedores;
import com.example.demo.modelsDAO.IProveedor;
import com.example.demo.serviceinterface.IProveedorService;

@Service
public class ProveedorService implements IProveedorService {

	@Autowired
	private IProveedor dao;
	
	@Override
	public List<Proveedores> listar() {		
		return (List<Proveedores>) dao.findAll();
	}

	@Override
	public Optional<Proveedores> listarId(int id) {		
		return dao.findById(id);
	}

	@Override
	public int save(Proveedores p) {
		int res=0;
		Proveedores per=dao.save(p);
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
