package gameoflife;

import java.awt.Color;
import java.util.Random;

import base.graphics.app.GraphAppSoftRendering;
import gameoflife.engine.countNbrs.CountNbrsEngine;

public class Life extends GraphAppSoftRendering {

	LifeSettings settings;
	LifeEngine lifeEng;
	GridDisplay gridDisplay;

	public static void main(String[] args) throws Exception {
		new Life();
	}

	public Life() throws Exception {
		settings = initSettings();
		start(settings);
	}

	private LifeEngine makeLifeEngine() {
		return new CountNbrsEngine(settings.width, settings.height);
//		return new StoredNbrsEngine(settings.width, settings.height);
//		return new StoredNbrsChangeListEngine(settings.width, settings.height);
	}

	private LifeSettings initSettings() {
		LifeSettings settgs = new LifeSettings();
		settgs.title = "Game of Life";
		settgs.showTitleBar = false;
		settgs.showMenu = true;
		settgs.showAccelMemory = false;
		settgs.showGraphCapabilities = false;
		settgs.width = 400;
		settgs.height = 300;
		settgs.magnifier = 2;
		settgs.winHeight = settgs.height * settgs.magnifier;
		settgs.winWidth = settgs.width * settgs.magnifier;
		settgs.targetFps = 60;
		return settgs;
	}

	@Override
	public void init() {
		super.init(); // build buffer image...
		lifeEng = makeLifeEngine();
		randomizeGrid();
		gridDisplay = new GridDisplay(settings);
		gridDisplay.setGraphics(g2Dimg);
		initGridImage();
	}

	private void initGridImage() {
		g2Dimg.setBackground(Color.BLACK);
		g2Dimg.clearRect(0, 0, getCanvas().getWidth(), getCanvas().getHeight());
		lifeEng.firstGeneration(gridDisplay);
	}

	@Override
	protected void updateImage() {
		lifeEng.nextGeneration(gridDisplay);
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
		Random r = new Random();
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
//		lifeEng.setCell(10, 10);
//		lifeEng.setCell(11, 10);
//		lifeEng.setCell(11, 8);
//		lifeEng.setCell(12, 10);
//		lifeEng.setCell(12, 9);

//		test blinker
//		lifeEng.setCell(10, 10);
//		lifeEng.setCell(11, 10);
//		lifeEng.setCell(12, 10);
	}

}
