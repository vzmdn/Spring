package com.vozmediano.mvc.model.service;

import java.util.List;

import com.vozmediano.mvc.model.entity.Teams;

public interface ITeamsService {
	public List<Teams> findAll();
	public void save(Teams t);
	public Teams findById(String id);
	public void delete(Teams t);
}
