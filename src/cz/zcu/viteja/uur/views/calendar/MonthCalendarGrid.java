package cz.zcu.viteja.uur.views.calendar;

import cz.zcu.viteja.uur.data.DateUtils;
import cz.zcu.viteja.uur.data.MonthCalendarData;
import cz.zcu.viteja.uur.data.MonthInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
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
				Text text = new Text();
				text.setText(data[a][b]);

				if (text.getText().equals("1")) {
					seda = !seda;
				}

				if (seda == true) {
					text.setFill(Color.GREY);
				}

				// System.out.println(dataMessenger.getCurrentMonthDayCount());

				this.mainPane.add(text, b, a);

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
		this.mainPane.setAlignment(Pos.BASELINE_CENTER);

		return this.mainPane;

	}

}
