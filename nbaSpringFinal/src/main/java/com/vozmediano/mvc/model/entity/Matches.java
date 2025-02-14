package com.vozmediano.mvc.model.entity;
// Generated 25 Dec 2024, 17:20:27 by Hibernate Tools 4.3.6.Final

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Matches generated by hbm2java
 */
@Entity
@Table(name = "matches", catalog = "nba")
public class Matches implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private Teams teamsByVisitorTeam;
	private Teams teamsByLocalTeam;
	private Integer pointsLocal;
	private Integer pointsVisitor;
	private String season;

	public Matches() {
	}

	public Matches(int code) {
		this.code = code;
	}

	public Matches(int code, Teams teamsByVisitorTeam, Teams teamsByLocalTeam, Integer pointsLocal,
			Integer pointsVisitor, String season) {
		this.code = code;
		this.teamsByVisitorTeam = teamsByVisitorTeam;
		this.teamsByLocalTeam = teamsByLocalTeam;
		this.pointsLocal = pointsLocal;
		this.pointsVisitor = pointsVisitor;
		this.season = season;
	}

	@Id

	@Column(name = "code", unique = true, nullable = false)
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visitor_team")
	public Teams getTeamsByVisitorTeam() {
		return this.teamsByVisitorTeam;
	}

	public void setTeamsByVisitorTeam(Teams teamsByVisitorTeam) {
		this.teamsByVisitorTeam = teamsByVisitorTeam;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "local_team")
	public Teams getTeamsByLocalTeam() {
		return this.teamsByLocalTeam;
	}

	public void setTeamsByLocalTeam(Teams teamsByLocalTeam) {
		this.teamsByLocalTeam = teamsByLocalTeam;
	}

	@Column(name = "points_local")
	public Integer getPointsLocal() {
		return this.pointsLocal;
	}

	public void setPointsLocal(Integer pointsLocal) {
		this.pointsLocal = pointsLocal;
	}

	@Column(name = "points_visitor")
	public Integer getPointsVisitor() {
		return this.pointsVisitor;
	}

	public void setPointsVisitor(Integer pointsVisitor) {
		this.pointsVisitor = pointsVisitor;
	}

	@Column(name = "season", length = 5)
	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

}
