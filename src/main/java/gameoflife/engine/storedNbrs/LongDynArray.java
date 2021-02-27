package gameoflife.engine.storedNbrs;

public class LongDynArray {

	private static final int INIT_SIZE = 100;

	private long[] list = new long[INIT_SIZE];
	private int size;

	public LongDynArray() {
	}

	long[] getList() {
		return list;
	}

	int getSize() {
		return size;
	}

	void insert(long value) {
		if (size >= list.length) {
			long[] newList = new long[size * 2];
			System.arraycopy(list, 0, newList, 0, size);
			list = newList;
		}
		list[size++] = value;
	}

	void clear() {
		size = 0;
	}

}
