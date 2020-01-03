package planner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class InfoPane{
	Date date;
	String info;
	GridPane gPane = new GridPane();
	//ScrollPane infoPane = new ScrollPane(gPane);
	ArrayList<Event> events = new ArrayList<Event>();
	Label lblDate = new Label();
	TextArea tBoxInfo = new TextArea(); //This was a label but was later changed to a textArea to make it editable
	Calendar day;
	Button btnAddEvent;
	VBox vertical = new VBox();
	StringBuilder notes = new StringBuilder("Error ifPaneGet");
	IOEvents io = new IOEvents();
	
	 InfoPane(){
		super();
		tBoxInfo.setMaxSize(500, 350);
		gPane.setVgap(25);
		gPane.setHgap(50);
		lblDate.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD, FontPosture.ITALIC, 40));
		tBoxInfo.setFont(Font.font("Times New Roman", 25));
		gPane.add(lblDate, 0, 0,4,1);
		gPane.add(tBoxInfo, 0, 1,5,5);
	}
	
	public GridPane getPage(Calendar day,String info) {
		
		//5 = day 4 = year
		StringBuilder date = new StringBuilder();
		ImageView plus = new ImageView(new Image("File:./src/planner/res/plus.png"));
		plus.setFitHeight(40);
		plus.setFitWidth(40);
		
		btnAddEvent = new Button("", plus);
		
		
		
		
		date.append(Misc.dayOfWeek(day.getFirstDayOfWeek()));
		
		date.append(", "+ day.get(5) + ", " + day.get(1));
		
		
		lblDate.setText(date.toString());
		gPane.setAlignment(Pos.TOP_LEFT);
		gPane.add(getList(day), 5, 1,4,7);
		gPane.add(btnAddEvent, 6, 0);
		
		//this must be done in the main Ui
//		tBoxInfo.setText(info);
		
		
		
		return gPane;
	} 
	
	public ScrollPane getList(Calendar day) {
		
		//GridPane gp = new GridPane();
		ScrollPane sp = new ScrollPane(vertical);
		
		events = getEvents(day); //testing!!!
		
		for(int i = 0; i < events.size(); i++) {
			//remove later

			
			vertical.getChildren().add(events.get(i));
			Line l = new Line(0, 0, 300,0 );
			l.setStrokeWidth(5);
			vertical.getChildren().add(l);
			
//			vertical.getChildren().add(e);
		}
		sp.setMaxSize(550, 450);
		
		return sp;
		
	}
	
	private ArrayList<Event> getEvents(Calendar day){
		
		
		
		ArrayList<Event> items = new ArrayList<Event>();
		
		
		String[] all = io.read(day);
		
		
		//notes from saves;
		notes = new StringBuilder(all[0].replace("(NexT_Line_)","\n"));
		
		for (int i = 1; i < all.length; i++) {
			String[] sections = all[i].split("<_D_X>");
			items.add(new Event(sections[0], day, sections[1].replace("(NexT_Line_)","\n")));
		}
		
		
//		for (int i = 0; i < events.size(); i++) {
//		}
		
		//check here from csv
		//for now testing
		
		
//		Calendar c = Calendar.getInstance();
//		Event e1 = new Event("HW",c,"...");
//		Event e2 = new Event("HW",c,"...");
//		Event e3 = new Event("HW",c,"...");
//		
//		items.add(e1);
//		items.add(e2);
//		items.add(e3);
		
		return items;
		
	}

	public void addEvent(Event e) {
		events.add(e);
		vertical.getChildren().add(e);
	}
	
	public void save(Calendar day,ArrayList<Event> events,String info) {
		io.write(day, events, info);
	}
	
	
}
