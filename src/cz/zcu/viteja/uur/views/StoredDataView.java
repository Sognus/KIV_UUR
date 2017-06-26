package cz.zcu.viteja.uur.views;

import java.util.HashMap;
import java.util.Map.Entry;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.data.events.DayEvent;
import cz.zcu.viteja.uur.data.events.DayEvents;
import cz.zcu.viteja.uur.data.events.MonthEvents;
import cz.zcu.viteja.uur.data.events.YearEvents;
import cz.zcu.viteja.uur.main.Main;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class StoredDataView extends View {

	private static StoredDataView instance;

	private TreeView<String> strom;
	private BorderPane mainPane;
	private Scene scene;

	@Override
	public Scene setup() {
		this.mainPane = new BorderPane();

		this.mainPane.setTop(this.setupTop());
		this.mainPane.setLeft(this.setupLeft());
		this.mainPane.setCenter(this.setupCenter());
		this.mainPane.setRight(this.setupRight());
		this.mainPane.setBottom(this.setupBottom());

		this.mainPane.setPadding(new Insets(0, 0, 0, 0));

		this.scene = new Scene(mainPane, 600, 400);

		return this.scene;
	}

	@Override
	protected Node setupTop() {
		return ControlMenu.getControlMenu();
	}

	@Override
	protected Node setupLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node setupRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Node setupCenter() {

		TreeItem<String> rootItem = new TreeItem<String>("Data");
		rootItem.setExpanded(true);

		for (Entry<Integer, YearEvents> yearEntry : Main.getInstance().data.getAllData().entrySet()) {
			String yearItemName = String.format("%s %d: ", "Rok", yearEntry.getKey());

			if (yearEntry.getValue().isEmpty())
				continue;

			TreeItem<String> yearItem = new TreeItem<String>(yearItemName);
			yearItem.setExpanded(false);

			// Mìsíce
			for (Entry<Integer, MonthEvents> monthEntry : yearEntry.getValue().getMonthEvents().entrySet()) {
				String monthItemName = (DateUtils.getMonthName(monthEntry.getKey()) + ":").replace(" ", "");

				if (monthEntry.getValue().isEmpty())
					continue;

				TreeItem<String> monthItem = new TreeItem<String>(monthItemName);
				monthItem.setExpanded(false);

				// Seskupení dnù
				for (Entry<Integer, DayEvents> daysEntry : monthEntry.getValue().getDayEvents().entrySet()) {
					String dayItemName = String.format("%d:", daysEntry.getKey());

					if (daysEntry.getValue().isEmpty())
						continue;

					TreeItem<String> daysItem = new TreeItem<String>(dayItemName);
					daysItem.setExpanded(false);

					// Samotné akce ve dnech
					for (DayEvent eventEntry : daysEntry.getValue().getDayEvents()) {
						String eventItemName = eventEntry.getDescription();
						TreeItem<String> eventItem = new TreeItem<String>(eventItemName);

						// Pøidat eventy do dnù
						daysItem.getChildren().add(eventItem);

					}

					// Pøidat dny do mìsícù
					monthItem.getChildren().add(daysItem);

				}

				// Pøidat mìsíce do rokù
				yearItem.getChildren().add(monthItem);
			}
			// Pøidat roky do koøene
			rootItem.getChildren().add(yearItem);

		}

		strom = new TreeView<String>(rootItem);

		return strom;
	}

	public static boolean stringContainsItemFromList(String inputStr, String[] items) {

		for (String s : items) {

			if (s.toLowerCase().contains(inputStr.toLowerCase())) {

				return true;
			}
		}

		return false;
	}

	@Override
	protected Node setupBottom() {

		FlowPane box = new FlowPane();
		Button deleteItem = new Button("Smazat událost");
		deleteItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setHeaderText("Tuto akci je tøeba potrvrdit");
				a.setContentText("Chystáte se nenávratnì smazat naplánovanou akci. Pøejete si pokraèovat?");
				a.showAndWait();

				if (a.getResult() == ButtonType.OK) {
					ObservableList<TreeItem<String>> selected = strom.getSelectionModel().getSelectedItems();

					for (TreeItem<String> stromoItem : selected) {
						if (stromoItem.getChildren().size() > 0) {
							// Nemùžeme smazat prvky které mají v sobe jiné
							// prvky
							return;
						}

						if (stromoItem.getValue() == "Data" || stromoItem.getValue().toLowerCase().contains("rok")
								|| stringContainsItemFromList(stromoItem.getValue(), DateUtils.monthNamesZero)) {
							return;

						}

						TreeItem<String> denMesice = stromoItem.getParent();
						TreeItem<String> mesicVRoce = denMesice.getParent();
						TreeItem<String> rok = mesicVRoce.getParent();
						TreeItem<String> root = rok.getParent();

						String[] aaaa = rok.getValue().split(" ");
						Integer aaab = Integer.valueOf(aaaa[1].replace(":", ""));
						Integer mesic = DateUtils.getMonthIndexByName(mesicVRoce.getValue().replace(":", ""));

						HashMap<Integer, YearEvents> data = Main.getInstance().data.getAllData();
						for (Entry<Integer, YearEvents> roky : data.entrySet()) {
							YearEvents ye = roky.getValue();
							for (Entry<Integer, MonthEvents> mesicee : ye.getMonthEvents().entrySet()) {
								MonthEvents me = mesicee.getValue();
								for (Entry<Integer, DayEvents> dnyy : me.getDayEvents().entrySet()) {
									DayEvents de = dnyy.getValue();

									DayEvent deleteThis = null;

									for (DayEvent devents : de.getDayEvents()) {
										if (devents.getYear() == aaab && devents.getMonth() == mesic
												&& devents.getDay() == Integer
														.valueOf(denMesice.getValue().replace(":", ""))
												&& stromoItem.getValue() == devents.getDescription()) {
											// FOUND IT
											deleteThis = devents;
										}

									}

									de.getDayEvents().remove(deleteThis);
								}
							}

						}

					}

					Main.getInstance().data.updateList();

					StoredDataView sdw = StoredDataView.getInstance();
					Scene s = sdw.setup();
					Main.getInstance().setScene(s);

				}

			}

		});

		box.getChildren().add(deleteItem);
		;

		return box;
	}

	// STATIC
	public static StoredDataView getInstance() {
		if (instance == null) {
			instance = new StoredDataView();
		}
		return instance;
	}

}
