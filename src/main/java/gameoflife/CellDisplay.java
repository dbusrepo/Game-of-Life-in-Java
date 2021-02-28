package gameoflife;

import java.awt.Color;
import java.awt.Graphics2D;

public class CellDisplay {

	int width, height;
	int magnifier;
	Graphics2D g;

	public CellDisplay(int width, int height, int magnifier) {
		this.width = width;
		this.height = height;
		this.magnifier = magnifier;
	}

	void setGraphics(Graphics2D g) {
		this.g = g;
	}

	public void showCell(int x, int y) {
		g.setColor(Color.GREEN);
		g.fillRect(x * magnifier, y * magnifier, magnifier, magnifier);
	}

	public void hideCell(int x, int y) {
		g.setColor(Color.BLACK);
		g.fillRect(x * magnifier, y * magnifier, magnifier, magnifier);
	}
}
