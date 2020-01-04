package planner;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sun.util.resources.CalendarData;

public class planner extends Application {
	
	Calendar day = Calendar.getInstance(TimeZone.getDefault(), Locale.US);
	InfoPane infoPane = new InfoPane();
	MainUI cal = new MainUI();
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//infoPane.getPage(day, "testing \n\n\n\n aaa"
		Scene scene = new Scene
				((cal.setUpCalander(day)),
						950,700);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Planner");
		
		primaryStage.show();
	}
	
	
	
	

	public static void main(String[] args) {
		Application.launch(args);
	}
}
