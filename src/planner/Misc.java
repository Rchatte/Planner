package planner;

import java.io.File;
import java.util.Calendar;

public class Misc {
	public static boolean isLeapYear(int y) {
		return ((y % 4 == 0) & (y % 100 != 0 | y % 400 ==0));
	}
	
	public static String monthName(int month) {
		switch(month) {
		case 0: 
			return "January";
		case 1: 
			return "February";
		case 2: 
			return "March";
		case 3: 
			return "April";
		case 4: 
			return "May";
		case 5: 
			return "June";
		case 6: 
			return "July";
		case 7: 
			return "August";
		case 8: 
			return "September";
		case 9: 
			return "October";
		case 10: 
			return "November";
		case 11: 
			return "December";
		}
		return "error";
	}
	
	
	public static int daysInMonth(String month, boolean isLeapYear) {
		
		switch(month) {
		case "January": 
			return 31;
		case "February": 
			return isLeapYear ?  29 :  28;
		case "March": 
			return 31;
		case "April": 
			return 30;
		case "May": 
			return 31;
		case "June": 
			return 30;
		case "July": 
			return 31;
		case "August": 
			return 31;
		case "September": 
			return 30;
		case "October": 
			return 31;
		case "November": 
			return 30;
		case "December": 
			return 31;
		default :
			return 0;
		}
		
	}

	public static String dayOfWeek(int weekDay) {
		StringBuilder date = new StringBuilder();
		switch(weekDay) {
		case 1:
			date.append("Sunday");
			break;
		case 2:
			date.append("Monday");
			break;
		case 3:
			date.append("Tuesday");
			break;
		case 4:
			date.append("Wednesday");
			break;
		case 5:
			date.append("Thursday");
			break;
		case 6:
			date.append("Friday");
			break;
		case 7:
			date.append("Saturday");
			break;


		}

		return date.toString();
	}
	
	public static String getFileName(Calendar day) {
		File f = new File("./Months");
		
			if(!(f.exists())) {
				f.mkdir();}
		
		
			
		//return("C:\\Users\\Rohan\\eclipse-workspace\\planner\\src\\planner\\res\\test.csv");
		return ("./Months/"+day.get(2)+ "_"+day.get(1)+".csv");
	}
	

}
