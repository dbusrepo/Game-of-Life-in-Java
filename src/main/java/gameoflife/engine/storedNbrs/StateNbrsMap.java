package gameoflife.engine.storedNbrs;

class StateNbrsMap {

	private static final int STATE_MASK = 0x1;
	private static final int NBRS_INC = 2; // to sum/sub 1 to the 2^ bit
	private static final int NBRS_SHIFT = 1;

	int width, height;
	int[][] cells; // stores the state (first bit) followed by the number of
					// alive neighbors.

	StateNbrsMap(int w, int h) {
		assert w >= 1 && h >= 1;
		width = w;
		height = h;
		cells = new int[h][];
		for (int y = 0; y != h; ++y) {
			cells[y] = new int[w]; // set to 0, ok
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	void clearCell(int x, int y) {
		cells[y][x] &= ~STATE_MASK;
		updateNbrsCnt(x, y, -NBRS_INC);
	}

	int cellState(int x, int y) {
		return cells[y][x] & STATE_MASK;
	}

	void setCell(int x, int y) {
		cells[y][x] |= STATE_MASK;
		updateNbrsCnt(x, y, NBRS_INC);
	}

	int[] getRow(int y) {
		return cells[y];
	}

	int getNbrsCnt(int x, int y) {
		return cells[y][x] >> NBRS_SHIFT;
	}

	private void updateNbrsCnt(int x, int y, int inc) {
		int xoleft = (x == 0) ? width - 1 : -1;
		int xoright = (x == width - 1) ? -(width - 1) : 1;
		int yoabove = (y == 0) ? (height - 1) : -1;
		int yobelow = (y == height - 1) ? -(height - 1) : 1;
		int[] rowAbove = cells[y + yoabove];
		int[] row = cells[y];
		int[] rowBelow = cells[y + yobelow];
		int xleft = x + xoleft;
		int xright = x + xoright;
		rowAbove[xleft] += inc;
		rowAbove[x] += inc;
		rowAbove[xright] += inc;
		row[xleft] += inc;
		row[xright] += inc;
		rowBelow[xleft] += inc;
		rowBelow[x] += inc;
		rowBelow[xright] += inc;
	}

	public void copyCells(StateNbrsMap sourceMap) {
		for (int y = 0; y != height; ++y) {
			System.arraycopy(sourceMap.cells[y], 0, cells[y], 0, width);
		}
	}
}
