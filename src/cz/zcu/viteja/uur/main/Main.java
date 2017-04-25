package cz.zcu.viteja.uur.main;

import cz.zcu.viteja.uur.views.MonthCalendarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Main instance;
	private Stage primaryStage;

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;

		this.primaryStage.setScene(MonthCalendarView.getInstance().setup());
		this.primaryStage.show();

		instance = this;
	}

	public void setScene(Scene newScene) {
		this.primaryStage.setScene(newScene);
		this.primaryStage.show();
	}

	public static Main getInstance() {
		return instance;

	}

}
