package com.example.demo.serviceinterface;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Contacto;

public interface IcontactoService {
	public List<Contacto>listar();
	public Optional <Contacto> listarId(int id);
	public int save(Contacto c);
	public void delete(int id);
	

}
