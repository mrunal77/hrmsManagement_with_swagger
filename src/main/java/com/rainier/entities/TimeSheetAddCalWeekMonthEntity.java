package com.rainier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="timeSheet_add_calWeek_month")
public class TimeSheetAddCalWeekMonthEntity {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="cal_week")
	private short calWeek;
	@Column(name="month_id")
	private short monthId;
	@Column(name="month_name")
	private String monthName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public short getCalWeek() {
		return calWeek;
	}
	public void setCalWeek(short calWeek) {
		this.calWeek = calWeek;
	}
	public short getMonthId() {
		return monthId;
	}
	public void setMonthId(short monthId) {
		this.monthId = monthId;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	
	
}
