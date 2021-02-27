package gameoflife.engine.storeNbrs;

import gameoflife.ILifeEngine;

public class StoreNbrsEngine implements ILifeEngine {

	CellMap cells, nextCells;

	public StoreNbrsEngine(int w, int h) {
		if (w < 1 || h < 1) {
			throw new IllegalArgumentException("sizes not correct");
		}
		cells = makeCellMap(w, h);
		nextCells = makeCellMap(w, h);
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
		nextCells.copyCells(cells);
		int width = cells.getWidth();
		for (int y = 0; y != cells.getHeight(); ++y) {
			int[] row = cells.getRow(y);
			int x = 0;
			rowDone: while (x != width) {
				while (row[x] == 0) {
					if (++x >= width) {
						break rowDone;
					}
				}
				int cnt = cells.getNbrsCnt(x, y);
				if (isCellAlive(x, y)) {
					if (cnt != 2 && cnt != 3) {
						nextCells.clearCell(x, y);
					}
				} else {
					if (cnt == 3) {
						nextCells.setCell(x, y);
					}
				}
				++x;
			}
		}
		swapCellMaps();
	}

	private void swapCellMaps() {
		CellMap tmp = cells;
		cells = nextCells;
		nextCells = tmp;
	}

}
