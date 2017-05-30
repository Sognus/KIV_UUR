package cz.zcu.viteja.uur.data.events;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

	private HashMap<Integer, YearEvents> data;

	private ArrayList<DayEvent> list;

	public Database() {
		this.data = new HashMap<Integer, YearEvents>();
		this.list = new ArrayList<DayEvent>();
	}

	public ArrayList<DayEvent> getDayEventList() {
		return this.list;
	}

	public void addEvent(DayEvent event) {
		int year = event.getYear();
		int month = event.getMonth();
		int day = event.getDay();

		YearEvents yE = this.getYearEvents(year);
		MonthEvents mE = yE.getMonthEvents(month);
		DayEvents dE = mE.getDayEvents(day);

		this.list.add(event);
		dE.addDayEvent(event);

	}

	public void removeEvent(DayEvent event) {
		int year = event.getYear();
		int month = event.getMonth();
		int day = event.getDay();

		YearEvents yE = this.getYearEvents(year);
		MonthEvents mE = yE.getMonthEvents(month);
		DayEvents dE = mE.getDayEvents(day);

		dE.removeDayEvent(event);

		if (dE.isEmpty()) {
			mE.removeDayEvents(day);
		}

		if (mE.isEmpty()) {
			yE.removeMonthEvents(month);
		}

		if (yE.isEmpty()) {
			if (this.data.containsKey(year)) {
				this.data.remove(year);
			}
		}

		this.list.remove(event);

	}

	private YearEvents getYearEvents(int year) {
		if (!data.containsKey(year)) {
			YearEvents yearEvents = new YearEvents(year);
			data.put(year, yearEvents);
		}
		return data.get(year);
	}

	public boolean hasEvent(int year, int month, int day) {

		// System.out.println(String.format("AAA: %d %d %d", year, month, day));

		/*
		 * for (Entry<Integer, YearEvents> entryA : this.data.entrySet()) {
		 * 
		 * System.out.println("" + entryA.getKey());
		 * 
		 * if (entryA.getKey() != Integer.valueOf(year)) { continue; }
		 * 
		 * YearEvents ye = entryA.getValue();
		 * System.out.println("Finding in YEAR=" + ye.getYearNumber());
		 * 
		 * for (Entry<Integer, MonthEvents> entryB :
		 * ye.getMonthEvents().entrySet()) { if (entryB.getKey() != month) {
		 * continue; }
		 * 
		 * MonthEvents me = entryB.getValue();
		 * System.out.println("Finding in MONTH=" + me.getMonthNumber());
		 * 
		 * for (Entry<Integer, DayEvents> entryC : me.getDayEvents().entrySet())
		 * { if (entryC.getKey() != day) { continue; }
		 * 
		 * DayEvents de = entryC.getValue();
		 * System.out.println("Finding in DAY=" + de.getDayNumber());
		 * 
		 * if (de.getDayEvents().size() > 0) { System.out.println("FOUND");
		 * return true; } } } }
		 */

		YearEvents yE = this.getYearEvents(year);
		MonthEvents mE = yE.getMonthEvents(month);
		DayEvents dE = mE.getDayEvents(day);

		if (dE != null) {
			if (dE.getDayEvents().size() > 0) {
				return true;
			}
		}

		return false;
	}

	public boolean hasEvent(int year, int month) {

		/*
		 * for (Entry<Integer, YearEvents> entryA : this.data.entrySet()) { if
		 * (entryA.getKey() != year) { continue; }
		 * 
		 * YearEvents ye = entryA.getValue();
		 * System.out.println("Finding in YEAR=" + ye.getYearNumber());
		 * 
		 * for (Entry<Integer, MonthEvents> entryB :
		 * ye.getMonthEvents().entrySet()) { if (entryB.getKey() != month) {
		 * continue; }
		 * 
		 * MonthEvents me = entryB.getValue();
		 * System.out.println("Finding in MONTH=" + me.getMonthNumber());
		 * 
		 * for (Entry<Integer, DayEvents> entryC : me.getDayEvents().entrySet())
		 * {
		 * 
		 * DayEvents de = entryC.getValue();
		 * System.out.println("Finding in DAY=" + de.getDayNumber());
		 * 
		 * if (de.getDayEvents().size() > 0) { System.out.println("FOUND");
		 * return true; } } } }
		 */

		YearEvents yE = this.getYearEvents(year);
		MonthEvents mE = yE.getMonthEvents(month);

		if (mE != null) {
			if (mE.getDayEvents().size() > 0) {
				for (int i = 0; i < 40; i++) {
					DayEvents dE = mE.getDayEvents(i);
					if (dE != null) {
						if (dE.getDayEvents().size() > 0) {
							return true;
						}
					}

				}
			}
		}

		return false;

	}

	public HashMap<Integer, YearEvents> getAllData() {
		return this.data;
	}

}
