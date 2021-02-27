package gameoflife.engine.storedNbrs;

import gameoflife.ILifeEngine;

public class StoredNbrsChangeListEngine implements ILifeEngine {

	private static final int SHIFT = 32;

	StateNbrsNextGenMap cells;
	LongDynArray live, die;
	LongDynArray nextLive, nextDie;
	int generation;

	public StoredNbrsChangeListEngine(int w, int h) {
		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("sizes not correct");
		}
		cells = new StateNbrsNextGenMap(w, h);
		live = new LongDynArray();
		die = new LongDynArray();
		nextLive = new LongDynArray();
		nextDie = new LongDynArray();
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
		if (generation == 0) {
			initLists();
		}
		// inv: here live and die (change) lists contain the cells to update for
		// this new generation. Those cells have the nextGen flag on. Note: in
		// this implementation, live and die lists do not contain
		// duplicates or false positives.
		// nextLive, nextDie are empty here
		updateCells(); // visit the change lists and update the cells
		updateLists(); // visits the change lists again and build the next
						// change lists
		++generation;
	}

	private void initLists() {
		// first generation requires a full scan of the cellmap to init the
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
						addNextDieCell(x, y);
					}
				} else {
					if (cnt == 3) {
						addNextLiveCell(x, y);
					}
				}
			}
		}
		swapLists();
	}

	private void updateCells() {
		updateLive();
		updateDie();
	}

	private void updateLive() {
		long[] liveCells = live.getList();
		int size = live.getSize();
		for (int i = 0; i != size; ++i) {
			long val = liveCells[i];
			int x = (int) (val >>> SHIFT);
			int y = (int) val;
			cells.setCell(x, y);
		}
	}

	private void updateDie() {
		long[] dieCells = die.getList();
		int size = die.getSize();
		for (int i = 0; i != size; ++i) {
			long val = dieCells[i];
			int x = (int) (val >>> SHIFT);
			int y = (int) val;
			cells.clearCell(x, y);
		}
	}

	private void updateLists() {
		// run down the lists again and check the neighbors of the cells
		// that changed in this generation. Filter those that will change in
		// the next gen and build the new change lists in the process
		checkNbrs(live);
		checkNbrs(die);
		swapLists(); // swaps lists (nextlive->live, ...) and keep the inv
	}

	private void checkNbrs(LongDynArray posList) {
		long[] liveCells = posList.getList();
		int size = posList.getSize();
		for (int i = 0; i != size; ++i) {
			long val = liveCells[i];
			int x = (int) (val >>> SHIFT);
			int y = (int) val;
			checkNbrs(x, y);
		}
	}

	private void checkNbrs(int x, int y) {
		int width = cells.getWidth();
		int height = cells.getHeight();
		int xoleft = (x == 0) ? width - 1 : -1;
		int xoright = (x == width - 1) ? -(width - 1) : 1;
		int yoabove = (y == 0) ? (height - 1) : -1;
		int yobelow = (y == height - 1) ? -(height - 1) : 1;
		int xleft = x + xoleft;
		int xright = x + xoright;
		int yabove = y + yoabove;
		int ybelow = y + yobelow;
		checkCell(xleft, yabove);
		checkCell(x, yabove);
		checkCell(xright, yabove);
		checkCell(xleft, y);
		checkCell(xright, y);
		checkCell(xleft, ybelow);
		checkCell(x, ybelow);
		checkCell(xright, ybelow);
	}

	private void checkCell(int x, int y) {
		int cnt = cells.getNbrsCnt(x, y);
		if (isCellAlive(x, y)) {
			if (cnt != 2 && cnt != 3) {
				if (!cells.isNextGen(x, y)) { // if marked skip to avoid
												// duplicates
					addNextDieCell(x, y);
				}
			}
		} else {
			if (cnt == 3) {
				if (!cells.isNextGen(x, y)) {
					addNextLiveCell(x, y);
				}
			}
		}
	}

	private void addNextLiveCell(int x, int y) {
		cells.setNextGen(x, y); // mark the cell
		nextLive.insert(makePos(x, y));
	}

	private void addNextDieCell(int x, int y) {
		cells.setNextGen(x, y);
		nextDie.insert(makePos(x, y));
	}

	private void swapLists() {
		LongDynArray tmp = live;
		live = nextLive;
		nextLive = tmp;
		tmp = die;
		die = nextDie;
		nextDie = tmp;
		nextLive.clear();
		nextDie.clear();
	}

	// encode the position x,y as a long
	// note:
	// https://stackoverflow.com/questions/1023373/findbugs-warning-integer-shift-by-32-what-does-it-mean
	private long makePos(int x, int y) {
		long value = ((long) x << SHIFT) | y;
		return value;
	}

	@Override
	public int getGeneration() {
		return generation;
	}

}
