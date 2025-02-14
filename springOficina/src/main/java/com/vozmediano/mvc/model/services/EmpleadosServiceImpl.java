package com.vozmediano.mvc.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vozmediano.mvc.entity.oficina.Empleados;
import com.vozmediano.mvc.model.dao.IEmpleadosDAO;
import com.vozmediano.mvc.model.exceptions.EmpleadoNotFoundException;

@Service
public class EmpleadosServiceImpl implements IEmpleadosService {
	@Autowired
	private IEmpleadosDAO empleadosDAO;
	
	@Override
	public List<Empleados> findAll(){
		return (List<Empleados>) empleadosDAO.findAll();
	}
	
	@Override
	public Empleados findById(int id) {
		return empleadosDAO.findById(id)
				.orElseThrow(() -> new EmpleadoNotFoundException(id));
	}

	@Override
	public void save(Empleados e) {
		empleadosDAO.save(e);
		
	}

	@Override
	public void delete(Empleados e) {
		empleadosDAO.delete(e);
		
	}
}
