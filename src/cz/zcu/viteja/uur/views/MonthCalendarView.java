package cz.zcu.viteja.uur.views;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.views.calendar.MonthCalendarGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonthCalendarView extends View {

	private static MonthCalendarView instance = null;

	private Scene scene;

	private BorderPane mainPane;
	private GridPane mainGrid;
	private VBox mainBox;

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

		this.scene = new Scene(mainPane, 600, 600);

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

		this.mainGrid = gp;

		// Label
		Button labelButton = new Button();

		labelButton.setText(String.format("%s %d", DateUtils.getMonthName(this.workMonth), this.workYear));

		// Tlaèítko - pøedchozí mìsíc
		Button previous = new Button();
		previous.setText(DateUtils.getMonthName(this.workMonth - 1));

		// Tlaèítko - následující mìsíc
		Button next = new Button();
		next.setText(DateUtils.getMonthName(this.workMonth + 1));

		previous.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				workMonth--;

				if (workMonth == 0) {
					workYear--;
					workMonth = 12;
				}

				System.out.println(String.format("%d-%d", workMonth, workYear));

				GridPane newPane = new MonthCalendarGrid(workYear, workMonth).setup();
				MonthCalendarView currentInstance = MonthCalendarView.getInstance();
				currentInstance.mainBox.getChildren().remove(currentInstance.mainGrid);
				currentInstance.mainGrid = newPane;
				currentInstance.mainBox.getChildren().add(newPane);

				next.setText(DateUtils.getMonthName(workMonth + 1));
				previous.setText(DateUtils.getMonthName(workMonth - 1));

				labelButton.setText(String.format("%s %d", DateUtils.getMonthName(currentInstance.workMonth),
						currentInstance.workYear));

			}
		});

		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				workMonth++;

				if (workMonth > 12) {
					workYear++;
					workMonth = 1;
				}

				System.out.println(String.format("%d-%d", workMonth, workYear));

				GridPane newPane = new MonthCalendarGrid(workYear, workMonth).setup();
				MonthCalendarView currentInstance = MonthCalendarView.getInstance();
				currentInstance.mainBox.getChildren().remove(currentInstance.mainGrid);
				currentInstance.mainGrid = newPane;
				currentInstance.mainBox.getChildren().add(newPane);

				next.setText(DateUtils.getMonthName(workMonth + 1));
				previous.setText(DateUtils.getMonthName(workMonth - 1));

				labelButton.setText(String.format("%s %d", DateUtils.getMonthName(currentInstance.workMonth),
						currentInstance.workYear));

			}
		});

		previous.getStyleClass().add("month-button-control");
		next.getStyleClass().add("month-button-control");
		labelButton.getStyleClass().add("month-label-control");

		// hBox
		HBox hBox = new HBox();
		hBox.getChildren().add(previous);
		hBox.getChildren().add(labelButton);
		hBox.getChildren().add(next);
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(25);

		// VBox
		VBox vBox = new VBox();
		vBox.getChildren().add(hBox);
		vBox.getChildren().add(gp);

		// Nastavení
		vBox.setAlignment(Pos.CENTER);
		gp.setMinWidth(gp.getPrefWidth());

		// Nastavit referenci
		this.mainBox = vBox;

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
