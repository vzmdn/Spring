package com.vozmediano.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vozmediano.mvc.model.entity.Stats;
import com.vozmediano.mvc.model.entity.StatsId;
import com.vozmediano.mvc.model.service.IStatsService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/nba/stats")
public class StatsRestController {
	@Autowired
	private IStatsService statsService;
	
	@GetMapping("")
	public List<Stats> getAllStats(){
		return statsService.findAll();
	}
	
	@GetMapping("/{id}")
	public Stats getStatsFromSeason(StatsId s) {
		return statsService.findStatsFromSeason(s);
	}
	
	
	
}
