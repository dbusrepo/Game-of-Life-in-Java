package gameoflife;

public abstract class LifeEngine {

	protected int genCnt;

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract boolean isCellAlive(int x, int y);

	public abstract void setCell(int x, int y); // set a cell alive

	public abstract void clearCell(int x, int y); // set a cell dead

	public void nextGeneration(GridDisplay gd) {
		updateGeneration(gd);
		incGenCnt();
	}

	protected abstract void updateGeneration(GridDisplay gd);

	public int getGenCounter() {
		return genCnt;
	}

	public void firstGeneration(GridDisplay gd) {
		for (int y = 0; y != getHeight(); ++y) {
			for (int x = 0; x != getWidth(); ++x) {
				if (isCellAlive(x, y)) {
					gd.showCell(x, y);
				}
			}
		}
		incGenCnt();
	}

	private void incGenCnt() {
		genCnt++;
	}

}
