public class PercolationStats {
	double ratios[];
	int T;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("N<=0 or T<=0");
		}
		this.T = T;
		int totalSites = N * N;
		int openedSites;
		ratios = new double[T];
		for (int i = 0; i < T; i++) {
			Percolation p1 = new Percolation(N);
			openedSites = 0;
			while (!p1.percolates()) {
				int a = StdRandom.uniform(1, N + 1);
				int b = StdRandom.uniform(1, N + 1);
				if (!p1.isOpen(a, b)) {
					p1.open(a, b);
					openedSites++;
				}
			}
			ratios[i] = (double) openedSites / totalSites;
		}
		// perform T independent experiments on an N-by-N grid
	}

	public double mean() {
		return StdStats.mean(ratios);
		// sample mean of percolation threshold
	}

	public double stddev() {
		return StdStats.stddev(ratios);
		// sample standard deviation of percolation threshold
	}

	public double confidenceLo() {
		return mean() - (stddev() * 1.96 / Math.sqrt(T));
		// low endpoint of 95% confidence interval
	}

	public double confidenceHi() {
		return mean() + (stddev() * 1.96 / Math.sqrt(T));
		// high endpoint of 95% confidence interval
	}

	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		int N = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(N, T);
		StdOut.println("Mean=" + ps.mean());
		StdOut.println("stddev=" + ps.stddev());
		StdOut.println("95% confidence interval=" + ps.confidenceLo() + ", "
				+ ps.confidenceHi());
	}
}