package gameoflife;

public interface ILifeEngine {

	int getWidth();

	int getHeight();

	boolean isCellAlive(int x, int y);

	void setCell(int x, int y); // set a cell alive

	void clearCell(int x, int y); // set a cell dead

	void nextGeneration(CellDisplay cg);

	int getGeneration(); // gen number

}
