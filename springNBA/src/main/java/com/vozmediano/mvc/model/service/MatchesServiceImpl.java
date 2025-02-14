package com.vozmediano.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vozmediano.mvc.model.dao.IMatchesDAO;
import com.vozmediano.mvc.model.entity.Matches;

@Service
public class MatchesServiceImpl implements IMatchesService {
	
	@Autowired
	private IMatchesDAO matchesDAO;
	
	@Override
	public List<Matches> findAll() {
		return (List<Matches>) matchesDAO.findAll();
	}

	@Override
	public void delete(Matches m) {
		matchesDAO.delete(m);
		
	}

	@Override
	public void save(Matches m) {
		matchesDAO.save(m);
		
	}

}
