package com.example.demo.modelsDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Contacto;

@Repository
public interface IContacto extends CrudRepository<Contacto, Integer> {

}
