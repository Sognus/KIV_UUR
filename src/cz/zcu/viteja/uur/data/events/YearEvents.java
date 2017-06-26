package cz.zcu.viteja.uur.data.events;

import java.util.HashMap;

public class YearEvents {

	private int yearNumber;

	private HashMap<Integer, MonthEvents> monthEvents;

	public YearEvents(int year) {
		this.yearNumber = year;

		this.monthEvents = new HashMap<Integer, MonthEvents>();
	}

	public MonthEvents getMonthEvents(int month) {
		if (!this.monthEvents.containsKey(month)) {
			MonthEvents newMonthEvens = new MonthEvents(this.yearNumber, month);
			this.monthEvents.put(month, newMonthEvens);
		}
		return this.monthEvents.get(month);
	}

	public boolean isEmpty() {

		long l = this.monthEvents.entrySet().stream().filter(test -> !test.getValue().isEmpty()).count();

		return (l < 1);
	}

	public void removeMonthEvents(int month) {
		if (this.monthEvents.containsKey(month)) {
			this.monthEvents.remove(month);
		}
	}

	public int getYearNumber() {
		return yearNumber;
	}

	public HashMap<Integer, MonthEvents> getMonthEvents() {
		return monthEvents;
	}

}
