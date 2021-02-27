package gameoflife.engine.countNbrs;

import gameoflife.ILifeEngine;

public class CountNbrsEngine implements ILifeEngine {

	static final int DEAD = 0;
	static final int ALIVE = 1;

	StateMap cells, nextCells;
	private int generation;

	public CountNbrsEngine(int w, int h) {
		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("sizes not correct");
		}
		cells = makeCellMap(w, h);
		nextCells = makeCellMap(w, h);
	}

	private StateMap makeCellMap(int w, int h) {
		return new StateMap(w, h);
	}

	@Override
	public boolean isCellAlive(int x, int y) {
		return cells.cellState(x, y) == ALIVE;
	}

	@Override
	public void setCell(int x, int y) {
		cells.setCellState(x, y, ALIVE);
	}

	@Override
	public void clearCell(int x, int y) {
		cells.setCellState(x, y, DEAD);
	}

	@Override
	public void nextGeneration() {
		nextCells.copyCells(cells);
		for (int y = 0; y != cells.getHeight(); ++y) {
			for (int x = 0; x != cells.getWidth(); ++x) {
				int numNbrs = countNeighbors(x, y);
				if (isCellAlive(x, y)) {
					if (numNbrs != 2 && numNbrs != 3) {
						nextCells.setCellState(x, y, DEAD);
					}
				} else { // the cell is off
					if (numNbrs == 3) {
						nextCells.setCellState(x, y, ALIVE);
					}
				}
			}
		}
		swapCellMaps();
		++generation;
	}

	private void swapCellMaps() {
		StateMap tmp = cells;
		cells = nextCells;
		nextCells = tmp;
	}

	private int countNeighbors(int x, int y) {
		return cells.cellState(x - 1, y - 1) + cells.cellState(x, y - 1)
				+ cells.cellState(x + 1, y - 1) + cells.cellState(x - 1, y)
				+ cells.cellState(x + 1, y) + cells.cellState(x - 1, y + 1)
				+ cells.cellState(x, y + 1) + cells.cellState(x + 1, y + 1);
	}

	@Override
	public int getGeneration() {
		return generation;
	}

}
