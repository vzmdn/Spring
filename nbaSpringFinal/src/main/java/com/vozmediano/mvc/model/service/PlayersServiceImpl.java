package com.vozmediano.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vozmediano.mvc.model.dao.IPlayersDAO;
import com.vozmediano.mvc.model.entity.Players;
import com.vozmediano.mvc.model.exceptions.PlayerNotFoundException;

@Service
public class PlayersServiceImpl implements IPlayersService {
	
	@Autowired
	private IPlayersDAO playersDAO;
	
	@Override
	public List<Players> findAll() {
		return (List<Players>) playersDAO.findAll();
	}

	@Override
	public void save(Players p) {
		playersDAO.save(p);
	}

	@Override
	public Players findById(int id) {
		return playersDAO.findById(id).orElseThrow(() -> new PlayerNotFoundException(id));
	}

	@Override
	public void delete(Players p) {
		playersDAO.delete(p);
	}

	

}
