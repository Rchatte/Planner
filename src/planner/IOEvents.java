package planner;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class IOEvents {


	public void write(Calendar day,ArrayList<Event> events,String info ) {
		File file = new File(Misc.getFileName(day));
		ArrayList<String> lines = new ArrayList<String>();
		
		
		
		try {
			Scanner read = new Scanner(file);
			
			
			while (read.hasNextLine()) {
				
				lines.add(read.nextLine());
				
			}
			
			StringBuilder temp = new StringBuilder(info);
			
			
			for (int i = 0; i < events.size(); i++) {
				temp.append(","+events.get(i).type +"<_D_X>"+events.get(i).Details);
			}
			
			lines.set(day.get(5) - 1, temp.toString().replace("\n", "(NexT_Line_)"));
			
			
			
			
			
			PrintWriter write = new PrintWriter(file);
			
			for (int i = 0; i < lines.size(); i++) {
				write.append(lines.get(i) + "\n");
	
			}
			
			read.close();
			write.close();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String[] read(Calendar day) {

		int max = Misc.daysInMonth(Misc.monthName(day.get(2)), Misc.isLeapYear(day.get(1)));
		File file = new File(Misc.getFileName(day));
		Scanner read;

		try {
			if(!(file.exists())){
				PrintWriter print = new PrintWriter(file);


				for(int i = 0; i < max; i++) {

					print.append( "N/A\n");

				}

				print.close();
				
				
			}}


			catch (Exception e) {
				
			}
		
		try {
			read = new Scanner(file);
			
			
			for (int i = 0; i < day.get(day.DAY_OF_MONTH) - 1; i++) {
				read.nextLine();
			}
			
			String[] temp = read.nextLine().split(",");
			
			
			read.close();
			return temp;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		String[] na = {"Error"};
		
		return na;
		
		}

	






}






