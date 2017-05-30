package cz.zcu.viteja.uur.views;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.main.Main;
import cz.zcu.viteja.uur.views.calendar.MonthCalendarGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MonthCalendarView extends View {

	private static MonthCalendarView instance = null;

	private Scene scene;

	private BorderPane mainPane;
	private GridPane mainGrid;
	private VBox mainBox;

	private Button next;
	private Button previous;
	private Button labelButton;

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

		this.scene = new Scene(mainPane, 600, 750);

		return this.scene;
	}

	@Override
	protected Node setupTop() {

		MenuBar menu = ControlMenu.getControlMenu();

		Menu akce = new Menu("Akce");

		MenuItem currentMonth = new MenuItem("Aktuální mìsíc");
		currentMonth.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				workYear = DateUtils.getCurrentYear();
				workMonth = DateUtils.getCurrentMonth();

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

		akce.getItems().addAll(currentMonth);
		menu.getMenus().add(akce);

		return menu;
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
		labelButton = new Button();

		labelButton.setText(String.format("%s %d", DateUtils.getMonthName(this.workMonth), this.workYear));

		// Tlaèítko - pøedchozí mìsíc
		previous = new Button();
		previous.setText(DateUtils.getMonthName(this.workMonth - 1));

		// Tlaèítko - následující mìsíc
		next = new Button();
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

		labelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int desiredYear = MonthCalendarView.getInstance().workYear;
				YearCalendarView yearView = YearCalendarView.getInstance();
				yearView.setWorkYear(desiredYear);
				Scene desiredScene = YearCalendarView.getInstance().setup();

				Main.getInstance().setScene(desiredScene);
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
		hBox.setAlignment(Pos.TOP_CENTER);
		hBox.setSpacing(25);

		HBox.setHgrow(labelButton, Priority.ALWAYS);
		HBox.setHgrow(previous, Priority.ALWAYS);
		HBox.setHgrow(next, Priority.ALWAYS);

		// VBox
		VBox vBox = new VBox();
		vBox.getChildren().add(hBox);
		vBox.getChildren().add(gp);
		vBox.setSpacing(25);

		// Nastavení
		vBox.setAlignment(Pos.TOP_CENTER);
		gp.setMinWidth(gp.getPrefWidth());

		// Nastavit referenci
		this.mainBox = vBox;

		return vBox;
	}

	@Override
	protected Node setupBottom() {
		return null;
	}

	public void setRenderDate(int year, int month) {
		this.workYear = year;
		this.workMonth = month;
	}

	// STATIC
	public static MonthCalendarView getInstance() {
		if (instance == null) {
			instance = new MonthCalendarView();
		}
		return instance;
	}

}
