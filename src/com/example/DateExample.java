package com.example;

import java.time.LocalDate;

public class DateExample {
	public static void main(String[] args) {
		LocalDate ac = LocalDate.of(1,  1, 1);
		System.out.println(ac.getDayOfWeek());
		
		LocalDate epoch = LocalDate.of(1970,  1, 1);
		System.out.println(epoch.getDayOfWeek());
	}
}
