package cz.zcu.viteja.uur.views;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.main.Main;
import cz.zcu.viteja.uur.views.calendar.MonthCalendarGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonthCalendarView extends View {

	private static MonthCalendarView instance = null;

	private Scene scene;

	private BorderPane mainPane;

	// Ovládá který mìsíc se bude zobrazovat
	private int workYear;
	private int workMonth;

	protected MonthCalendarView() {
		this.workYear = DateUtils.getCurrentYear();
		this.workMonth = DateUtils.getCurrentMonth();

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
		MonthCalendarGrid cg = new MonthCalendarGrid(this.workYear, this.workMonth);
		GridPane gp = cg.setup();

		Button previous = new Button();
		previous.setText(DateUtils.getMonthName(this.workMonth - 1));

		previous.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				workMonth--;

				if (workMonth == 0) {
					workYear--;
					workMonth = 12;
				}

				previous.setText(DateUtils.getMonthName(workMonth));

				System.out.println(String.format("%d-%d", workMonth, workYear));

				Scene newScene = setup();
				Main.getInstance().setScene(newScene);
				;

			}
		});

		Button next = new Button();
		next.setText(DateUtils.getMonthName(this.workMonth + 1));

		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				workMonth++;

				if (workMonth > 12) {
					workYear++;
					workMonth = 1;
				}

				next.setText(DateUtils.getMonthName(workMonth));

				System.out.println(String.format("%d-%d", workMonth, workYear));

				Scene newScene = setup();
				Main.getInstance().setScene(newScene);
				;

			}
		});

		// Label
		Label label = new Label();

		label.setText(String.format("%s %d", DateUtils.getMonthName(this.workMonth), this.workYear));

		// Stack panes

		// hBox
		HBox hBox = new HBox();
		hBox.getChildren().add(previous);
		hBox.getChildren().add(label);
		hBox.getChildren().add(next);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(25);

		hBox.setPadding(new Insets(0, 30, 0, 30));

		// VBox
		VBox vBox = new VBox();
		vBox.getChildren().add(hBox);
		vBox.getChildren().add(gp);

		// Nastavení vBox
		vBox.setAlignment(Pos.CENTER);

		return vBox;
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
