package cz.zcu.viteja.uur.views.calendar;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.data.MonthCalendarData;
import cz.zcu.viteja.uur.data.MonthInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MonthCalendarGrid {

	private GridPane mainPane;

	private int workYear;
	private int workMonth;

	@Deprecated
	public MonthCalendarGrid() {
		this.workYear = DateUtils.getCurrentYear();
		this.workMonth = DateUtils.getCurrentMonth();
	}

	public MonthCalendarGrid(int year, int month) {
		this.workYear = year;
		this.workMonth = month;
	}

	public GridPane setup() {

		// Data pro zobrazení aktuálního mìsíce
		MonthCalendarData dataProvider = new MonthCalendarData(this.workYear, this.workMonth);
		MonthInfo dataMessenger = dataProvider.getMonthInfo();
		String[][] data = dataMessenger.getData();

		// Vytvoøení Grid layoutu
		this.mainPane = new GridPane();

		// Naplnìní gridlayoutu daty
		int total = 0;
		boolean seda = false;
		for (int a = 0; a < data.length; a++) {
			for (int b = 0; b < data[a].length; b++) {
				StackPane sp = new StackPane();
				Text text = new Text();
				text.setText(data[a][b]);

				if (text.getText().equalsIgnoreCase("1")) {
					seda = !seda;
				}

				if (seda == true) {
					text.setFill(Color.GREY);
				} else {
					text.setFill(Color.WHITE);
				}

				sp.getChildren().add(text);
				sp.setPadding(new Insets(3));

				if (DateUtils.getCurrentMonth() == this.workMonth && DateUtils.getCurrentYear() == this.workYear
						&& text.getText().equalsIgnoreCase(String.valueOf(DateUtils.getCurrentDay()))
						&& text.getFill() != Color.GREY) {
					sp.setStyle("-fx-background-color: derive(blue, 50%);");
					// sp.setStyle("-fx-background-fill: cyan;");
				}

				// System.out.println(dataMessenger.getCurrentMonthDayCount());

				this.mainPane.add(sp, b, a);
				// this.mainPane.setHgrow(sp, Priority.ALWAYS);
				// this.mainPane.setVgrow(sp, Priority.ALWAYS);

				if (text.getText().equalsIgnoreCase("Ne")) {
					seda = !seda;
				}

				total++;
			}
		}

		// Další nastavení
		this.mainPane.setPadding(new Insets(10, 30, 0, 30));
		this.mainPane.setVgap(20);
		this.mainPane.setHgap(20);
		this.mainPane.setAlignment(Pos.CENTER);

		return this.mainPane;

	}

}
