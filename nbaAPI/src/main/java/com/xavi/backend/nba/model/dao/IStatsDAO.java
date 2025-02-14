package com.xavi.backend.nba.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.xavi.backend.nba.model.entity.Stats;
import com.xavi.backend.nba.model.entity.StatsId;

public interface IStatsDAO extends CrudRepository<Stats, StatsId>{

}
