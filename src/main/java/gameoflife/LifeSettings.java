package gameoflife;

import java.awt.Color;

import base.graphics.app.Settings;

public class LifeSettings extends Settings {

	static final int DEFAULT_ROWS = 300;
	static final int DEFAULT_COLS = 300;
	static final int DEFAULT_MAGNIFIER = 4;
	static final Color DEFAULT_ALIVE_COLOR = Color.GREEN;
	static final Color DEFAULT_DEAD_COLOR = Color.BLACK;

	public int width = DEFAULT_COLS;
	public int height = DEFAULT_ROWS;
	public int magnifier = DEFAULT_MAGNIFIER;
	public Color aliveColor = DEFAULT_ALIVE_COLOR;
	public Color deadColor = DEFAULT_DEAD_COLOR;

}
