package planner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class InfoPane{
	Date date;
	String info;
	GridPane gPane = new GridPane();
	ScrollPane infoPane = new ScrollPane(gPane);
	ArrayList<Event> events = new ArrayList<Event>();
	Label lblDate = new Label();
	Label lblInfo = new Label();
	Calendar day;
			
	 InfoPane(){
		super();
		
		gPane.setVgap(25);
		gPane.setHgap(50);
		lblDate.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD, FontPosture.ITALIC, 40));
		lblInfo.setFont(Font.font("Times New Roman", 25));
		gPane.add(lblDate, 0, 0,4,1);
		gPane.add(lblInfo, 0, 1,4,5);
	}
	
	public ScrollPane getPage(Calendar day,String info) {
		//5 = day 4 = year
		StringBuilder date = new StringBuilder();
		
		
		date.append(Misc.dayOfWeek(day.getFirstDayOfWeek()));
		
		date.append(", "+ day.get(5) + ", " + day.get(1));
		
		lblDate.setText(date.toString());
		lblInfo.setText(info);
		
		return infoPane;
	} 
	
	public ScrollPane getList(Calendar day) {
		VBox vertical = new VBox();
		GridPane gp = new GridPane();
		ScrollPane sp = new ScrollPane(gp);
		
		for(int i = 0; i < events.size(); i++) {
			//
//			vertical.getChildren().add(e);
		}
		
	}
	
	private ArrayList<Event> getEvents(){
		ArrayList<Event> items = new ArrayList<Event>();
		
		//check here from csv
		//for now testing
		
		Calendar c = Calendar.getInstance();
		Event e1 = new Event("HW",c,"...");
		Event e2 = new Event("HW",c,"...");
		Event e3 = new Event("HW",c,"...");
		
		items.add(e1);
		items.add(e2);
		items.add(e3);
		
		return items;
		
	}

	
	
}
