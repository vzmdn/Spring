package com.vozmediano.mvc.model.service;

import java.util.List;

import com.vozmediano.mvc.model.entity.Players;

public interface IPlayersService {
	public List<Players> findAll();
	public void save(Players p);
	public Players findById(int id);
	public void delete(Players p);
}
