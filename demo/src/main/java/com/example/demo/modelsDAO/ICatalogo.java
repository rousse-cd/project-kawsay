package com.example.demo.modelsDAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Catalogo;

@Repository
public interface ICatalogo extends CrudRepository<Catalogo, Integer>{

}
