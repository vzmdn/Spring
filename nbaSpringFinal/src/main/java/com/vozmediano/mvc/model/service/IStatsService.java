package com.vozmediano.mvc.model.service;

import java.util.List;

import com.vozmediano.mvc.model.entity.Stats;
import com.vozmediano.mvc.model.entity.StatsId;

public interface IStatsService {
	public List<Stats> findAll();
	public void save(Stats s);
	public Stats findStatsFromSeason(StatsId s);
	public void delete(Stats s);
	
}
