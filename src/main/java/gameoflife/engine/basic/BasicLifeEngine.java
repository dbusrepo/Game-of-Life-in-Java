package gameoflife.engine.basic;

import gameoflife.ILifeEngine;

public class BasicLifeEngine implements ILifeEngine {

	static final int DEAD = 0;
	static final int ALIVE = 1;

	ICellMap cells, nextCells;

	public BasicLifeEngine(int w, int h) {
		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("sizes not correct");
		}
		cells = makeCellMap(w, h);
		nextCells = makeCellMap(w, h);
	}

	private ICellMap makeCellMap(int w, int h) {
		return null; // new CellMapWrapEdges(w, h);
	}

	@Override
	public boolean isCellAlive(int x, int y) {
		return cells.cellState(x, y) == ALIVE;
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
		nextCells.copyCells(cells);
		for (int y = 0; y != cells.getHeight(); ++y) {
			for (int x = 0; x != cells.getWidth(); ++x) {
				int numNbrs = countNeighbor(x, y);
				if (isCellAlive(x, y)) {
					if (numNbrs != 2 && numNbrs != 3) {
						nextCells.clearCell(x, y);
					}
				} else { // the cell is off
					if (numNbrs == 3) {
						nextCells.setCell(x, y);
					}
				}
			}
		}
		swapCellMaps();
	}

	private void swapCellMaps() {
		ICellMap tmp = cells;
		cells = nextCells;
		nextCells = tmp;
	}

	private int countNeighbor(int x, int y) {
		return cells.cellState(x - 1, y - 1) + cells.cellState(x, y - 1)
				+ cells.cellState(x + 1, y - 1) + cells.cellState(x - 1, y)
				+ cells.cellState(x + 1, y) + cells.cellState(x - 1, y + 1)
				+ cells.cellState(x, y + 1) + cells.cellState(x + 1, y + 1);
	}

}
