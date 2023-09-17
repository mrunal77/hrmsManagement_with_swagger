package com.rainier.utility;

import org.apache.log4j.Logger;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static java.time.DayOfWeek.*;


import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class CurrentWeekDates {
	private final static Logger logger = Logger.getLogger(CurrentWeekDates.class);

	public short currentWeekNo() {
		logger.info("currentWeekNo() method");
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		// System.out.println("Week number:" + calendar.get(Calendar.WEEK_OF_YEAR));
		return (short) calendar.get(Calendar.WEEK_OF_YEAR);
	}

	public HashMap<String, String> getDateDetails(Date todayDate) {
		logger.info("getDateDetails() method");
		HashMap<String, String> DateHM = new HashMap<String, String>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = todayDate.toInstant();
		LocalDate today = instant.atZone(defaultZoneId).toLocalDate();
		// LocalDate.of(2018, 9, 19);
		// LocalDate.now();
		today = today.plusDays(1);
		LocalDate sunday = today.with(previousOrSame(SUNDAY));
		LocalDate saturday = today.with(nextOrSame(SATURDAY));
		LocalDate monday = sunday.plusDays(1);
		LocalDate tuesday = sunday.plusDays(2);
		LocalDate wednesday = sunday.plusDays(3);
		LocalDate thursday = sunday.plusDays(4);
		LocalDate friday = sunday.plusDays(5);

		LocalDate nextweek = sunday.plusWeeks(1);
		LocalDate nsunday = nextweek.with(previousOrSame(SUNDAY));
		LocalDate nsaturday = nextweek.with(nextOrSame(SATURDAY));

		LocalDate previousweek = monday.minusWeeks(1);
		LocalDate psunday = previousweek.with(previousOrSame(SUNDAY));
		LocalDate psaturday = previousweek.with(nextOrSame(SATURDAY));

		DateHM.put("currentWeek", sunday.format(format) + "-" + saturday.format(format));
		DateHM.put("previousWeek", psunday.format(format) + "-" + psaturday.format(format));
		DateHM.put("nextWeek", nsunday.format(format) + "-" + nsaturday.format(format));
		DateHM.put("monday", monday.format(format));
		DateHM.put("tuesday", tuesday.format(format));
		DateHM.put("wednesday", wednesday.format(format));
		DateHM.put("thursday", thursday.format(format));
		DateHM.put("friday", friday.format(format));
		DateHM.put("saturday", saturday.format(format));
		DateHM.put("sunday", sunday.format(format));
		DateHM.put("year", String.valueOf(today.getYear()));
		DateHM.put("month", String.valueOf(today.getMonthValue()));
		// String y = today.getYear();

		// System.out.println("year: " + today.getYear());
		// System.out.println("Monday of the Week: " + monday.format(format));
		// System.out.println("Sunday of the Week: " + tuesday);
		// System.out.println("Monday of the Week: " + wednesday);
		// System.out.println("Sunday of the Week: " + thursday);
		// System.out.println("Monday of the Week: " + friday);
		// System.out.println("Sunday of the Week: " + saturday);

		// System.out.println("DateHM: " + DateHM);
		return DateHM;
	}
}