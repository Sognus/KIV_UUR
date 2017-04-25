package cz.zcu.viteja.uur.views.calendar;

import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MonthCalendarGrid {

	private GridPane mainPane;

	public MonthCalendarGrid() {

	}

	public GridPane setup() {

		Locale loc = new Locale("cs", "CZ");
		String[] dny = this.getCorrectShortDays();
		System.out.println(Arrays.toString(DateFormatSymbols.getInstance(loc).getShortWeekdays()));

		Calendar cal = Calendar.getInstance(loc);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = (cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek() + 7) % 7 + 1;
		int wk = cal.get(Calendar.WEEK_OF_MONTH);

		YearMonth ym = YearMonth.of(2017, 4);

		String firstDay = ym.atDay(1).getDayOfWeek().name();
		String lastDay = ym.atEndOfMonth().getDayOfWeek().name();

		System.out.println(firstDay);

		// Vytvoøí GridPane
		this.mainPane = new GridPane();

		// Pøidá hlavièku GridPane
		for (int i = 0; i < dny.length; i++) {
			Label l = new Label();
			l.setText(dny[i]);

			this.mainPane.add(l, i, 0);
		}

		YearMonth yearMonthObject = YearMonth.of(2017, 4);
		int daysInMonth = yearMonthObject.lengthOfMonth();

		YearMonth lastMonth = YearMonth.of(2017, 3);
		int lastMonthLastDay = lastMonth.lengthOfMonth();

		// Pøidá dny
		// Label l2 = new Label();
		// l2.setText("" + day + "/" + wk);
		// this.mainPane.add(l2, day, wk);

		int daysTotal = 0;

		int row = 1;
		int column = this.getDayIndex(firstDay) - 1;

		// Nastaví dny pøedchozího mìsíce
		for (int i = column; i > 0; i--) {
			Text text = new Text();
			text.setText(String.valueOf(lastMonthLastDay));

			text.setFill(Color.GREY);

			this.mainPane.add(text, i, 1);

			lastMonthLastDay--;
			daysTotal++;
		}

		// Nastaví dny mìsíce
		for (int i = 1; i <= daysInMonth; i++) {
			Text text = new Text();
			text.setText(String.valueOf(i));

			this.mainPane.add(text, (column % 7) + 1, (int) (1 + Math.floor(column / 7)));

			column++;
			daysTotal++;
		}

		for (int nextMonth = 1; 42 - daysTotal > 0; nextMonth++) {
			Text text = new Text();
			text.setText(String.valueOf(nextMonth));

			text.setFill(Color.GREY);

			this.mainPane.add(text, (column % 7) + 1, (int) (1 + Math.floor(column / 7)));

			daysTotal++;
			column++;
		}

		// Label label = new Label();
		// label.setText("1");
		// this.mainPane.add(label, this.getDayIndex(firstDay), 1);

		// Další nastavení
		this.mainPane.setPadding(new Insets(30));
		this.mainPane.setVgap(20);
		this.mainPane.setHgap(20);
		this.mainPane.setAlignment(Pos.BASELINE_CENTER);

		return this.mainPane;

	}

	private String[] getCorrectShortDays() {
		Locale loc = new Locale("cs", "CZ");
		DateFormatSymbols symbols = DateFormatSymbols.getInstance(loc);
		String[] start = symbols.getShortWeekdays();

		String[] fixed = new String[start.length];
		for (int i = 2; i < start.length; i++) {
			fixed[i - 1] = start[i];
		}
		fixed[7] = start[1];
		return fixed;

	}

	private int getDayIndex(String name) {
		switch (name) {
		case "MONDAY":
			return 1;
		case "TUESDAY":
			return 2;
		case "WEDNESDAY":
			return 3;
		case "THURSDAY":
			return 4;
		case "FRIDAY":
			return 5;
		case "SATURDAY":
			return 6;
		case "SUNDAY":
			return 7;
		default:
			return 0;
		}
	}

	private int getDayRow(int day) {
		return day - 1;
	}

}
