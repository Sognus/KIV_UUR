package cz.zcu.viteja.uur.views;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.main.Main;
import cz.zcu.viteja.uur.views.calendar.YearCalendarGrid;
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

public class YearCalendarView extends View {

	private static YearCalendarView instance = null;

	private Scene scene;

	private BorderPane mainPane;
	private GridPane mainGrid;
	private VBox mainBox;

	// Ovládá rok a mìsíc, který se bude zobrazovat = aktuální rok + mìsíc
	private int workYear;
	private int workMonth;

	protected YearCalendarView() {
		this.workYear = DateUtils.getCurrentYear();
		this.workMonth = DateUtils.getCurrentMonth();

	}

	public void setWorkYear(int workYear) {
		this.workYear = workYear;
	}

	@Override
	public Scene setup() {
		this.mainPane = new BorderPane();

		this.mainPane.setTop(this.setupTop());
		this.mainPane.setLeft(this.setupLeft());
		this.mainPane.setCenter(this.setupCenter());
		this.mainPane.setRight(this.setupRight());
		this.mainPane.setBottom(this.setupBottom());

		this.scene = new Scene(mainPane, 800, 425);

		return this.scene;
	}

	@Override
	protected Node setupTop() {
		// TODO Auto-generated method stub
		return null;
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
		YearCalendarGrid cg = new YearCalendarGrid(this.workYear);
		GridPane gp = cg.setup();

		this.mainGrid = gp;

		// Label
		Button labelButton = new Button();

		labelButton.setText(String.format("%d", this.workYear));

		// Tlaèítko - pøedchozí rok
		Button previous = new Button();
		previous.setText(String.format("%d", this.workYear - 1));

		// Tlaèítko - následující rok
		Button next = new Button();
		next.setText(String.format("%d", this.workYear + 1));

		previous.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				workYear--;

				System.out.println(String.format("%d", workYear));

				GridPane newPane = new YearCalendarGrid(workYear).setup();
				YearCalendarView currentInstance = YearCalendarView.getInstance();
				currentInstance.mainBox.getChildren().remove(currentInstance.mainGrid);
				currentInstance.mainGrid = newPane;
				currentInstance.mainBox.getChildren().add(newPane);

				next.setText(String.format("%d", currentInstance.workYear + 1));
				previous.setText(String.format("%d", workYear - 1));

				labelButton.setText(String.format("%d", currentInstance.workYear));

			}
		});

		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				workYear++;

				System.out.println(String.format("%d", workYear));

				GridPane newPane = new YearCalendarGrid(workYear).setup();
				YearCalendarView currentInstance = YearCalendarView.getInstance();
				currentInstance.mainBox.getChildren().remove(currentInstance.mainGrid);
				currentInstance.mainGrid = newPane;
				currentInstance.mainBox.getChildren().add(newPane);

				next.setText(String.format("%d", currentInstance.workYear + 1));
				previous.setText(String.format("%d", workYear - 1));

				labelButton.setText(String.format("%d", currentInstance.workYear));
			}
		});

		labelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				YearCalendarView yearView = YearCalendarView.getInstance();
				yearView.workYear = DateUtils.getCurrentYear();
				Scene desiredScene = yearView.setup();

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

		// VBox
		VBox vBox = new VBox();
		vBox.getChildren().add(hBox);
		vBox.getChildren().add(gp);
		vBox.setSpacing(20);

		// Nastavení
		vBox.setAlignment(Pos.TOP_CENTER);
		gp.setMinWidth(gp.getPrefWidth());

		// Nastavit referenci
		this.mainBox = vBox;

		return vBox;
	}

	@Override
	protected Node setupBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	// STATIC
	public static YearCalendarView getInstance() {
		if (instance == null) {
			instance = new YearCalendarView();
		}
		return instance;
	}

}
