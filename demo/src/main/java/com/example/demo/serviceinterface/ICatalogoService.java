package com.example.demo.serviceinterface;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Catalogo;

public interface ICatalogoService {
	public List<Catalogo> listar();

	public Optional<Catalogo> listarId(int id);

	public int save(Catalogo p);

	public void delete(int id);
}
