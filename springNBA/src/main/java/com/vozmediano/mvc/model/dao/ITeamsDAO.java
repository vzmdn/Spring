package com.vozmediano.mvc.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.vozmediano.mvc.model.entity.Teams;

public interface ITeamsDAO extends CrudRepository<Teams, String> {

}
