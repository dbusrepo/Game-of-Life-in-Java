package gameoflife;

import java.awt.Graphics2D;

import base.graphics.app.GraphAppSoftRendering;
import gameoflife.engine.countNbrs.CountNbrsEngine;

public class Life extends GraphAppSoftRendering {

	LifeSettings settings;
	ILifeEngine lifeEngine;
	CellDisplay cellGraph;

	public static void main(String[] args) throws Exception {
		new Life();
	}

	public Life() throws Exception {
		initSettings();
		lifeEngine = makeLifeEngine();
		cellGraph = new CellDisplay(settings.width, settings.height,
				settings.magnifier);
		randomizeGrid();
		start(settings);
	}

	private ILifeEngine makeLifeEngine() {
		return new CountNbrsEngine(settings.height, settings.width);
	}

	private void initSettings() {
		LifeSettings sett = new LifeSettings();
		sett.title = "Game of Life";
		sett.showTitleBar = false;
		sett.showMenu = true;
		sett.showAccelMemory = false;
		sett.showGraphCapabilities = false;
		sett.winWidth = sett.height * sett.magnifier;
		sett.winHeight = sett.width * sett.magnifier;
		sett.targetFps = 5;
//		stgs.statsBgCol = Color.GREEN;
		this.settings = sett;
	}

	@Override
	public void drawFrame(Graphics2D g) {
//		super.drawFrameApp(g);
		cellGraph.setGraphics(g);
		lifeEngine.nextGeneration(cellGraph);
	}

	@Override
	public void update(long elapsedTime) {
		// not used
	}

	@Override
	public void finishOff() {
	}

	@Override
	public void printFinalStats() {
//		System.out.println("#Generations:" + lifeEngine.getGeneration());
	}

	private void randomizeGrid() {
//		Random r = new Random(123);
//		int initLength = (lifeEngine.getWidth() * lifeEngine.getHeight()) / 2;
//		while (initLength > 0) {
//			int x = r.nextInt(lifeEngine.getWidth());
//			int y = r.nextInt(lifeEngine.getHeight());
//			if (lifeEngine.isCellAlive(x, y) == false) {
//				lifeEngine.setCell(x, y);
//			}
//			initLength--;
//		}
		lifeEngine.setCell(10, 10);
		lifeEngine.setCell(11, 10);
		lifeEngine.setCell(12, 10);
	}
}
