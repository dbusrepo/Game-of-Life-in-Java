package gameoflife.engine.storedNbrs;

public class StateNbrsNextGenMap extends StateNbrsMap {

	private static final int NEXT_GEN_MASK = 0x80000000;

	StateNbrsNextGenMap(int w, int h) {
		super(w, h);
	}

	@Override
	void clearCell(int x, int y) {
		clearNextGen(x, y);
		super.clearCell(x, y);
	}

	@Override
	void setCell(int x, int y) {
		clearNextGen(x, y);
		super.setCell(x, y);
	}

	private void clearNextGen(int x, int y) {
		cells[y][x] &= ~NEXT_GEN_MASK;
	}

	void setNextGen(int x, int y) {
		cells[y][x] |= NEXT_GEN_MASK;
	}

	boolean isNextGen(int x, int y) {
		return (cells[y][x] & NEXT_GEN_MASK) != 0;
	}
}
