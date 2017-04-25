package cz.zcu.viteja.uur.data;

import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

	private static Locale loc = new Locale("cs", "CZ");
	private static Calendar cal = Calendar.getInstance(loc);

	public static String[] monthNames = { "", "Leden", "Únor", "Bøezen", "Duben", "Kvìten", "Èerven", "Èervenec",
			"Srpen", "Záøí", "Øíjen", "Listopad", "Prosinec" };

	public static int getCurrentYear() {
		return cal.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return cal.get(Calendar.MONTH) + 1;

	}

	public static String getMonthName(int monthIndex) {
		if (monthIndex < 1) {
			monthIndex = 12;
		}

		if (monthIndex > 12) {
			monthIndex = 1;
		}

		return monthNames[monthIndex];
	}
}
