package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2667_단지번호붙이기_이호진{

	static int N;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };
	static int[] count;
	static int cnt;
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		N = scann.nextInt();
		map = new int[N][N];
		count = new int[N * N];

		for (int i = 0; i < N; i++) {
			char[] c = scann.next().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = c[j] - '0';
			}
		}
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					cnt++; //다음 단지
					count[cnt] = 1; //처음 들어왔으니까 1
					dfs(i, j);
				}
			}
		}
		
		Arrays.sort(count);
		System.out.println(cnt);
		for (int i = 0; i < count.length; i++) {
			if(count[i]!=0) {
				System.out.println(count[i]);
			}
		}
	}

	public static void dfs(int r, int c) {
		map[r][c] = 0;

		// 4방
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!check(nr, nc)) {
				continue;
			}
			if (map[nr][nc] == 1) {
				count[cnt]++;
				dfs(nr, nc);
			}
		}

	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}
