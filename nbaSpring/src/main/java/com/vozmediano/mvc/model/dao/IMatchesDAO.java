package com.vozmediano.mvc.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.vozmediano.mvc.model.entity.Matches;

public interface IMatchesDAO extends CrudRepository<Matches, Integer> {

}
