package cz.zcu.viteja.uur.data;

public class YearCalendarData {

	private String[] data;

	public YearCalendarData(int workYear) {

		this.createData();
	}

	private void createData() {
		this.data = DateUtils.getMonthNamesFromZero();
	}

	public String[] getData() {
		return this.data;
	}

}
