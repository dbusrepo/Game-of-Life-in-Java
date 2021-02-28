package gameoflife;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import base.graphics.app.GraphAppSoftRendering;
import gameoflife.engine.countNbrs.CountNbrsEngine;

public class Life extends GraphAppSoftRendering {

	LifeSettings settings;
	LifeEngine lifeEng;
	CellDisplay cellGraph;

	public static void main(String[] args) throws Exception {
		new Life();
	}

	public Life() throws Exception {
		initSettings();
		lifeEng = makeLifeEngine();
		cellGraph = new CellDisplay(settings);
		randomizeGrid();
		start(settings);
	}

	private LifeEngine makeLifeEngine() {
		return new CountNbrsEngine(settings.width, settings.height);
//		return new StoredNbrsEngine(settings.width, settings.height);
//		return new StoredNbrsChangeListEngine(settings.width, settings.height);
	}

	private void initSettings() {
		LifeSettings sett = new LifeSettings();
		sett.title = "Game of Life";
		sett.showTitleBar = false;
		sett.showMenu = true;
		sett.showAccelMemory = false;
		sett.showGraphCapabilities = false;
		sett.width = 200;
		sett.height = 150;
		sett.winHeight = sett.height * sett.magnifier;
		sett.winWidth = sett.width * sett.magnifier;
		sett.targetFps = 600;
		this.settings = sett;
	}

	@Override
	public void drawFrame(Graphics2D g) {
		cellGraph.setGraphics(g);
		if (lifeEng.getGenCounter() == 0) {
			g.setBackground(Color.BLACK);
			g.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
			lifeEng.firstGeneration(cellGraph);
		} else {
			lifeEng.nextGeneration(cellGraph);
		}
	}

	@Override
	public void update(long elapsedTime) {
	}

	@Override
	public void finishOff() {
	}

	@Override
	public void printFinalStats() {
//		System.out.println("#Generations:" + lifeEngine.getGeneration());
	}

	private void randomizeGrid() {
		Random r = new Random(123);
		int initLength = (lifeEng.getWidth() * lifeEng.getHeight()) / 2;
		while (initLength > 0) {
			int x = r.nextInt(lifeEng.getWidth());
			int y = r.nextInt(lifeEng.getHeight());
			if (lifeEng.isCellAlive(x, y) == false) {
				lifeEng.setCell(x, y);
			}
			initLength--;
		}

		// test glider
//		lifeEngine.setCell(10, 10);
//		lifeEngine.setCell(11, 10);
//		lifeEngine.setCell(11, 8);
//		lifeEngine.setCell(12, 10);
//		lifeEngine.setCell(12, 9);

//		test blinker
//		lifeEngine.setCell(10, 10);
//		lifeEngine.setCell(11, 10);
//		lifeEngine.setCell(12, 10);
	}
}
