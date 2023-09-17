package com.rainier.beans;

import java.util.List;

public class TimeSheetDailyWork {

	private int id;
	
	private int userid;
	
	private List<TimeSheet> sheet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public List<TimeSheet> getSheet() {
		return sheet;
	}

	public void setSheet(List<TimeSheet> sheet) {
		this.sheet = sheet;
	}
	
	
}
