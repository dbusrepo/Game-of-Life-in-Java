package gameoflife.engine.countNbrs;

class CellMapWrapEdges extends CellMap {

	CellMapWrapEdges(int w, int h) {
		super(w, h);
	}

	@Override
	int cellState(int x, int y) {
		int width = getWidth();
		int height = getHeight();
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

}