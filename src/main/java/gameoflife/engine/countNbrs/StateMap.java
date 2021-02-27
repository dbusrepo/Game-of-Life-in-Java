package gameoflife.engine.countNbrs;

class StateMap {

	int width, height;
	int[][] cells; // stores the state, alive or dead

	StateMap(int w, int h) {
		assert w >= 1 && h >= 1;
		width = w;
		height = h;
		cells = new int[h][];
		for (int y = 0; y != h; ++y) {
			cells[y] = new int[w];
			for (int x = 0; x != w; ++x) {
				setCellState(x, y, CountNbrsEngine.DEAD);
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	int cellState(int x, int y) {
//		if (x < 0 || x >= width || y < 0 || y >= height) {
//			return 0; // return 0 for off edges if no wrapping
//		}
		while (x < 0)
			x += width;
		while (x >= width)
			x -= width;
		while (y < 0)
			y += height;
		while (y >= height)
			y -= height;
		return cells[y][x];
	}

	void setCellState(int x, int y, int state) {
		cells[y][x] = state;
	}

	// here we assume same size
	void copyCells(StateMap sourceMap) {
		for (int y = 0; y != height; ++y) {
			System.arraycopy(sourceMap.cells[y], 0, cells[y], 0, width);
		}
	}

}