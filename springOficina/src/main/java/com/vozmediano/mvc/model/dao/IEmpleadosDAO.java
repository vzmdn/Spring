package com.vozmediano.mvc.model.dao;



import org.springframework.data.repository.CrudRepository;

import com.vozmediano.mvc.entity.oficina.Empleados;

public interface IEmpleadosDAO extends CrudRepository<Empleados, Integer>{

}
