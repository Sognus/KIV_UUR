package cz.zcu.viteja.uur.views.calendar;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.data.YearCalendarData;
import cz.zcu.viteja.uur.main.Main;
import cz.zcu.viteja.uur.views.MonthCalendarView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class YearCalendarGrid {

	private GridPane mainPane;

	private int workYear;

	// Aktuální datum
	private int currentMonth;
	private int currentYear;

	public YearCalendarGrid(int year) {
		this.workYear = year;
		this.currentYear = DateUtils.getCurrentYear();
		this.currentMonth = DateUtils.getCurrentMonth() - 1;

	}

	public GridPane setup() {

		// Data pro zobrazení aktuálního mìsíce
		YearCalendarData dataProvider = new YearCalendarData(workYear);
		String[] data = dataProvider.getData();

		// Vytvoøení Grid layoutu
		this.mainPane = new GridPane();

		// Naplnìní gridlayoutu daty
		for (int a = 0; a < 16; a++) {
			int columnIndex = a % 4;
			int rowIndex = (int) Math.floor(a / 4);

			int dataIndex = a % 12;

			StackPane sp = new StackPane();
			Button text = new Button();
			text.setText(data[dataIndex]);

			sp.getChildren().add(text);
			sp.setPadding(new Insets(3));

			text.getStyleClass().add("month-grid-button");

			text.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					// System.out.println("You have clicked on " +
					// sp.getChildren().get(0).toString());

					MonthCalendarView monthView = MonthCalendarView.getInstance();
					monthView.setRenderDate(workYear, dataIndex + 1);

					Scene desiredScene = monthView.setup();

					Main.getInstance().setScene(desiredScene);

				}
			});

			if (currentYear == workYear && a < 12 && dataIndex == currentMonth) {
				sp.setStyle("-fx-background-color: derive(blue, 50%);");
			}

			this.mainPane.add(sp, columnIndex, rowIndex);
		}

		// Další nastavení
		this.mainPane.setPadding(new Insets(10, 30, 0, 10));
		this.mainPane.setVgap(40);
		this.mainPane.setHgap(40);
		this.mainPane.setAlignment(Pos.TOP_CENTER);

		return this.mainPane;

	}

}