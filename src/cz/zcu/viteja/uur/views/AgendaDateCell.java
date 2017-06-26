package cz.zcu.viteja.uur.views;

import java.time.LocalDate;

import cz.zcu.viteja.uur.data.AgendaEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.text.Font;

public class AgendaDateCell extends TableCell<AgendaEvent, String> {
	private DatePicker dateDP;

	// switching to the editation mode
	public void startEdit() {
		// propagation of mode switching to the parent class
		super.startEdit();

		// creating editor - DatePicker if it is not already prepared
		if (dateDP == null) {
			createDatePicker();
		}
		// disabling label with text
		setText(null);
		// setting up editor
		setGraphic(dateDP);
		// opening editor (fields with calendar)
		dateDP.show();
	}

	// switching from editation to presentation mode
	public void cancelEdit() {
		super.cancelEdit();

		// setting label
		setText(getItem().toString());
		// removing date picker
		setGraphic(null);
	}

	// creating date picker
	private void createDatePicker() {
		dateDP = new DatePicker(LocalDate.now());
		// forbidding writing to the date picker field - only mouse selection of
		// the date is allowed
		dateDP.setEditable(false);

		// reaction on commit - when date is selected, date picker have to
		// create commit event
		dateDP.setOnAction(event -> {
			// commit when date was selected
			if (dateDP.getValue() != null) {
				commitEdit("");
				// cancel when no date was provided
			} else {
				cancelEdit();
			}
		});
	}

	// setting value to the cell when model is changed or manipulated
	// this method have to be always overloaded, when new cell type is created
	public void updateItem(String item, boolean empty) {
		// propagating change to the parent class
		super.updateItem(item, empty);

		// setting font for the label
		setFont(new Font("Arial", 15));
		// when no item is provided, cell is showing no information
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			// in editation mode
			if (isEditing()) {
				// setting the value to editor - date picker
				if (dateDP != null) {
					dateDP.setValue(LocalDate.now());
				}
				// disabling label
				setText(null);
				// setting up editor
				setGraphic(dateDP);
				// in presentation mode
			} else {
				// setting the date to label
				setText(getItem().toString());
				// disabling editor
				setGraphic(null);
			}
		}
	}

}
