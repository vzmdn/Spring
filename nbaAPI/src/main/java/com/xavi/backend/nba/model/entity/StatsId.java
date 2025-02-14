package com.xavi.backend.nba.model.entity;
// Generated 5 dic 2023 9:24:52 by Hibernate Tools 4.3.6.Final

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * StatsId generated by hbm2java
 */
@Embeddable
public class StatsId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String season;
	private int player;

	public StatsId() {
	}

	public StatsId(String season, int player) {
		this.season = season;
		this.player = player;
	}

	@Column(name = "season", nullable = false, length = 5)
	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	@Column(name = "player", nullable = false)
	public int getPlayer() {
		return this.player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StatsId))
			return false;
		StatsId castOther = (StatsId) other;

		return ((this.getSeason() == castOther.getSeason()) || (this.getSeason() != null
				&& castOther.getSeason() != null && this.getSeason().equals(castOther.getSeason())))
				&& (this.getPlayer() == castOther.getPlayer());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSeason() == null ? 0 : this.getSeason().hashCode());
		result = 37 * result + this.getPlayer();
		return result;
	}

}
