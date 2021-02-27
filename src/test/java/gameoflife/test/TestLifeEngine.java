package gameoflife.test;

import java.util.Random;

import org.junit.jupiter.api.Test;

import gameoflife.ILifeEngine;
import gameoflife.engine.countNbrs.CountNbrsLifeEngine;
import gameoflife.engine.storeNbrs.StoreNbrsLifeEngine;

class TestLifeEngine {

	@Test
	void test() {
		int seed = 3745;
		int w = 150;
		int h = 200;
		int numGen = 100;
		int initLength = (w * h) / 2;
		Random r = new Random(seed);
		ILifeEngine basic = makeBaseEngine(w, h);
		ILifeEngine engine = makeEngine(w, h);
		// init cell maps
		while (initLength > 0) {
			int x = r.nextInt(w);
			int y = r.nextInt(h);
			if (basic.isCellAlive(x, y) == false) {
				basic.setCell(x, y);
				engine.setCell(x, y);
			}
			initLength--;
		}
		// test generations
//		for (int g = 0; g <= numGen; ++g) {
//			for (int y = 0; y != h; ++y) {
//				for (int x = 0; x != w; ++x) {
//					assertTrue(basic.isCellAlive(x, y) == engine.isCellAlive(x,
//							y));
//				}
//			}
//			basic.nextGeneration();
//			engine.nextGeneration();
//		}
	}

	private ILifeEngine makeBaseEngine(int w, int h) {
		return new CountNbrsLifeEngine(w, h);
	}

	private ILifeEngine makeEngine(int w, int h) {
		return new StoreNbrsLifeEngine(w, h);
	}

}
