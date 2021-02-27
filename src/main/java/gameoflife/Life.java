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
		LifeSettings sett = new LifeSettings();
		sett.title = "Game of Life";
		sett.showTitleBar = false;
		sett.showMenu = true;
		sett.showAccelMemory = false;
		sett.showGraphCapabilities = false;
		sett.winWidth = sett.cols * sett.magnifer;
		sett.winHeight = sett.rows * sett.magnifer;
//		stgs.statsBgCol = Color.GREEN;
		this.settings = sett;
	}

	@Override
	public void drawFrame(Graphics2D g) {
//		super.drawFrameApp(g);
		lifeEngine.nextGeneration();
	}

	@Override
	public void update(long elapsedTime) {
	}

	@Override
	public void finishOff() {
		// TODO Auto-generated method stub
	}

	@Override
	public void printFinalStats() {
		System.out.println("#Generations:" + lifeEngine.getGeneration());
	}
}
