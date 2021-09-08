package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_7576_토마토_한지희 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };
		int N = in.nextInt();
		int M = in.nextInt();
		int[][] tomato = new int[M][N];
		int cnt = 0, days = 0;
		Queue<int[]> q = new LinkedList<int[]>();

		for (int r = 0; r < M; r++)
			for (int c = 0; c < N; c++) {
				tomato[r][c] = in.nextInt();
				if (tomato[r][c] == 1)
					q.add(new int[] { r, c });
				else if (tomato[r][c] == 0)
					cnt++;
			}

		while (cnt > 0 && !q.isEmpty()) {
			for (int s = q.size(); s > 0; s--) {
				int[] cur = q.poll();
				for (int k = 0; k < 4; k++) {
					int ny = cur[0] + dr[k];
					int nx = cur[1] + dc[k];
					if (ny < 0 || nx < 0 || ny >= M || nx >= N || tomato[ny][nx] != 0)
						continue;
					cnt--;
					tomato[ny][nx] = 1;
					q.add(new int[] { ny, nx });
				}
			}
			days++;
		}
		System.out.println(cnt == 0 ? days : -1);
		in.close();
	}
}
