package com.example.demo.modelsDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Proveedores;

@Repository
public interface IProveedor extends CrudRepository<Proveedores, Integer>{

}
