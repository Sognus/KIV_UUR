package cz.zcu.viteja.uur.views;

import org.controlsfx.control.RangeSlider;

import cz.zcu.viteja.uur.data.events.DayEvent;
import cz.zcu.viteja.uur.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CreateFormView extends View {

	private static CreateFormView instance;
	private BorderPane mainPane;
	private Scene scene;

	protected CreateFormView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Scene setup() {
		this.mainPane = new BorderPane();

		this.mainPane.setTop(this.setupTop());
		this.mainPane.setLeft(this.setupLeft());
		this.mainPane.setCenter(this.setupCenter());
		this.mainPane.setRight(this.setupRight());
		this.mainPane.setBottom(this.setupBottom());

		this.mainPane.setPadding(new Insets(0, 0, 10, 0));

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
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20, 0, 0, 0));

		Label labelName = new Label("Název");
		TextField textFieldName = new TextField();

		Label labelDate = new Label("Datum: ");
		DatePicker datePicker = new DatePicker();
		datePicker.getStyleClass().add("date-picker-popup");
		datePicker.getStyleClass().add("date-picker");
		datePicker.getEditor().setDisable(true);

		Label labelRange = new Label("Èas akce");
		RangeSlider slider = new RangeSlider(0, 24, 0, 24);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setBlockIncrement(1);
		slider.setMinorTickCount(2);
		slider.setMajorTickUnit(3);

		Button buttonCreate = new Button("Vytvoøit");
		buttonCreate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (textFieldName.getText().length() < 1 || datePicker.getValue() == null) {
					Alert a = new Alert(AlertType.WARNING);
					a.setHeaderText("Formuláø nebyl øádnì vyplnìn!");
					a.setContentText(
							"Jedno nebo více políèek nebylo vyplnìno. Prosím zkontrolujte své hodnoty ve formuláøi a zkuste to znovu.");
					a.showAndWait();
					return;
				}

				int year = datePicker.getValue().getYear();
				int month = datePicker.getValue().getMonth().getValue();
				int day = datePicker.getValue().getDayOfMonth();

				int startHour = (int) slider.getLowValue();
				int endHour = (int) slider.getHighValue();

				String description = textFieldName.getText();

				DayEvent dayEvent = new DayEvent(year, month, day, startHour, endHour, description);
				Main.getInstance().data.addEvent(dayEvent);

				Alert b = new Alert(AlertType.INFORMATION);
				b.setHeaderText("Akce byla úspìšnì naplánována");
				b.setContentText(
						"Akce byla úspìšnì naplánována, nyní je možné zobrazit ji v kalendáøi a v seznamu akcí.");
				b.show();

			}

		});

		// Eventy

		// Layout
		grid.add(labelName, 0, 0);
		grid.add(textFieldName, 1, 0);

		grid.add(labelDate, 0, 1);
		grid.add(datePicker, 1, 1);

		grid.add(labelRange, 0, 2);
		grid.add(slider, 1, 2);

		grid.add(buttonCreate, 1, 3);

		// Odsazení
		grid.setVgap(20);
		grid.setHgap(20);
		grid.setAlignment(Pos.TOP_CENTER);

		return grid;
	}

	@Override
	protected Node setupBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	// STATIC
	public static CreateFormView getInstance() {
		if (instance == null) {
			instance = new CreateFormView();
		}
		return instance;
	}
}
