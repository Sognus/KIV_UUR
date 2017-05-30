package cz.zcu.viteja.uur.views;

import java.time.YearMonth;
import java.util.ArrayList;

import cz.zcu.viteja.uur.data.AgendaEvent;
import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.data.events.DayEvent;
import cz.zcu.viteja.uur.main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class AgendaView extends View {

	private static AgendaView instance = null;

	private Scene scene;

	private BorderPane mainPane;

	protected AgendaView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene setup() {
		this.mainPane = new BorderPane();

		this.mainPane.setCenter(this.setupCenter());
		this.mainPane.setTop(this.setupTop());

		this.scene = new Scene(mainPane, 1024, 768);

		return scene;
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

	@SuppressWarnings("unchecked")
	@Override
	protected Node setupCenter() {
		TableView<AgendaEvent> table = new TableView<AgendaEvent>();

		TableColumn<AgendaEvent, String> dateColumn = new TableColumn<AgendaEvent, String>("Datum");
		TableColumn<AgendaEvent, String> hourColumn = new TableColumn<AgendaEvent, String>("Èas");
		TableColumn<AgendaEvent, String> descColumn = new TableColumn<AgendaEvent, String>("Krátký popis");

		dateColumn.setEditable(false);
		dateColumn.setSortable(false);
		dateColumn.setMinWidth(60);
		dateColumn.setCellValueFactory(value -> value.getValue().getDateColumn());

		hourColumn.setEditable(false);
		hourColumn.setSortable(false);
		hourColumn.setMinWidth(40);
		hourColumn.setCellValueFactory(value -> value.getValue().getHourColumn());

		descColumn.setEditable(false);
		descColumn.setSortable(false);
		descColumn.setMinWidth(200);
		descColumn.setCellValueFactory(value -> value.getValue().getDescriptionColumn());

		table.getColumns().addAll(dateColumn, hourColumn, descColumn);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// TEST DELETE ASAP
		ObservableList<AgendaEvent> data = FXCollections.observableArrayList();

		AgendaEvent ae1 = new AgendaEvent("Pondìlí", "1", "bøezna", "1997", 17, 18, "Navštívit našeho prezidenta");
		// data.add(ae1);

		// Data render
		ArrayList<DayEvent> list = Main.getInstance().data.getDayEventList();
		for (DayEvent de : list) {
			YearMonth ym = YearMonth.of(de.getYear(), de.getMonth());
			String unlocalizedDayName = ym.atDay(de.getDay()).getDayOfWeek().name();
			String localizedDayName = DateUtils.translateDayOfWeek(unlocalizedDayName);

			String numericDayValue = String.valueOf(de.getDay());
			String localizedMonthName = DateUtils.getMonthName(de.getMonth());
			String year = String.valueOf(de.getYear());

			int startHour = de.getStartHour();
			int endHour = de.getEndHour();

			String shortDescription = de.getDescription();

			AgendaEvent ae = new AgendaEvent(localizedDayName, numericDayValue, localizedMonthName, year, startHour,
					endHour, shortDescription);
			data.add(ae);
		}

		// DATARENDER TEST

		table.setItems(data);

		return table;
	}

	@Override
	protected Node setupBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	// STATIC
	public static AgendaView getInstance() {
		if (instance == null) {
			instance = new AgendaView();
		}
		return instance;
	}

}
