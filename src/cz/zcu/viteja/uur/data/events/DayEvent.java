package cz.zcu.viteja.uur.data.events;

public class DayEvent {

	private int year;
	private int month;
	private int day;

	private int startHour;
	private int endHour;

	private String description;

	public DayEvent(int year, int month, int day, int startHour, int endHour, String description) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.startHour = startHour;
		this.endHour = endHour;
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getStartHour() {
		return startHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String s) {
		this.description = s;
	}

	@Override
	public String toString() {
		return String.format("%d. %d. %d - %s", this.day, this.month, this.year, this.description);

	}

}
