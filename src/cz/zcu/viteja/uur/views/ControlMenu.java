package cz.zcu.viteja.uur.views;

import cz.zcu.viteja.uur.main.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class ControlMenu {

	public static MenuBar getControlMenu() {
		MenuBar menu = new MenuBar();

		Menu aplikace = new Menu("Aplikace");

		MenuItem ukoncit = new MenuItem("Ukonèit");
		ukoncit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();

			}
		});

		MenuItem pridatAkci = new MenuItem("Pøidat akci");

		pridatAkci.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				CreateFormView cfw = CreateFormView.getInstance();
				Scene scene = cfw.setup();
				Main.getInstance().setScene(scene);
			}

		});

		aplikace.getItems().addAll(pridatAkci, new SeparatorMenuItem(), ukoncit);

		Menu zobrazit = new Menu("Zobrazit");

		MenuItem rok = new MenuItem("Rok");
		rok.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent t) {
				YearCalendarView ycw = YearCalendarView.getInstance();
				Scene scene = ycw.setup();
				Main.getInstance().setScene(scene);
			}
		});

		MenuItem mesic = new MenuItem("Mìsíc");
		mesic.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				MonthCalendarView mcw = MonthCalendarView.getInstance();
				Scene scene = mcw.setup();
				Main.getInstance().setScene(scene);
			}
		});

		MenuItem agenda = new MenuItem("Agenda");
		agenda.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				AgendaView aw = AgendaView.getInstance();
				Scene scene = aw.setup();
				Main.getInstance().setScene(scene);
			}
		});

		MenuItem zobrazitData = new MenuItem("Uložená data");
		zobrazitData.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StoredDataView sdw = StoredDataView.getInstance();
				Scene scene = sdw.setup();
				Main.getInstance().setScene(scene);

			}

		});

		zobrazit.getItems().addAll(rok, mesic, agenda, new SeparatorMenuItem(), zobrazitData);

		// Menus
		menu.getMenus().add(aplikace);
		menu.getMenus().add(zobrazit);

		return menu;
	}

}
