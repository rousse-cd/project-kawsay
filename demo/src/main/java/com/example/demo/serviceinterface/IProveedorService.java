package com.example.demo.serviceinterface;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Proveedores;

public interface IProveedorService {
	public List<Proveedores> listar();

	public Optional<Proveedores> listarId(int id);

	public int save(Proveedores p);

	public void delete(int id);
}
