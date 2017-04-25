package cz.zcu.viteja.uur.data;

import java.text.DateFormatSymbols;
import java.time.YearMonth;
import java.util.Locale;

public class MonthCalendarData {

	private int currentMonth;
	private int currentYear;

	private MonthInfo monthInfo;

	public MonthCalendarData(int year, int month) {
		this.currentMonth = month;
		this.currentYear = year;

		this.createData();

	}

	private void createData() {
		// Data pro export
		int previousMonthDays = 1;
		int monthDays = 1;
		int nextMonthDays = 1;

		String[][] data = new String[7][8];

		// Získání pole s krátkými názvy dnù v týdnu
		String[] dny = this.getCorrectShortDays();

		// Jméno prvního dne v mìsíci
		YearMonth ym = YearMonth.of(this.currentYear, this.currentMonth);
		String firstDay = ym.atDay(1).getDayOfWeek().name();

		int daysTotal = 0;

		// Vytvoøí data pro hlavièku kalendáøe
		for (int i = 0; i < dny.length; i++) {
			data[0][i] = dny[i];
			// daysTotal++;
		}

		// Kalendáø souèasného mìsíce
		YearMonth yearMonthObject = YearMonth.of(this.currentYear, this.currentMonth);
		int daysInMonth = yearMonthObject.lengthOfMonth();

		int workYear = this.currentYear;
		int workMonth = this.currentMonth;

		// Jestliže pøedchozí mìsíc je prosinec minulého roku
		if (workMonth - 1 == 0) {
			workMonth = 12;
			workYear--;
		} else {
			workMonth--;
		}

		// Kalendáø pøechozího mìsíce
		YearMonth lastMonth = YearMonth.of(workYear, workMonth);
		int lastMonthLastDay = lastMonth.lengthOfMonth();

		int column = this.getDayIndex(firstDay) - 1;

		// Nastaví dny pøedchozího mìsíce
		for (int i = column; i > 0; i--) {
			data[1][i] = String.valueOf(lastMonthLastDay);

			lastMonthLastDay--;
			previousMonthDays++;
			daysTotal++;
		}

		// Nastaví dny mìsíce
		for (int i = 1; i <= daysInMonth; i++) {
			data[(int) (1 + Math.floor(column / 7))][(column % 7) + 1] = String.valueOf(i);

			column++;
			monthDays++;
			daysTotal++;
		}

		// Nastaví dny dalšího mìsíce
		for (int nextMonth = 1; 42 - daysTotal > 0; nextMonth++) {
			data[(int) (1 + Math.floor(column / 7))][(column % 7) + 1] = String.valueOf(nextMonth);

			daysTotal++;
			nextMonthDays++;
			column++;
		}

		// Vytvoøí Pøepravku
		this.monthInfo = new MonthInfo(data, previousMonthDays, monthDays, nextMonthDays);
	}

	public MonthInfo getMonthInfo() {
		if (this.monthInfo == null) {
			this.createData();
		}
		return this.monthInfo;

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

}
