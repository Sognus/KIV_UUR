package cz.zcu.viteja.uur.views.calendar;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.data.MonthCalendarData;
import cz.zcu.viteja.uur.data.MonthInfo;
import cz.zcu.viteja.uur.main.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
		String prevData = "";
		boolean seda = false;
		for (int a = 0; a < data.length; a++) {
			for (int b = 0; b < data[a].length; b++) {
				String currentData = data[a][b] == null ? "null" : data[a][b].trim();

				if (currentData == "null")
					continue;

				StackPane sp = new StackPane();
				Button text = new Button();

				String textForSet = currentData.length() < 2 ? " " + currentData : currentData;

				text.setText(textForSet);

				if (currentData.equalsIgnoreCase("1")) {
					seda = !seda;
				}
				if (seda == true) {
					// text.setFill(Color.GREY);
					text.getStyleClass().add("month-grid-button-not-this-month");
				} else {
					text.getStyleClass().add("month-grid-button-this-month");

				}

				sp.getChildren().add(text);
				sp.setPadding(new Insets(3));

				if (DateUtils.getCurrentMonth() == this.workMonth && DateUtils.getCurrentYear() == this.workYear
						&& currentData.equalsIgnoreCase(String.valueOf(DateUtils.getCurrentDay()))
						&& seda == false /* text.getFill() != Color.GREY */) {
					text.getStyleClass().add("month-grid-button-current");
				}

				// Existuje event
				if (this.isNumeric(currentData) && seda == false) {
					int yyy = this.workYear;
					int mmm = this.workMonth;
					int ddd = Integer.valueOf(currentData);

					boolean isGood = Main.getInstance().data.hasEvent(yyy, mmm, ddd);

					if (isGood) {
						text.getStyleClass().add("month-grid-button-has-event");
					}

				}

				this.mainPane.add(sp, b, a);

				if (currentData.equalsIgnoreCase("Ne")) {
					seda = !seda;
				}

				text.getStyleClass().add("month-grid-button");

				prevData = currentData;
				total++;
			}
		}

		// Další nastavení
		this.mainPane.setPadding(new Insets(10, 30, 0, 20));
		this.mainPane.setVgap(5);
		this.mainPane.setHgap(5);
		this.mainPane.setAlignment(Pos.CENTER);

		return this.mainPane;

	}

	public boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
	}

}
