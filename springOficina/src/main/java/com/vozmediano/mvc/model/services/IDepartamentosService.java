package com.vozmediano.mvc.model.services;

import java.util.List;

import com.vozmediano.mvc.entity.oficina.Departamentos;

public interface IDepartamentosService {
	public List<Departamentos> findAll();
	
	public void save(Departamentos d);
	
	public void delete(Departamentos d);

	Departamentos findById(String depId);
}
