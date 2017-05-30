package cz.zcu.viteja.uur.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AgendaEvent {

	private String localizedDayName;
	private String numericDayValue;
	private String localizedMonthName;
	private String year;

	private int startHour;
	private int endHour;

	private String shortDescription;

	public AgendaEvent(String localizedDayName, String numericDayValue, String localizedMonthName, String year,
			int startHour, int endHour, String shortDescription) {
		super();
		this.localizedDayName = localizedDayName;
		this.numericDayValue = numericDayValue;
		this.localizedMonthName = localizedMonthName;
		this.year = year;
		this.startHour = startHour;
		this.endHour = endHour;
		this.shortDescription = shortDescription;
	}

	public String getLocalizedDayName() {
		return localizedDayName;
	}

	public StringProperty getDateColumn() {
		return new SimpleStringProperty(
				String.format("%s, %s. %s", localizedDayName, numericDayValue, localizedMonthName));
	}

	public StringProperty getHourColumn() {
		String start = startHour < 10 ? "0" + startHour + ":00" : "" + startHour + ":00";
		String end = endHour < 10 ? "0" + endHour + ":00" : "" + endHour + ":00";

		return new SimpleStringProperty(String.format("%s - %s", start, end));
	}

	public StringProperty getDescriptionColumn() {
		return new SimpleStringProperty(this.shortDescription);
	}

	public String getLocalizedMonthName() {
		return localizedMonthName;
	}

	public String getYear() {
		return year;
	}

	public int getStartTime() {
		return startHour;
	}

	public int getEndDate() {
		return endHour;
	}

	public String getShortDescription() {
		return shortDescription;
	}

}
