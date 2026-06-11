package com.DateAndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateAndTime {

	public static void main(String[] args) {
		
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getDayOfWeek());
		System.out.println(ldt.getDayOfYear());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMonthValue());
		LocalTime lt = LocalTime.now();
		System.out.println(lt);
		
		String date = "13-07-2026";
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate convertedDate = LocalDate.parse(date,f);
		System.out.println(convertedDate);
		
		//TO OPTAIN the difference between date
		Period p =Period.between(ld, convertedDate);
		long totalDays = ChronoUnit.DAYS.between(ld, convertedDate);
		System.out.println(totalDays);
	}

}
