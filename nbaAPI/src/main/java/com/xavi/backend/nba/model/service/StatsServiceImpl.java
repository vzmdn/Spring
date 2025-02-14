package com.xavi.backend.nba.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xavi.backend.nba.model.dao.IStatsDAO;
import com.xavi.backend.nba.model.entity.Stats;
import com.xavi.backend.nba.model.entity.StatsId;

@Service
public class StatsServiceImpl implements IStatsService{

	@Autowired
	private IStatsDAO statsDAO;
	
	@Override
	public List<Stats> findAll() {
		return (List<Stats>)statsDAO.findAll();
	}

	@Override
	public Stats findById(StatsId id) {
		return statsDAO.findById(id).orElse(null);
	}

	@Override
	public void save(Stats e) {
		statsDAO.save(e);
		
	}

	@Override
	public void delete(Stats e) {
		statsDAO.delete(e);
		
	}

}
