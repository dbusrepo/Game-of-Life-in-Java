package gameoflife.engine.basic;

class CellMapWrapEdges implements ICellMap {

	ICellMap cellMap;

	CellMapWrapEdges(ICellMap cellMap) {
		this.cellMap = cellMap;
	}

	@Override
	public int cellState(int x, int y) {
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
		return cellMap.cellState(x, y);
	}

	@Override
	public int getHeight() {
		return cellMap.getHeight();
	}

	@Override
	public int getWidth() {
		return cellMap.getWidth();
	}

	@Override
	public void setCell(int x, int y) {
		cellMap.setCell(x, y);
	}

	@Override
	public void clearCell(int x, int y) {
		cellMap.clearCell(x, y);
	}

	@Override
	public void copyCells(ICellMap sourceMap) {
		cellMap.copyCells(sourceMap);
	}

}