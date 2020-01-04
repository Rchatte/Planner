package planner;


import java.util.Calendar;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class Event extends GridPane {
	String type;
	Calendar day;
	String Details;
	Button del;
	

	public Event(String type, Calendar day, String Details) {
		super();
		super.setVgap(25);
		super.setHgap(40);
		this.type = type;
		this.day = day;
		this.Details = Details;
		Label t = new Label(type);
		Label d = new Label(String.format("%s - %s - %s", Misc.monthName(day.get(2)),Misc.dayOfWeek(7),day.get(5)));
		Label det = new Label(Details);
		
		ImageView trash = new ImageView(new Image("trashIcon.png"));
		trash.setFitHeight(25);
		trash.setFitWidth(25);
		del = new Button("",trash);
		
		
		d.setFont(Font.font("Times New Roman",FontWeight.BOLD, 15));
		t.setFont(Font.font(15));
		super.add(del, 4, 0);
		super.add(t, 0, 0 , 3,2);
		super.add(d, 0, 1, 3,1);
		super.add(det, 0, 2, 4, 3);
				
		
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Event) {
			Event e = (Event)obj;
			return (e.day.equals(this.day) & e.type.equalsIgnoreCase(this.type) & e.Details.equals(this.Details));
		}
		else
			return false;
	}

	
}