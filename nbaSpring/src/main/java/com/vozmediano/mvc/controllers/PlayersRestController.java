package com.vozmediano.mvc.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vozmediano.mvc.model.entity.Players;
import com.vozmediano.mvc.model.entity.Stats;
import com.vozmediano.mvc.model.entity.StatsId;
import com.vozmediano.mvc.model.exceptions.PlayerNotFoundException;
import com.vozmediano.mvc.model.exceptions.StatNotFoundException;
import com.vozmediano.mvc.model.service.IPlayersService;
import com.vozmediano.mvc.model.service.IStatsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nba/players")
public class PlayersRestController {
	@Autowired
	private IPlayersService playersService;

	@Autowired
	private IStatsService statsService;

	// Not found Exceptions
	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(PlayerNotFoundException pnfe) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "404 - NOT FOUND");
		response.put("error", pnfe.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StatNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(StatNotFoundException snfe) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "404 - NOT FOUND");
		response.put("error", snfe.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}

	// Bad request Exception
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(HttpMessageNotReadableException notReadable) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "400 - BAD REQUEST");
		response.put("message", "Data is not valid");
		response.put("error", notReadable.getMessage());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}

	// Get all players
	@GetMapping("")
	public ResponseEntity<?> getAllPlayers(@RequestParam(required = false) String position) {
		List<Players> players = null;
		if (position != null) {
			players = playersService.findByPosition(position);
		} else {
			players = playersService.findAll();
		}

		if (players.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<List<Players>>(players, HttpStatus.OK);
		}
	}

	// Create new player
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Players create(@RequestBody Players p) {
		playersService.save(p);
		return p;
	}

	// Get one player id
	@GetMapping("/{id}")
	public Players getPlayersById(@PathVariable int id) {
		return playersService.findById(id);
	}

	// Delete one player id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable int id) {
		Players p = null;
		try {
			p = playersService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (p == null) {
			return ResponseEntity.noContent().build();
		} else {
			for (Stats s : p.getStatses())
				statsService.delete(s);
			playersService.delete(p);
			return new ResponseEntity<Players>(p, HttpStatus.OK);
		}

	}

	// Update one player id
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> update(@RequestBody Players p, @PathVariable int id) {
		Players currentP = playersService.findById(id);
		if (currentP == null) {
			return ResponseEntity.noContent().build();
		} else {
			currentP.setCode(p.getCode());
			currentP.setName(p.getName());
			currentP.setOrigin(p.getOrigin());
			currentP.setHeight(p.getHeight());
			currentP.setWeight(p.getWeight());
			currentP.setPosition(p.getPosition());
			currentP.setSalary(p.getSalary());
			playersService.save(currentP);
			return new ResponseEntity<Players>(currentP, HttpStatus.OK);
		}
	}

	// Get all stats of player id
	@GetMapping("/{id}/stats")
	public Set<Stats> findPlayerStats(@PathVariable int id) {
		Players p = playersService.findById(id);
		return (Set<Stats>) p.getStatses();
	}

	// Create new stat for the player id
	@PostMapping("/{id}/stats")
	@ResponseStatus(HttpStatus.CREATED)
	public Stats createStat(@RequestBody Stats s, @PathVariable int id) {
		Players currentP = playersService.findById(id);
		statsService.save(s);
		currentP.getStatses().add(s);
		playersService.save(currentP);
		return s;
	}

	// Get stats of player id_p for the season id_s
	@GetMapping("/{id_p}/stats/{id_s}")
	public Stats findSeasonStats(@PathVariable int id_p, @PathVariable String id_s) {
		@SuppressWarnings("unused")
		Players p = playersService.findById(id_p); // solo se inicializa p para que salte la excepción not found
		StatsId statsId = new StatsId(id_s.replace('-', '/'), id_p);
		Stats stats = statsService.findStatsFromSeason(statsId);
		return stats;
	}

	// Delete stats of player id_p for the season id_s
	@DeleteMapping("/{id_p}/stats/{id_s}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable int id_p, @PathVariable String id_s) {
		Players p = null;
		try {
			p = playersService.findById(id_p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (p == null) {
			return ResponseEntity.noContent().build();
		} else {
			StatsId statsId = new StatsId(id_s, id_p);
			Stats stats = statsService.findStatsFromSeason(statsId);
			if (stats == null) {
				return ResponseEntity.noContent().build();
			} else {
				statsService.delete(stats);
				return new ResponseEntity<Stats>(stats, HttpStatus.OK);
			}
		}

	}

	// Update stats of player id_p for the season id_s
	@PutMapping("/{id_p}/stats/{id_s}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> updateStats(@RequestBody Stats s, @PathVariable String id_s, @PathVariable int id_p) {
		Players currentP = playersService.findById(id_p);
		StatsId statsId = new StatsId(id_s, id_p);
		Set<Stats> oldStats = currentP.getStatses();
		Stats updatedStat = null;
		for (Stats oldStat : oldStats) {
			if (oldStat.getId().equals(statsId)) {
				oldStat.setPointsPerMatch(s.getPointsPerMatch());
				oldStat.setAssistancesPerMatch(s.getAssistancesPerMatch());
				oldStat.setBlocksPerMatch(s.getBlocksPerMatch());
				oldStat.setReboundPerMatch(s.getReboundPerMatch());
				updatedStat = oldStat;
				break;
			}
		}
		if (updatedStat == null) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<Stats>(updatedStat, HttpStatus.OK);
		}
	}

}
