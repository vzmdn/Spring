package com.vozmediano.mvc.model.services;

import java.util.List;

import com.vozmediano.mvc.entity.oficina.Empleados;

public interface IEmpleadosService {
	public List<Empleados> findAll();

	Empleados findById(int id);
	
	public void save(Empleados e);
	
	public void delete(Empleados e);
}
