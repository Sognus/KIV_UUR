package cz.zcu.viteja.uur.data;

public class MonthInfo {

	private int previousMonthDays;
	private int monthDays;
	private int nextMonthDays;

	private String[] data;

	public MonthInfo(String[] data, int previousMonthDays, int monthDays, int nextMonthDays) {
		this.data = data;
		this.previousMonthDays = previousMonthDays;
		this.monthDays = monthDays;
		this.nextMonthDays = nextMonthDays;
	}

	public int getPreviousMonthDayCount() {
		return previousMonthDays;
	}

	public int getCurrentMonthDayCount() {
		return monthDays;
	}

	public int getNextMonthDayCount() {
		return nextMonthDays;
	}

	public String[] getData() {
		return data;
	}

}
