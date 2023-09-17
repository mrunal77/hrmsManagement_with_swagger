package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_timezones")
public class Tbl_TimezonesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "timezone")
	private String timeZone;

	@Column(name = "timezone_abbr")
	private String timeZoneAbbr;

	@Column(name = "offset_value")
	private String offsetValue;

	@Column(name = "isactive")
	private int isActive;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneAbbr() {
		return timeZoneAbbr;
	}

	public void setTimeZoneAbbr(String timeZoneAbbr) {
		this.timeZoneAbbr = timeZoneAbbr;
	}

	public String getOffsetValue() {
		return offsetValue;
	}

	public void setOffsetValue(String offsetValue) {
		this.offsetValue = offsetValue;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

}
