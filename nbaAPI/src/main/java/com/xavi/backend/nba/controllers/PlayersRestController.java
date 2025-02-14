package com.xavi.backend.nba.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xavi.backend.nba.model.entity.Players;
import com.xavi.backend.nba.model.entity.Stats;
import com.xavi.backend.nba.model.entity.StatsId;
import com.xavi.backend.nba.model.service.IPlayersService;
import com.xavi.backend.nba.model.service.IStatsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nba/players")
public class PlayersRestController {

	@Autowired
	private IPlayersService playerService;
	@Autowired
	private IStatsService statsService;	
	
	@GetMapping({"","/"})
	public List<Players> getAllPlayers(
			@RequestParam(required=false) String position) {
		if (position == null) {
			return playerService.findAll();
		} else {
			return playerService.findByPosition(position);
		}
	}
	
	
	@PostMapping({"","/"})
	@ResponseStatus(HttpStatus.CREATED)
	public Players create(@RequestBody Players e) {
		playerService.save(e);
		return e;
	}
	@GetMapping("/{id}")
	public Players getPlayerById(@PathVariable int id) {
		return playerService.findById(id);
	}
	


	@GetMapping("/{id}/stats")
	public Set<Stats> getStatsOfPlayerById(@PathVariable int id) {
		return  playerService.findById(id).getStatses();
	}
	
	@PostMapping("/{id}/stats")
	@ResponseStatus(HttpStatus.CREATED)
	public Stats createStat(@PathVariable int id, @RequestBody Stats s) {
		Players p = playerService.findById(id);
		s.setPlayers(p);
		s.getId().setPlayer(id);
		statsService.save(s);
		return  s;
	}
	
	@GetMapping("/{id_p}/stats/{id_s}")
	public Stats getStatsByIdOfPlayerById(@PathVariable int id_p, 
			@PathVariable String id_s) {
		
		StatsId newStatId= new StatsId(id_s.replace('-', '/'), id_p);
		
		return statsService.findById(newStatId);
	}
	
	
	@DeleteMapping("/{id_p}/stats/{id_s}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void DeleteStatsByIdOfPlayerById(@PathVariable int id_p, 
			@PathVariable String id_s) {
		Stats s= statsService.findById(
						new StatsId(id_s.replace('-', '/'), id_p));
		statsService.delete(s);
	}
	@PutMapping("/{id_p}/stats/{id_s}")
	@ResponseStatus(HttpStatus.CREATED)
	public Stats UpdateStatsByIdOfPlayerById(@PathVariable int id_p, 
			@PathVariable String id_s, @RequestBody Stats s) {
		Stats current_s= statsService.findById(
						new StatsId(id_s.replace('-', '/'), id_p));
		current_s.setAssistancesPerMatch(s.getAssistancesPerMatch());
		current_s.setBlocksPerMatch(s.getBlocksPerMatch());
		current_s.setPointsPerMatch(s.getPointsPerMatch());
		current_s.setReboundPerMatch(s.getReboundPerMatch());
		statsService.save(current_s);
		return current_s;
	}
}
