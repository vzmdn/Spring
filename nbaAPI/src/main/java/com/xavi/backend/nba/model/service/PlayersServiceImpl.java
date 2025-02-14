package com.xavi.backend.nba.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xavi.backend.nba.model.dao.IPlayersDAO;
import com.xavi.backend.nba.model.entity.Players;
@Service
public class PlayersServiceImpl implements IPlayersService{

	@Autowired
	private IPlayersDAO playersDAO;
	
	@Override
	public List<Players> findAll() {
		return (List<Players>)playersDAO.findAll();
	}

	@Override
	public Players findById(int id) {
		return playersDAO.findById(id).orElse(null);
	}

	@Override
	public void save(Players e) {
		playersDAO.save(e);
		
	}

	@Override
	public void delete(Players e) {
		playersDAO.delete(e);
		
	}

	@Override
	public List<Players> findByPosition(String pos) {
		return (List<Players>)playersDAO.findByPosition(pos);
	}

}
