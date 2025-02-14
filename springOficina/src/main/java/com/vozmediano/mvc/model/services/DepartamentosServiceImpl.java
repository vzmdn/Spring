package com.vozmediano.mvc.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vozmediano.mvc.entity.oficina.Departamentos;
import com.vozmediano.mvc.model.dao.IDepartamentosDAO;

@Service
public class DepartamentosServiceImpl implements IDepartamentosService {
	@Autowired
	private IDepartamentosDAO departamentosDAO;
	
	@Override
	public List<Departamentos> findAll(){
		return (List<Departamentos>) departamentosDAO.findAll();
	}
	
	@Override
	public Departamentos findById(String id) {
		return departamentosDAO.findById(id).orElse(null);
	}

	@Override
	public void save(Departamentos d) {
		departamentosDAO.save(d);
		
	}

	@Override
	public void delete(Departamentos d) {
		departamentosDAO.delete(d);
		
	}
}
