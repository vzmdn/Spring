package com.vozmediano.mvc.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.vozmediano.mvc.model.entity.Stats;
import com.vozmediano.mvc.model.entity.StatsId;

public interface IStatsDAO extends CrudRepository<Stats, StatsId> {

}
