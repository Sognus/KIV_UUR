package cz.zcu.viteja.uur.data.events;

import java.util.HashMap;

public class MonthEvents {

	private int monthNumber;
	private int yearNumber;

	private HashMap<Integer, DayEvents> dayEvents;

	public MonthEvents(int year, int month) {
		this.yearNumber = year;
		this.monthNumber = month;

		this.dayEvents = new HashMap<Integer, DayEvents>();
	}

	public DayEvents getDayEvents(int day) {
		if (!dayEvents.containsKey(day)) {
			DayEvents newDayEvents = new DayEvents(this.yearNumber, this.monthNumber, day);
			this.dayEvents.put(day, newDayEvents);
		}
		return this.dayEvents.get(day);
	}

	public boolean isEmpty() {
		return dayEvents.isEmpty();
	}

	public void removeDayEvents(int day) {
		if (this.dayEvents.containsKey(day))
			this.dayEvents.remove(day);
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public int getYearNumber() {
		return yearNumber;
	}

	public HashMap<Integer, DayEvents> getDayEvents() {
		return dayEvents;
	}

}
