package gameoflife;

import java.awt.Graphics2D;

import base.graphics.app.GraphAppSoftRendering;
import gameoflife.engine.countNbrs.CountNbrsEngine;

public class Life extends GraphAppSoftRendering {

	LifeSettings settings;
	ILifeEngine lifeEngine;

	public static void main(String[] args) throws Exception {
		new Life();
	}

	public Life() throws Exception {
		initSettings();
		lifeEngine = makeLifeEngine();
		start(settings);
	}

	private ILifeEngine makeLifeEngine() {
		return new CountNbrsEngine(settings.cols, settings.rows);
	}

	private void initSettings() {
		LifeSettings stgs = new LifeSettings();
		stgs.title = "Game of Life";
		stgs.showTitleBar = false;
		stgs.showMenu = true;
		stgs.showGraphCapabilities = false;
		stgs.winWidth = stgs.cols * stgs.magnifer;
		stgs.winHeight = stgs.rows * stgs.magnifer;
//		stgs.showFps = false;
		settings = stgs;
	}

	@Override
	public void drawFrameApp(Graphics2D g) {
//		super.drawFrameApp(g);
		lifeEngine.nextGeneration();
	}

	@Override
	public void updateApp(long elapsedTime) {
	}

	@Override
	public void finishOffApp() {
		// TODO Auto-generated method stub
	}

	@Override
	public void printFinalStatsApp() {
		System.out.println("#Generations:" + lifeEngine.getGeneration());
	}
}
