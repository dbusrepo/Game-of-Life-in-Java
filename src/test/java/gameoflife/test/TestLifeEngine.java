package gameoflife.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import gameoflife.ILifeEngine;
import gameoflife.LifeEngine;
import gameoflife.engine.basic.BasicLifeEngine;

class TestLifeEngine {

	@Test
	void test() {
		int seed = 374;
		int w = 15;
		int h = 20;
		int numGen = 100;
		int initLength = (w * h) / 2;
		Random r = new Random(seed);
		ILifeEngine basic = makeBasicEngine(w, h);
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
		for (int g = 0; g <= numGen; ++g) {
			for (int y = 0; y != h; ++y) {
				for (int x = 0; x != w; ++x) {
					assertTrue(basic.isCellAlive(x, y) == engine.isCellAlive(x,
							y));
				}
			}
			basic.nextGeneration();
			engine.nextGeneration();
		}
	}

	private ILifeEngine makeBasicEngine(int w, int h) {
		return new BasicLifeEngine(w, h);
	}

	private ILifeEngine makeEngine(int w, int h) {
		return new LifeEngine(w, h);
	}

}
