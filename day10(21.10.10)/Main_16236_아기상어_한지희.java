import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b16236_아기상어_211010 {
	static int N;
	static int[][] map = new int[20][20];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, 1, -1 };
	static final int MAX = Integer.MAX_VALUE;
	static class Point {
		int r, c, d;
		public Point(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		int srow = 0, scol = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
				if (map[i][j] == 9) {
					srow = i;
					scol = j;
					map[i][j] = 0;
				}
			}
		}
		in.close();
		System.out.println(solve(srow, scol));
	}

	static int solve(int r, int c) {
		int time = 0;
		int size = 2;
		int cnt = 2;
		Point point = new Point(r, c, 0);
		do {
			boolean visited[][] = new boolean[20][20];
			Queue<Point> q = new LinkedList<Point>();
			visited[point.r][point.c] = true;
			q.add(new Point(point.r, point.c, 0));
			point.d = MAX;
			while (q.peek() != null) {
				Point curr = q.poll();
				if (curr.d > point.d)
					break;

				if (map[curr.r][curr.c] > size) {
					continue;
				}
				if (map[curr.r][curr.c] != 0 && map[curr.r][curr.c] < size) {
					if (curr.d < point.d) {
						point = curr;
					} else if (curr.d == point.d) {
						if (curr.r < point.r)
							point = curr;
						else if (curr.r == point.r && curr.c < point.c)
							point = curr;

					}
					continue;
				}
				for (int i = 0; i < 4; i++) {
					int nr = curr.r + dr[i];
					int nc = curr.c + dc[i];
					if (nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1)
						continue;
					if (visited[nr][nc])
						continue;

					visited[nr][nc] = true;
					q.add(new Point(nr, nc, curr.d + 1));
				}
			}

			if (point.d != MAX) {
				time += point.d;
				--cnt;
				if (cnt == 0) {
					++size;
					cnt = size;
				}
				map[point.r][point.c] = 0;

			}
		} while (point.d != MAX);
		return time;
	}

}
