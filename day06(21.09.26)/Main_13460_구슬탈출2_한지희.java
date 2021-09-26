import java.util.Scanner;

public class Main_13460_구슬탈출2_한지희 {

	static int N, M;
	static char[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[] red = new int[2];
	static int[] blue = new int[2];
	static int[] goal = new int[2];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		map = new char[N][M];
		for (int i = 0; i < N; i++)
			map[i] = new char[M];
		for (int r = 0; r < N; r++) {
			char[] temp = in.next().toCharArray();
			for (int c = 0; c < M; c++) {
				if (temp[c] == 'R') {
					red[0] = r;
					red[1] = c;
					map[r][c] = '.';
				} else if (temp[c] == 'B') {
					blue[0] = r;
					blue[1] = c;
					map[r][c] = '.';
				} else if (temp[c] == 'O') {
					goal[0] = r;
					goal[1] = c;
					map[r][c] = '.';
				} else {
					map[r][c] = temp[c];
				}
			}
		}
		in.close();
		dfs(red, blue, 0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

	}

	public static void dfs(int[] r, int[] b, int moveCnt) {
		if (moveCnt == 11)
			return;
		if (r[0] == -100 && b[0] != -100) {
			if (min > moveCnt) {
				min = moveCnt;
			}
			return;
		}
		if (b[0] == -100)
			return;

		for (int d = 0; d < 4; d++) {
			int[] curRed = { r[0], r[1] };
			int[] curBlue = { b[0], b[1] };
			move(curRed, curBlue, d);
			dfs(curRed, curBlue, moveCnt + 1);
		}
	}

	public static void move(int[] r, int[] b, int d) {
		int[] curRed = new int[] { r[0], r[1] };
		int[] curBlue = new int[] { b[0], b[1] };
		while (next(curRed, curBlue, d)) {
			if (goal[0] == curRed[0] && goal[1] == curRed[1]) {
				curRed[0] = -100;
				curRed[1] = -100;
			}
			if (goal[0] == curBlue[0] && goal[1] == curBlue[1]) {
				curBlue[0] = -100;
				curBlue[1] = -100;
			}
		}
		r[0] = curRed[0];
		r[1] = curRed[1];
		b[0] = curBlue[0];
		b[1] = curBlue[1];
	}

	public static boolean checkRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		else if (map[r][c] == '#')
			return false;
		else
			return true;
	}

	public static boolean next(int[] red, int[] blue, int dir) {
		int nr1, nr2, nc1, nc2;
		nr1 = red[0] + dr[dir];
		nr2 = blue[0] + dr[dir];
		nc1 = red[1] + dc[dir];
		nc2 = blue[1] + dc[dir];

		if (checkRange(nr1, nc1) && checkRange(nr2, nc2)) {
			red[0] = nr1;
			red[1] = nc1;
			blue[0] = nr2;
			blue[1] = nc2;
			return true;
		} else if (!checkRange(nr1, nc1) && checkRange(nr2, nc2)) {
			if (red[0] == nr2 && red[1] == nc2) {
				return false;
			} else {
				blue[0] = nr2;
				blue[1] = nc2;
				return true;
			}
		} else if (checkRange(nr1, nc1) && !checkRange(nr2, nc2)) {
			if (blue[0] == nr1 && blue[1] == nc1) {
				return false;
			} else {
				red[0] = nr1;
				red[1] = nc1;
				return true;
			}
		}
		return false;
	}
}
