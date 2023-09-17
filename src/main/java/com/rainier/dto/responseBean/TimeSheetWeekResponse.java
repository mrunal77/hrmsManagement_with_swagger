package com.rainier.dto.responseBean;

public class TimeSheetWeekResponse {
private short calWeek;
private short monthId;
private String monthName;


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

public short getCalWeek() {
	return calWeek;
}

public void setCalWeek(short calWeek) {
	this.calWeek = calWeek;
}

}
