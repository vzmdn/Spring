package com.vozmediano.mvc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vozmediano.mvc.entity.oficina.Departamentos;
import com.vozmediano.mvc.entity.oficina.Empleados;
import com.vozmediano.mvc.model.exceptions.EmpleadoNotFoundException;
import com.vozmediano.mvc.model.services.IEmpleadosService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/empleados")
public class EmpleadosRestController {

	@Autowired
	private IEmpleadosService empleadosService;
	@Autowired
	//private IDepartamentosService departamentosService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllEmpleados(){
		List<Empleados> res = empleadosService.findAll();
		if(!res.isEmpty()) return new ResponseEntity<List<Empleados>>(res, HttpStatus.OK);
		else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		//else return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public Empleados getEmpleadosById(@PathVariable int id) {
		return empleadosService.findById(id);		
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleados create(@RequestBody Empleados emp) {
		empleadosService.save(emp);
		return emp;
		
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		Empleados emp = empleadosService.findById(id);
		empleadosService.delete(emp);
	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleados update(@RequestBody Empleados emp, @PathVariable int id) {
		Empleados currentEmp = empleadosService.findById(id);
		currentEmp.setApellido(emp.getApellido());
		currentEmp.setComision(emp.getComision());
		currentEmp.setDir(emp.getDir());
		currentEmp.setFechaAlta(emp.getFechaAlta());
		currentEmp.setOficio(emp.getOficio());
		currentEmp.setSalario(emp.getSalario());		
		empleadosService.save(currentEmp);
		return currentEmp;
	}
	
	@PutMapping("/{id}/departamento")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleados updateDepartament(@RequestBody Departamentos dep, @PathVariable int id) {
		Empleados emp = empleadosService.findById(id);
		//Departamentos depFromDB = departamentosService.findById(depId);
		emp.setDepartamentos(dep);
		empleadosService.save(emp);
		return emp;
	}
	
	@ExceptionHandler (EmpleadoNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(EmpleadoNotFoundException enfe){
		Map<String,Object> response = new HashMap<>();
		response.put("message", enfe.getMessage());
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
	}
	
}
