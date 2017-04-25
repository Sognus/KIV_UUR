package cz.zcu.viteja.uur.views;

import cz.zcu.viteja.uur.views.calendar.MonthCalendarGrid;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MonthCalendarView extends View {

	private static MonthCalendarView instance = null;

	private Scene scene;

	private BorderPane mainPane;

	protected MonthCalendarView() {

	}

	@Override
	public Scene setup() {
		this.mainPane = new BorderPane();

		this.mainPane.setTop(this.setupTop());
		this.mainPane.setLeft(this.setupLeft());
		this.mainPane.setCenter(this.setupCenter());
		this.mainPane.setRight(this.setupRight());
		this.mainPane.setBottom(this.setupBottom());

		this.scene = new Scene(mainPane, 350, 300);

		return this.scene;
	}

	@Override
	protected Node setupTop() {
		return null;
	}

	@Override
	protected Node setupLeft() {
		return null;
	}

	@Override
	protected Node setupRight() {
		return null;
	}

	@Override
	protected Node setupCenter() {
		MonthCalendarGrid cg = new MonthCalendarGrid();

		return cg.setup();
	}

	@Override
	protected Node setupBottom() {
		return null;
	}

	// STATIC
	public static MonthCalendarView getInstance() {
		if (instance == null) {
			instance = new MonthCalendarView();
		}
		return instance;
	}

}
