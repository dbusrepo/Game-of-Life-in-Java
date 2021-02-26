package gameoflife.engine.basic;

// impl based on array of arrays
class CellMapDArray implements ICellMap {

	int width, height;
	int[][] cells;

	// w, h >= 1
	CellMapDArray(int w, int h) {
		assert w >= 1 && h >= 1;
		width = w;
		height = h;
		cells = new int[h][];
		for (int y = 0; y != h; ++y) {
			cells[y] = new int[w];
			for (int x = 0; x != w; ++x) {
				clearCell(x, y);
			}
		}
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	// no wrapping here
	@Override
	public int cellState(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return 0; // return 0 for off edges if no wrapping
		}
		return cells[y][x];
	}

	@Override
	public void setCell(int x, int y) {
		cells[y][x] = BasicLifeEngine.ALIVE;
	}

	@Override
	public void clearCell(int x, int y) {
		cells[y][x] = BasicLifeEngine.DEAD;
	}

	@Override
	public void copyCells(ICellMap sourceMap) {
		assert sourceMap instanceof CellMapDArray;
		CellMapDArray source = (CellMapDArray) sourceMap;
		for (int y = 0; y != height; ++y) {
			System.arraycopy(source.cells[y], 0, cells[y], 0, width);
		}
	}

}