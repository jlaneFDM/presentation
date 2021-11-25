package com.datetimepresentation.www;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class ProjectDemo {

	public static void demoLocalDate() {
		
		System.out.println("\n");

		LocalDate currentDate = LocalDate.now();
		System.out.println("LocalDate.now() instance: " + currentDate);
		
		LocalDate dateOfExample = LocalDate.of(2021, Month.NOVEMBER, 26);
		System.out.println("LocalDate.of instance: " + dateOfExample);
		
		/*the dateOfExample by itself might not seem very useful, but you can use
		 it with temporal adjusters with a LocalDate object to track certain dates*/
		
		TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.FRIDAY);
		
		LocalDate nextFriday = dateOfExample.with(temporalAdjuster);
		System.out.println("Temporal adjuster example: " + nextFriday);
	}
	
	public static void demoLocalTime() {
		
		System.out.println("\n");
		
		//get the local time based off your machine
		LocalTime localTime = LocalTime.now();
		System.out.println("Unformatted local time: " + localTime);
		
		//subtract an hour from the local time
		LocalTime minusOneHour = localTime.minusHours(1);
		System.out.println("The unformatted local time minus 1 hour: " + minusOneHour);
		
		//Reformatting the local time 
		LocalTime currentTime = LocalTime.parse(localTime.toString());
		DateTimeFormatter dtf1 =  DateTimeFormatter.ofPattern("HH:mm");
		String formattedLocalTime = currentTime.format(dtf1);
		System.out.println("Formatted local time: " + formattedLocalTime);
		
		//Retrieving the local time in another time zone 
		ZoneId asiaShanghai = ZoneId.of("Asia/Shanghai"); 
		LocalTime shanghaiTime = LocalTime.now(asiaShanghai);
		
		System.out.println("Unformatted Shanghai time: " + shanghaiTime);
		
		//format Shanghai time
		LocalTime currentTimeInShanghai = LocalTime.parse(shanghaiTime.toString());
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
		String formattedShanghaiTime = currentTimeInShanghai.format(dtf2);
		
		System.out.println("Formatted Shanghai time: " + formattedShanghaiTime);
		
		System.out.println("It's " + formattedShanghaiTime  + " in Shanghai "
			+ "and " + formattedLocalTime + " in Kitchener-Waterloo.");
}
	
	public static void demoLocalDateTime() {
		
		System.out.println("demolocalDateTime called\n");
		
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("The date and time in Kitchener-Waterloo are: " + localDateTime);
		
		ZoneId europeParis = ZoneId.of("Europe/Paris");
		LocalDateTime localDateTimeParis = LocalDateTime.now(europeParis);
		System.out.println("The date and time in Paris are: " + localDateTimeParis);
		
		//As in the previous examples LocalDateTime can be formatted
		
		DateTimeFormatter dtfKitchener = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm:ss a");
		String formattedKitchenerTime = localDateTime.format(dtfKitchener);
		
		DateTimeFormatter dtfParis = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy hh:mm:ss a");
		String formattedParisTime = localDateTimeParis.format(dtfParis);
		
		System.out.println("Paris: " + formattedParisTime + "\n" + "Kitchener: " + formattedKitchenerTime);
		
		//You can also get dates in the future
		System.out.println("The local date-time in 6 months: " + LocalDateTime.now().plusMonths(6));
		
		
		//You can also use temporal adjusters to track down local date-times
		LocalDateTime currentDate = LocalDateTime.of(2021, Month.NOVEMBER, 26, 9, 0);
		TemporalAdjuster temporalAdjuster = TemporalAdjusters.previous(DayOfWeek.FRIDAY);
		LocalDateTime lastFriday = currentDate.with(temporalAdjuster);
		System.out.println(lastFriday);
		
		
	};

	public static void main(String[] args) {

		boolean isRunning = true;
		
		while (isRunning) {
			System.out.println("\n");
			System.out.print("Press 1 to demo LocalDate. \n" + 
			"Press 2 to demo LocalTime. \n"
					+ "Press 3 to demo LocatDateTime. \n" 
					+ "Press 4 to exit. \n"
					+ "Input: ");

			var scanner = new Scanner(System.in);

			String userChoice = scanner.nextLine();

			switch (userChoice) {
			case "1": {
				ProjectDemo.demoLocalDate();
				break;
			}
			case "2": {
				ProjectDemo.demoLocalTime();
				break;
			}
			case "3": {
				ProjectDemo.demoLocalDateTime();
				break;
			}
			case "4": {
				isRunning = false;
				System.out.println("Goodbye");
				scanner.close();
				break;
			}
			default: 
				System.out.println("Invalid choice");
			}
		}
	}
}
