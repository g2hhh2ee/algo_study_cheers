package BJ;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main_2667_단지번호_한지희 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] a;
	static int N;
	static int count = 1;
	static boolean visit[][];
	public static void dfs(int r, int c) {
		a[r][c] = count;// 방문한 집 표시
		visit[r][c] = true;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if (a[nr][nc] == 1 && !visit[nr][nc]) { // 집이 있으면서 방문하지 않은 곳
					dfs(nr, nc); // 연결 되어있는 단지 탐색
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		a = new int[N][N];
		// int N = 7;
		/*int[][] a ={{0,1,1,0,1,0,0},
		{0,1,1,0,1,0,1},
		{1,1,1,0,1,0,1},
		{0,0,0,0,1,1,1},
		{0,1,0,0,0,0,0},
		{0,1,1,1,1,1,0},
		{0,1,1,1,0,0,0}};*/

		for (int r = 0; r < N; r++) {
			String s = in.next();
			for (int c = 0; c < N; c++) {
				a[r][c] = s.charAt(c) - '0';
				// System.out.println(Arrays.toString(a));
			}
			// for (int[] b : a) System.out.println(Arrays.toString(a));
			// System.out.println();
		}

		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] == 1 && !visit[i][j]) {// 집이 있으면서 방문하지 않은 곳
					dfs(i, j);// 연결 되어있는 단지 탐색
					count++;// 다음 단지 수로 이동
				}
			}
		}
		System.out.println(count - 1);
		int result[] = new int[count];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] != 0)
					result[a[i][j]]++;
			}
		}
		Arrays.sort(result);
		for (int i = 1; i < result.length; i++) {
			System.out.println(result[i]);
		}
		in.close();

	}

}
