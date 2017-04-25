package cz.zcu.viteja.uur.main;

import cz.zcu.viteja.uur.views.MonthCalendarView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(MonthCalendarView.getInstance().setup());
		primaryStage.show();

	}

}
