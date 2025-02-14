package com.vozmediano.mvc.model.service;

import java.util.List;

import com.vozmediano.mvc.model.entity.Matches;

public interface IMatchesService {
	public List<Matches> findAll();
	public void delete(Matches m);
	public void save(Matches m);
}
