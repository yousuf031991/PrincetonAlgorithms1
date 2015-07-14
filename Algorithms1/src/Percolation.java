public class Percolation {
	private static final int OPEN = 1;
	int grid[][];
	int size;
	WeightedQuickUnionUF uf;

	public Percolation(int N) {
		if (N <= 0) {
			throw new IllegalArgumentException(
					"Illegal argument exception -N<0 =" + N);
		}
		size= N;
		grid = new int[N][N];
		uf = new WeightedQuickUnionUF((N * N) + 2);

	}

	public void open(int i, int j) {
		i--;
		j--;
		validate(i, j);
		grid[i][j] = OPEN;
		if (j > 0 && grid[i][j - 1] == OPEN)
			uf.union(xyTo1D(i, j), xyTo1D(i, j - 1));
		if (j < size - 1 && grid[i][j + 1] == OPEN)
			uf.union(xyTo1D(i, j), xyTo1D(i, j + 1));
		if (i > 0 && grid[i - 1][j] == OPEN)
			uf.union(xyTo1D(i, j), xyTo1D(i - 1, j));
		if (i < size - 1 && grid[i + 1][j] == OPEN)
			uf.union(xyTo1D(i, j), xyTo1D(i + 1, j));
		if (i == 0) {
			uf.union(xyTo1D(i, j), 0);
		} else if (i == size - 1) {
			uf.union(xyTo1D(i, j), (size * size) + 1);
		}
	}

	private void validate(int i, int j) {
		if (i < 0 || i > size - 1) {
			throw new IndexOutOfBoundsException("1st index " + i
					+ " is not between 0 and " + size);
		} else if (j < 0 || j > size - 1) {
			throw new IndexOutOfBoundsException("2nd index " + j
					+ " is not between 0 and " + size);
		}
	}

	public boolean isOpen(int i, int j) {
		i--;
		j--;
		validate(i, j);
		if (grid[i][j] == OPEN) {
			return true;
		} else {
			return false;
		}
	}

	public int xyTo1D(int x, int y) {
		validate(x, y);
		return x * size + y + 1;
	}

	public boolean isFull(int i, int j) {
		i--;
		j--;
		validate(i, j);
		if (grid[i][j] == OPEN && uf.connected(xyTo1D(i, j), 0)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean percolates() {
		return uf.connected(0, (size * size) + 1);
	}

	public static void main(String[] args) {

	}
}