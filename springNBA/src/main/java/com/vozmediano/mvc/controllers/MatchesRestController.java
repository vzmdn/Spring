package com.vozmediano.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vozmediano.mvc.model.entity.Matches;
import com.vozmediano.mvc.model.service.IMatchesService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/nba/matches")
public class MatchesRestController {
	@Autowired
	private IMatchesService matchesService;
	
	@GetMapping("")
	public List<Matches> getAllMatches(){
		return matchesService.findAll();
	}
	
}
