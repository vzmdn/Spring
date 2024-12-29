package com.vozmediano.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vozmediano.mvc.model.dao.IStatsDAO;
import com.vozmediano.mvc.model.entity.Stats;
import com.vozmediano.mvc.model.entity.StatsId;

@Service
public class StatsServiceImpl implements IStatsService {
	
	@Autowired
	private IStatsDAO statsDAO;
	
	@Override
	public List<Stats> findAll() {
		return (List<Stats>) statsDAO.findAll();
	}

	@Override
	public void save(Stats s) {
		statsDAO.save(s);
		
	}

	@Override
	public Stats findStatsFromSeason(StatsId s) {
		return statsDAO.findById(s).orElse(null);
	}

	@Override
	public void delete(Stats s) {
		statsDAO.delete(s);
		
	}

	
}
