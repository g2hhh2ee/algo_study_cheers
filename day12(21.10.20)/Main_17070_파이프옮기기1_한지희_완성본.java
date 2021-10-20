import java.util.Scanner;

public class b17070_파이프옮기기1_1020 {
	static int N;
	static int ans=0;
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

		dfs(1, 2, 0);
		System.out.println(ans);
	}

	private static void dfs(int r, int c, int d) {
		if (r == N && c == N) {
			ans++;// 끝점에 도달하면 1 증가
			//return;
		}
		for (int i = 0; i < 3; i++) {// 가로 세로 대각선

			

			if (d == 0 && i == 1) {// 가로일때는 세로로 못가니깐~
				continue;
			}
			if (d == 1 && i == 0) {// 세로일때는 가로로 못가니깐~
				continue;
			}

			if (i == 2) {// 대각선일때는~ 벽 있는 경우에는~
				if (r + 1 <= N && c + 1 <= N) {
					if (map[r + 1][c] == 1 || map[r][c + 1] == 1) {
						continue;
					}
				}
			}
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr > 0 && nc > 0 && nr <= N && nc <= N && map[nr][nc] == 0) {
				dfs(nr, nc, i);
				/*if(map[nr][nc] != 1) {
					dfs(nr, nc, i);
				}*/
			}
		}

	}


}
