package cz.zcu.viteja.uur.main;

import java.io.File;

import cz.zcu.viteja.uur.data.events.Database;
import cz.zcu.viteja.uur.views.MonthCalendarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	// Uložená data
	public Database data;

	// JavaFX
	private static Main instance;
	private Stage primaryStage;

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Init database
		instance = this;
		data = new Database();

		// JavaFX
		this.primaryStage = primaryStage;
		this.primaryStage.initStyle(StageStyle.UNIFIED);

		Scene scene = MonthCalendarView.getInstance().setup();

		File f = new File("resources/flat-theme.css");
		scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Plánovaè akcí");
		this.primaryStage.setResizable(false);
		this.primaryStage.show();

		// Icon
		File iconFile = new File("resources/icon.jpg");
		Image image = new Image("file:///" + iconFile.getAbsolutePath().replace("\\", "/"));
		this.primaryStage.getIcons().add(image);

		setUserAgentStylesheet(STYLESHEET_CASPIAN);

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
