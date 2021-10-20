package BJ;

import java.io.*;
import java.util.*;

public class Main_17070_파이프옮기기1_한혜성 {

	static int N;
	static int[][] map;
	static int count;
	static int[] dr = { 0, 1, 1 }; // 가로 , 대각선 , 세로
	static int[] dc = { 1, 1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		/*
		 * for (int i = 0; i < N; i++) { for (int j = 0; j < N; j++) {
		 * System.out.print(map[i][j] + " "); }System.out.println(); }
		 */
		count = 0;
		if(map[N-1][N-1]==1) {
			count=0;
		}else {
		BFS();
		}
		System.out.println(count);

	}

	private static void BFS() {
		Queue<Pipe> que = new LinkedList<>();

		que.add(new Pipe(0, 1, 0));
		que.add(new Pipe(0, 1, 1));
		// 넣어줄 때 아예 방향값을 달리해서 모든 경우의 수 넣어준다.

		while (!que.isEmpty()) {

			Pipe p = que.poll();
			int nr = p.r + dr[p.dir];
			int nc = p.c + dc[p.dir];

			if (!check(nr, nc)) continue;
			if (map[nr][nc] == 1) continue;
			
			

			
			if (p.dir == 0) {// 가로받으면 가로, 대각선 가능
				que.add(new Pipe(nr, nc, 0));
				que.add(new Pipe(nr, nc, 1));

			} else if (p.dir == 1) {// 대각선받으면 가로,대각선, 세로 다 가능
				
				// 걸쳐지는 두칸 다 0이어야함
				if (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1) continue;
								
				que.add(new Pipe(nr, nc, 0));
				que.add(new Pipe(nr, nc, 1));
				que.add(new Pipe(nr, nc, 2));
				

			} else if (p.dir == 2) {// 세로 받으면 대각선, 세로 가능
				que.add(new Pipe(nr, nc, 1));
				que.add(new Pipe(nr, nc, 2));
			}
			
			if (nr == N - 1 && nc == N - 1) {
				count++;
				continue;
			}

		}

	}

	private static boolean check(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}

}

class Pipe {
	int r;
	int c;
	int dir;

	public Pipe(int r, int c, int dir) {
		super();
		this.r = r;
		this.c = c;
		this.dir = dir;
	}
}