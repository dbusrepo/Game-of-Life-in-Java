package gameoflife;

import java.awt.Graphics2D;

public class GridDisplay {

	LifeSettings setts;
	Graphics2D g;

	public GridDisplay(LifeSettings setts) {
		this.setts = setts;
	}

	void setGraphics(Graphics2D g) {
		this.g = g;
	}

	public void showCell(int x, int y) {
		g.setColor(setts.aliveColor);
		drawCell(x, y);
	}

	public void hideCell(int x, int y) {
		g.setColor(setts.deadColor);
		drawCell(x, y);
	}

	private void drawCell(int x, int y) {
		g.fillRect(x * setts.magnifier, y * setts.magnifier, setts.magnifier,
				setts.magnifier);
	}

}
