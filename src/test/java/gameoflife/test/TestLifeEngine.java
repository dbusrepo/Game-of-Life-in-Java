package gameoflife.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import gameoflife.GridDisplay;
import gameoflife.LifeEngine;
import gameoflife.engine.countNbrs.CountNbrsEngine;
import gameoflife.engine.storedNbrs.StoredNbrsChangeListEngine;

class TestLifeEngine {

	@Disabled
	@Test
	void test() {
		int seed = 37435;
		int w = 150;
		int h = 200;
		int numGen = 100;
		int initLength = (w * h) / 2;
		Random r = new Random(seed);
		LifeEngine baseEng = makeBaseEngine(w, h);
		LifeEngine curEng = makeEngine(w, h);
		GridDisplay nullCellGrap = new GridDisplay(null) {

			@Override
			public void showCell(int x, int y) {
			}

			@Override
			public void hideCell(int x, int y) {
			}

		};
		// init cell maps
		while (initLength > 0) {
			int x = r.nextInt(w);
			int y = r.nextInt(h);
			if (curEng.isCellAlive(x, y) == false) {
				baseEng.setCell(x, y);
				curEng.setCell(x, y);
			}
			initLength--;
		}

		// test generations
		for (int g = 0; g <= numGen; ++g) {
			for (int y = 0; y != h; ++y) {
				for (int x = 0; x != w; ++x) {
					assertTrue(baseEng.isCellAlive(x, y) == curEng
							.isCellAlive(x, y));
				}
			}
			baseEng.nextGeneration(nullCellGrap);
			curEng.nextGeneration(nullCellGrap);
		}
	}

	private LifeEngine makeBaseEngine(int w, int h) {
		return new CountNbrsEngine(w, h);
//		return new StoredNbrsEngine(w, h);
	}

	private LifeEngine makeEngine(int w, int h) {
//		return new StoredNbrsEngine(w, h);
		return new StoredNbrsChangeListEngine(w, h);
//		return new CountNbrsEngine(w, h);
	}

}
