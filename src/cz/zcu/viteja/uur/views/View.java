package cz.zcu.viteja.uur.views;

import javafx.scene.Node;
import javafx.scene.Scene;

public abstract class View {

	public abstract Scene setup();

	protected abstract Node setupTop();

	protected abstract Node setupLeft();

	protected abstract Node setupRight();

	protected abstract Node setupCenter();

	protected abstract Node setupBottom();
}
