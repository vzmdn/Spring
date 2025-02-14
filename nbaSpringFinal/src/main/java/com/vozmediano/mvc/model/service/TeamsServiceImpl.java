package com.vozmediano.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vozmediano.mvc.model.dao.ITeamsDAO;
import com.vozmediano.mvc.model.entity.Teams;
import com.vozmediano.mvc.model.exceptions.TeamNotFoundException;

@Service
public class TeamsServiceImpl implements ITeamsService {
	
	@Autowired
	private ITeamsDAO teamsDAO;
	
	@Override
	public List<Teams> findAll() {
		return (List<Teams>) teamsDAO.findAll();
	}

	@Override
	public void save(Teams t) {
		teamsDAO.save(t);
		
	}

	@Override
	public Teams findById(String id) {
		return teamsDAO.findById(id).orElseThrow(() -> new TeamNotFoundException (id));
	}

	@Override
	public void delete(Teams t) {
		teamsDAO.delete(t);
		
	}

}
