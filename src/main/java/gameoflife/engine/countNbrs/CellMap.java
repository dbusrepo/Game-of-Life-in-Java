package gameoflife.engine.countNbrs;

class CellMap {

	int width, height;
	int[][] cells;

	// w, h >= 1
	CellMap(int w, int h) {
		assert w >= 1 && h >= 1;
		width = w;
		height = h;
		cells = new int[h][];
		for (int y = 0; y != h; ++y) {
			cells[y] = new int[w];
			for (int x = 0; x != w; ++x) {
				setCellState(x, y, CountNbrsLifeEngine.DEAD);
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	// no wrapping here
	int cellState(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return 0; // return 0 for off edges
		}
		return cells[y][x];
	}

	void setCellState(int x, int y, int state) {
		cells[y][x] = state;
	}

	// here we assume same size
	void copyCells(CellMap sourceMap) {
		assert sourceMap instanceof CellMap;
		CellMap source = sourceMap;
		for (int y = 0; y != height; ++y) {
			System.arraycopy(source.cells[y], 0, cells[y], 0, width);
		}
	}

}