package cz.zcu.viteja.uur.data;

import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

	private static Locale loc = new Locale("cs", "CZ");
	private static Calendar cal = Calendar.getInstance(loc);

	public static String[] monthNames = { "", "Leden", "Únor", "Bøezen", "Duben", "Kvìten", "Èerven", "Èervenec",
			"Srpen", "Záøí", "Øíjen", "Listopad", "Prosinec" };

	public static String[] monthNamesZero = { "Leden", "Únor", "Bøezen", "Duben", "Kvìten", "Èerven", "Èervenec",
			"Srpen", "Záøí", "Øíjen", "Listopad", "Prosinec" };

	public static String[] dnyVTydnu = { "", "Pondìlí", "Úterý", "Støeda", "Ètvrtek", "Pátek", "Sobota", "Nedìle" };

	public static String[] dnyVTydnu2 = { "Pondìlí", "Úterý", "Støeda", "Ètvrtek", "Pátek", "Sobota", "Nedìle" };

	public static int getCurrentYear() {
		return cal.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return cal.get(Calendar.MONTH) + 1;

	}

	public static int getCurrentDay() {
		return cal.get(Calendar.DATE);
	}

	public static String getMonthName(int monthIndex) {
		if (monthIndex < 1) {
			monthIndex = 12;
		}

		if (monthIndex > 12) {
			monthIndex = 1;
		}

		// return monthNames[monthIndex];
		return adjustMonthNameToLongest(monthNames[monthIndex]);
	}

	private static String adjustMonthNameToLongest(String month) {
		int longest = -1;

		for (String s : monthNames) {
			if (s.length() > longest)
				longest = s.length();
		}

		StringBuilder sb = new StringBuilder();
		sb.append(month);

		boolean append = true;
		while (longest - sb.length() >= 0) {
			if (append) {
				sb.append(" ");
			} else {
				sb.insert(0, " ");
			}
			append = !append;
		}

		return sb.toString();
	}

	public static String getDayOfWeek2(int a) {
		if (a < 0 || a > 6) {
			return "<<bad>>";
		}
		return dnyVTydnu2[a];
	}

	public static String getDayOfWeek(int a) {
		if (a < 1 || a > 7) {
			return "<<bad>>";
		}
		return dnyVTydnu[a];
	}

	public static String translateDayOfWeek(String s) {

		String navrat = "<<bad>>";

		switch (s) {
		case "MONDAY":
			navrat = "Pondìlí";
			break;
		case "TUESDAY":
			navrat = "Úterý";
			break;
		case "WEDNESDAY":
			navrat = "Støeda";
			break;
		case "THURSDAY":
			navrat = "Ètvrtek";
			break;
		case "FRIDAY":
			navrat = "Pátek";
			break;
		case "SATURDAY":
			navrat = "Sobota";
			break;
		case "SUNDAY":
			navrat = "Nedìle";
			break;
		default:
			navrat = "<<bad>>";
			break;

		}

		return navrat;
	}

	public static String[] getMonthNames() {
		return monthNames;
	}

	public static String[] getMonthNamesFromZero() {
		return monthNamesZero;
	}

	public static int getMonthIndexByName(String name) {
		for (int a = 0; a < monthNames.length; a++) {
			if (name.toLowerCase().equals(monthNames[a].toLowerCase())) {
				return a;
			}
		}
		return 0;
	}

	public static int getMonthIndexFromZeroByName(String name) {
		for (int a = 1; a <= monthNamesZero.length; a++) {
			if (name.toLowerCase().equals(monthNames[a].toLowerCase())) {
				return a;
			}
		}
		return 0;
	}
}
