package planner;

import java.sql.Savepoint;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	private Calendar day;
	
	int today[] = new int[3];

	public ScrollPane setUpCalander(Calendar day) {
		this.day = day;
		days = new GridPane();
		bp.setCenter(days);
		days.setVgap(25);
		days.setHgap(25);

		today[0] = day.get(1); 
		today[1] = day.get(2); 
		today[2] = day.get(5); 


		monthSetUp(day);



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
			
			Calendar test = (Calendar)newSet.clone();
			
			b.setOnAction(e -> {
				
				checkDay(test);
			});
			newSet.add(5, 1);

		}



	}

	public void checkDay(Calendar day) {
		
		InfoPane infoSection = new InfoPane();
		
		bp.setCenter(infoSection.getPage(day, ""));
		
		infoSection.tBoxInfo.setText(infoSection.notes.toString());
		ImageView cal = new ImageView(new Image("File:./src/planner/res/IconCAL.png"));
		cal.setFitHeight(40);
		cal.setFitWidth(40);
		Button out = new Button("", cal);
		
		infoSection.gPane.add(out, 5, 0);
		
		
		
		infoSection.btnAddEvent.setOnAction(e -> {
			
			addEvent(infoSection);
			
		});
		
		out.setOnAction(e ->{
			
			infoSection.save(day, infoSection.events, infoSection.tBoxInfo.getText());
			monthSetUp(day);
			
		});
		
		
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
		
		


		next.setOnAction(e -> {
			day.add(2, 1);
			monthSetUp(day);
		});

		last.setOnAction(e -> {
			day.add(2, -1);
			monthSetUp(day);
		});


	}
	
	private void addEvent(InfoPane info) {
		GridPane gp = new GridPane();
		gp.setVgap(50);
		gp.setHgap(25);
		bp.setCenter(gp);
		
		
		
		Button btnCancel = new Button("Cancel");
		btnCancel.setOnAction(x -> {
			bp.setCenter(info.gPane);
		});
		String options[] = {"Other","HomeWork","Quiz","Project","Test","Midterm","Final"};
		ComboBox<String> typeBox = new ComboBox<String>(FXCollections.observableArrayList(options));
		typeBox.setPromptText("Pick here");
		
		TextArea tb = new TextArea();
		tb.setPromptText("Add Details");
		
		Button submit = new Button("Submit");
		submit.setOnAction(x-> {
			Event e = new Event(typeBox.getValue(),this.day,tb.getText());
			info.addEvent(e);
			bp.setCenter(info.gPane);
		});
		submit.setMinSize(200, 50);;
		submit.setFont(Font.font(25));
		gp.add(submit, 5, 9,6,2);
		gp.add(tb, 3, 2,5,7);
		gp.add(typeBox, 5, 0,5,2);
		gp.add(new Label("Type: "), 1, 0,5,2);
		gp.add(btnCancel, 10, 0);
		
		
		
		
		
	}
	




}
