package ext;

import java.util.Scanner;

public class b17070_파이프옮기기1_1020 {
	static int N, ans;
	static int[][] map;
	static int[] dr = { 0, 1, 1 };// 우 하 대
	static int[] dc = { 1, 0, 1 };

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = in.nextInt();
			}
		}

		ans = 0;
		dfs(1, 2, 0);
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int d) {
		if (r == N && c == N) {
			ans++;// 끝점에 도달하면 1 증가
			return;
		}
		for (int k = 0; k < 3; k++) {// 가로 세로 대각선

			if (d == 0 && k == 1) {// 가로일때는 세로로 못가니깐~
				continue;
			}
			if (d == 1 && k == 0) {// 세로일때는 가로로 못가니깐~
				continue;
			}
//			if(d==0 && k==1) {//가로일때는 세로로 못가니깐~
//				continue;
//			}

			int nr = r + dr[k];
			int nc = c + dc[k];

			if (nr > 0 && nc > 0 && nr <= N && nc <= N && map[nr][nc] != 1) {
				dfs(nr, nc, k);
//				if(map[nr][nc]!=1) {
//					dfs(nr,nc,k);
//				}
			}
		}

	}
}
