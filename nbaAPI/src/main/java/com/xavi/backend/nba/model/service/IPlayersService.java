package com.xavi.backend.nba.model.service;

import java.util.List;

import com.xavi.backend.nba.model.entity.Players;

public interface IPlayersService {
	public List<Players> findAll();
	
	public List<Players> findByPosition(String pos);
	
	public Players findById(int id);

	public void save(Players e);

	public void delete(Players e);
}
