package gameoflife.engine.storeNbrs;

import gameoflife.ILifeEngine;

public class StoreNbrsChangeListEngine implements ILifeEngine {

	private static final int SHIFT = 32;

	CellMap cells;
	PosList live, die;
	int numGeneration;

	public StoreNbrsChangeListEngine(int w, int h) {
		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("sizes not correct");
		}
		cells = makeCellMap(w, h);
	}

	private CellMap makeCellMap(int w, int h) {
		return new CellMap(w, h);
	}

	@Override
	public boolean isCellAlive(int x, int y) {
		return cells.cellState(x, y) != 0;
	}

	@Override
	public void setCell(int x, int y) {
		cells.setCell(x, y);
	}

	@Override
	public void clearCell(int x, int y) {
		cells.clearCell(x, y);
	}

	@Override
	public void nextGeneration() {
		if (numGeneration == 0) {
			initLists();
		}
		// inv: live, die lists here contain the cells to update in the
		// next gen. Note: there can be false positives and duplicates (this
		// implementation admits them here)
		updateCells(); // update cells status
		// live, die lists have been updated to contain only the effective
		// updated cells (so no more false positives or duplicates here)
		// Now to maintain the invariant, we need to update:
		updateLists();
	}

	private void initLists() {
		// first generation: it requires a full scan of the cellmap to init the
		// lists
		for (int y = 0; y != cells.getHeight(); ++y) {
			int[] row = cells.getRow(y);
			for (int x = 0; x != cells.getWidth(); ++x) {
				if (row[x] == 0) { // fast skip of zero cells
					continue;
				}
				int cnt = cells.getNbrsCnt(x, y);
				if (isCellAlive(x, y)) {
					if (cnt != 2 && cnt != 3) {
						die.insert(makePos(x, y));
					}
				} else {
					if (cnt == 3) {
						live.insert(makePos(x, y));
					}
				}
			}
		}
	}

	// encode the position x,y as a long
	private long makePos(int x, int y) {
		long value = (x << SHIFT) | y;
		return value;
	}

	private void updateLists() {
		// TODO Auto-generated method stub
	}

	private void updateCells() {

	}

}
