package gameoflife;

public interface ILifeEngine {

	boolean isCellAlive(int x, int y);

	void setCell(int x, int y); // set a cell alive

	void clearCell(int x, int y); // set a cell dead

	void nextGeneration();

}
