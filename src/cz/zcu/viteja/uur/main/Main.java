package cz.zcu.viteja.uur.main;

import java.io.File;

import cz.zcu.viteja.uur.views.MonthCalendarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private static Main instance;
	private Stage primaryStage;

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.initStyle(StageStyle.UNIFIED);

		Scene scene = MonthCalendarView.getInstance().setup();
		File f = new File("resources/flat-theme.css");
		scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Plánovaè akcí");
		this.primaryStage.setResizable(false);
		this.primaryStage.show();

		setUserAgentStylesheet(STYLESHEET_CASPIAN);

		instance = this;
	}

	public void setScene(Scene newScene) {
		File f = new File("resources/flat-theme.css");
		newScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		this.primaryStage.setScene(newScene);

		this.primaryStage.show();
	}

	public static Main getInstance() {
		return instance;

	}

}
