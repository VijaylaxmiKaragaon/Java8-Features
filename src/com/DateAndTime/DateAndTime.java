package com.DateAndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
		

	}

}
