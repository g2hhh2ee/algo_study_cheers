package boj;

import java.util.Scanner;

public class Main_BOJ_14503_로복청소기 {
	static int[] dr = { -1, 0, 1, 0 }; // 북동남서
	static int[] dc = { 0, 1, 0, -1 };
	static int N,M;
	static int r,c,d;
	static int count;
	static int[][] map;
	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		//로직
		//계속 쭉 가야겠지? 그래야겠지? dfs쓰자
		count = 1; //첨 시작은 무조건 청소
		dfs(r,c,d);
		
		//출력
		System.out.println(count);
	}
	
	public static void dfs(int r, int c, int d) {
		map[r][c] = 2; //청소했음
		for (int i = 0; i < 4; i++) {
			d = d-1;  //왼쪽으로 돈다
			if(d == -1) {
				d = 3;
			}
			int nr = r + dr[d];
			int nc = c + dc[d];
			//System.out.println(nr+" "+nc);
			if(nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if(map[nr][nc] == 0) {
					count++;
					dfs(nr,nc,d);
					return;
				}
			}
		}
		
		//후진
		int dir = (d+2)%4;
		int br = r + dr[dir];
		int bc = c + dc[dir];
		if(br >= 0 && bc >= 0 && br < N && bc < M && map[br][bc] != 1) {
			dfs(br,bc,d);
		}
	}

}
