package gameoflife.engine.basic;

interface ICellMap {

	int getHeight();

	int getWidth();

	int cellState(int x, int y);

	void setCell(int x, int y);

	void clearCell(int x, int y);

	// assume same size and type
	void copyCells(ICellMap sourceMap);

}
