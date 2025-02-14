package com.vozmediano.mvc.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vozmediano.mvc.model.entity.Players;

public interface IPlayersDAO extends CrudRepository<Players, Integer> {
	List<Players> findByPosition(String pos);
}
