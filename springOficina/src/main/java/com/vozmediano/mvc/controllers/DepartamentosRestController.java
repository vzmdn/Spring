package com.vozmediano.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vozmediano.mvc.entity.oficina.Departamentos;
import com.vozmediano.mvc.model.services.IDepartamentosService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/departamentos")
public class DepartamentosRestController {

	@Autowired
	private IDepartamentosService departamentosService;
	
	@GetMapping("")
	public List<Departamentos> getAllDepartamentos(){
		return departamentosService.findAll();
	}
	@GetMapping("/{id}")
	public Departamentos getDepartamentosById(@PathVariable String id) {
		return departamentosService.findById(id);		
	}
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Departamentos create(@RequestBody Departamentos dep) {
		departamentosService.save(dep);
		return dep;
		
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		Departamentos dep = departamentosService.findById(id);
		departamentosService.delete(dep);
	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Departamentos update(@RequestBody Departamentos dep, @PathVariable String id) {
		Departamentos currentDep = departamentosService.findById(id);
		currentDep.setDnombre(dep.getDnombre());		
		departamentosService.save(currentDep);
		return currentDep;
	}
	
}
