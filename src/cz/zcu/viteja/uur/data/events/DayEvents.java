package cz.zcu.viteja.uur.data.events;

import java.util.ArrayList;

public class DayEvents {

	private int monthNumber;
	private int dayNumber;
	private int yearNumber;

	private ArrayList<DayEvent> dayEvents;

	public DayEvents(int year, int month, int day) {
		this.yearNumber = year;
		this.dayNumber = day;
		this.monthNumber = month;

		this.dayEvents = new ArrayList<DayEvent>();
	}

	public void addDayEvent(DayEvent event) {

		// this.dayEvents.add(event);
		this.addUnusedName(event);
	}

	private boolean alreadyAdded(DayEvent event) {
		for (DayEvent e : dayEvents) {
			if (e.getDescription().equalsIgnoreCase(event.getDescription())) {
				return true;
			}
		}
		return false;
	}

	private void addUnusedName(DayEvent event) {
		if (this.alreadyAdded(event)) {
			boolean added = true;
			int additivum = 1;
			while (added) {
				String tryDesc = String.format("%s%d", event.getDescription(), additivum);
				DayEvent tryEvent = new DayEvent(event.getYear(), event.getMonth(), event.getDay(),
						event.getStartHour(), event.getEndHour(), tryDesc);

				boolean alreadyAdded = this.alreadyAdded(tryEvent);

				if (alreadyAdded) {
					this.dayEvents.add(tryEvent);

				}

				additivum++;
				added = alreadyAdded;
			}
		} else {
			this.dayEvents.add(event);
		}
	}

	public void removeDayEvent(DayEvent event) {
		ArrayList<DayEvent> filteredEvents = new ArrayList<DayEvent>();

		for (DayEvent e : dayEvents) {
			if (e.equals(event)) {
				continue;
			}
			filteredEvents.add(e);
		}

		dayEvents = filteredEvents;
	}

	public boolean isEmpty() {
		return dayEvents.isEmpty();
	}

	public int getMonthNumber() {
		return monthNumber;
	}

	public int getDayNumber() {
		return dayNumber;
	}

	public int getYearNumber() {
		return yearNumber;
	}

	public ArrayList<DayEvent> getDayEvents() {
		return dayEvents;
	}

}
