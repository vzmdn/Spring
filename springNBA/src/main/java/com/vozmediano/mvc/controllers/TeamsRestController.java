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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vozmediano.mvc.model.entity.Matches;
import com.vozmediano.mvc.model.entity.Players;
import com.vozmediano.mvc.model.entity.Teams;
import com.vozmediano.mvc.model.exceptions.TeamNotFoundException;
import com.vozmediano.mvc.model.service.IMatchesService;
import com.vozmediano.mvc.model.service.IPlayersService;
import com.vozmediano.mvc.model.service.ITeamsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nba/teams")
public class TeamsRestController {
	@Autowired
	private ITeamsService teamsService;

	@Autowired
	private IMatchesService matchesService;

	@Autowired
	private IPlayersService playersService;

	// Not found Exception
	@ExceptionHandler(TeamNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> handleException(TeamNotFoundException tnfe) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", "404 - NOT FOUND");
		response.put("error", tnfe.getMessage());
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

	// Get all teams
	@GetMapping("")
	public ResponseEntity<?> getAllTeams() {
		List<Teams> teams = teamsService.findAll();
		if (teams.isEmpty())
			return ResponseEntity.noContent().build();
		else
			return new ResponseEntity<List<Teams>>(teams, HttpStatus.OK);
	}

	// Create new team
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Teams create(@RequestBody Teams t) {
		teamsService.save(t);
		return t;
	}

	// Get one team by id
	@GetMapping("/{id}")
	public Teams getPlayersById(@PathVariable String id) {
		return teamsService.findById(id);
	}

	// Delete one team by id
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable String id) {
		Teams t = null;
		try {
			t = teamsService.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (t == null) {
			return ResponseEntity.noContent().build();
		} else {
			for (Players p : t.getPlayerses()) {
				p.setTeams(null);
				playersService.save(p);
			}
			for (Matches vm : t.getMatchesesForVisitorTeam())
				matchesService.delete(vm);
			for (Matches lm : t.getMatchesesForLocalTeam())
				matchesService.delete(lm);
			teamsService.delete(t);
			return new ResponseEntity<Teams>(t, HttpStatus.OK);
		}
	}

	// Update one team by id
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Teams update(@RequestBody Teams t, @PathVariable String id) {
		Teams currentT = teamsService.findById(id);
		currentT.setCity(t.getCity());
		currentT.setConference(t.getConference());
		currentT.setDivision(t.getDivision());
		teamsService.save(currentT);
		return currentT;
	}

	// Get all players of team id
	@GetMapping("/{id}/players")
	public ResponseEntity<?> getPlayers(@PathVariable String id) {
		Teams t = teamsService.findById(id);
		Set<Players> players = t.getPlayerses();
		if(players.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<Set<Players>>(players, HttpStatus.OK);
		}
	}

	// Create new player for team id
	@PostMapping("/{id}/players")
	@ResponseStatus(HttpStatus.CREATED)
	public Players createPlayerForTeam(@RequestBody Players p, @PathVariable String id) {
		Teams t = teamsService.findById(id);
		p.setTeams(t);
		t.getPlayerses().add(p);
		playersService.save(p);
		return p;
	}

	// Get all matches as local of team id
	@GetMapping("/{id}/local")
	public ResponseEntity<?> getLocalMatches(@PathVariable String id) {
		Teams t = teamsService.findById(id);
		Set<Matches> matches = t.getMatchesesForLocalTeam();
		if(matches.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<Set<Matches>>(matches, HttpStatus.OK);
		}
	}

	// Get all matches as visitor of team id
	@GetMapping("/{id}/visitor")
	public ResponseEntity<?> getVisitorMatches(@PathVariable String id) {
		Teams t = teamsService.findById(id);
		Set<Matches> matches = t.getMatchesesForVisitorTeam();
		if(matches.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<Set<Matches>>(matches, HttpStatus.OK);
		}
	}

	// Create new match using team id_l as local and id_v as visitor
	@PostMapping("/{id_l}/local/{id_v}")
	@ResponseStatus(HttpStatus.CREATED)
	public Matches createLocalMatch(@RequestBody Matches m, @PathVariable String id_l, @PathVariable String id_v) {
		Teams localTeam = teamsService.findById(id_l);
		Teams visitorTeam = teamsService.findById(id_v);
		m.setTeamsByLocalTeam(localTeam);
		m.setTeamsByVisitorTeam(visitorTeam);
		matchesService.save(m);
		localTeam.getMatchesesForLocalTeam().add(m);
		visitorTeam.getMatchesesForVisitorTeam().add(m);
		return m;
	}

	// Create new match using team id_l as local and id_v as visitor
	@PostMapping("/{id_v}/visitor/{id_l}")
	@ResponseStatus(HttpStatus.CREATED)
	public Matches createVisitorMatch(@RequestBody Matches m, @PathVariable String id_v, @PathVariable String id_l) {
		Teams visitorTeam = teamsService.findById(id_v);
		Teams localTeam = teamsService.findById(id_l);
		m.setTeamsByVisitorTeam(visitorTeam);
		m.setTeamsByLocalTeam(localTeam);
		matchesService.save(m);
		visitorTeam.getMatchesesForVisitorTeam().add(m);
		localTeam.getMatchesesForLocalTeam().add(m);
		return m;
	}

}
