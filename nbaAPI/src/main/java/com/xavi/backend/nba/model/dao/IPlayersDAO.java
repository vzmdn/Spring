package com.xavi.backend.nba.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.xavi.backend.nba.model.entity.Players;

public interface IPlayersDAO extends CrudRepository<Players, Integer>{

	List<Players> findByPosition(String pos);

}
