package gameoflife;

import base.graphics.app.GraphAppSoftRendering;
import base.graphics.app.Settings;

public class Main extends GraphAppSoftRendering {
	Settings settings;

	public static void main(String[] args) throws Exception {
		new Main();
	}

	public Main() throws Exception {
		settings = new Settings();
		settings.winWidth = 300;
		settings.winHeight = 300;
		settings.title = "Game of Life";
		settings.showTitleBar = false;
		settings.showMenu = true;
		start(settings);
	}

	@Override
	public void updateApp(long elapsedTime) {
		// TODO Auto-generated method stub
	}

	@Override
	public void finishOffApp() {
		// TODO Auto-generated method stub
	}

	@Override
	public void printFinalStatsApp() {
		// TODO Auto-generated method stub
	}
}
