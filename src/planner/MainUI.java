package planner;

import java.util.Calendar;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class MainUI {
	
	private GridPane days = new GridPane();
	private BorderPane bp = new BorderPane();
	private ScrollPane main = new ScrollPane(bp);
	private IOEvents io = new IOEvents();
	
	int today[] = new int[3];

	public ScrollPane setUpCalander(Calendar day) {
		days = new GridPane();
		bp.setCenter(days);
		days.setVgap(25);
		days.setHgap(25);

		today[0] = day.get(1); 
		today[1] = day.get(2); 
		today[2] = day.get(5); 


		monthSetUp(day);




		//7 day of week
		//4 = week of the month
		//2 = month starts at 0
		//1 = year



//		Calendar day2 =(Calendar) day.clone();
//		day2.add(5, 64);
//		int i = 0;
//		while(true) {
//			try {
//				System.out.println(i + " day 0 " + day.get(i));
//				System.out.println(i + " day 2 " + day2.get(i++));
//			}
//			catch(Exception ex) {
//				break;
//			}
//		}




		return main;
	}

	public void monthSetUp(Calendar start) {
		preset(start);
		days = new GridPane();
		bp.setCenter(days);
		days.setVgap(25);
		days.setHgap(25);


		for (int i = 1; i < 8; i++) {
			Label weekday = new Label(Misc.dayOfWeek(i));
			weekday.setFont(Font.font("Bell MT", FontPosture.ITALIC, 20));
			days.add(weekday, i , 0);
			
		}

		int max = Misc.daysInMonth(Misc.monthName(start.get(2)), Misc.isLeapYear(start.get(1)));
		Calendar newSet = (Calendar)start.clone();
		
		newSet.add(5,  (newSet.get(5) * -1) + 1);
		for (int i = 0; i < max; i++) {
			

			Button b = new Button(newSet.get(5)+"");
			b.setFont(Font.font("Areil", FontWeight.BLACK, 25));
			days.add(b, newSet.get(7), newSet.get(4)); 
			if (today[0] == newSet.get(1) & today[1] == newSet.get(2) & today[2] == newSet.get(5) ) {
				b.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN, CornerRadii.EMPTY, new Insets(1))));
			}
			newSet.add(5, 1);
			
			b.setOnAction(e -> {
				checkDay(newSet);
			});

		}



	}

	public void checkDay(Calendar day) {
		private InfoPane infoSection = new InfoPane();
		for (int i = 0; i < io.currentEvents.size(); i++) {
			if(io.currentEvents.get(i).day.equals(day)) {
				infoSection.events.add(io.currentEvents.get(i));
			}
		}
		
	}
	
	
	public void preset(Calendar day) {
//		GridPane gp = new GridPane();
//		gp.setVgap(30);
//		gp.setHgap(30);
		Pane p = new Pane();
		Label label = new Label();
		label.setText(String.format("%s    %d", Misc.monthName(day.get(2))+"," , day.get(1)));
		label.setFont(Font.font("Areial", FontWeight.BOLD, 50));
		p.getChildren().add(label);
		label.setLayoutX(0);
		label.setLayoutY(25);
		
		//gp.add(label, 0, 2,6,2);
		bp.setTop(p);


		Button next = new Button("Next Month");
		Button last = new Button("Last Month");

		p.getChildren().add(next);

		next.setLayoutX(700);
		next.setLayoutY(0);
		
		p.getChildren().add(last);

		last.setLayoutX(0);
		last.setLayoutY(0);
		
		
//		gp.add(last, 0, 0);
//		gp.add(next, 8, 0);

		next.setOnAction(e -> {
			day.add(2, 1);
			monthSetUp(day);
		});

		last.setOnAction(e -> {
			day.add(2, -1);
			monthSetUp(day);
		});


	}




}
