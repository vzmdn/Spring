package com.xavi.backend.nba.model.service;

import java.util.List;

import com.xavi.backend.nba.model.entity.Stats;
import com.xavi.backend.nba.model.entity.StatsId;

public interface IStatsService {
	public List<Stats> findAll();

	public Stats findById(StatsId id);

	public void save(Stats e);

	public void delete(Stats e);
}
