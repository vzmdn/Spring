package com.vozmediano.mvc.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.vozmediano.mvc.model.entity.Players;

public interface IPlayersDAO extends CrudRepository<Players, Integer> {

}
