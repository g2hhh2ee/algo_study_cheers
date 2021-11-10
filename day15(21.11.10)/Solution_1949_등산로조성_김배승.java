import java.util.Scanner;

public class Solution_1949_등산로조성_김배승 {

	static int T, N, K;
	static int[][] map;
	static boolean[][] visit;
	static int max;
	static int[] dr= {1,0,-1,0}; //남동북서
	static int[] dc= {0,1,0,-1};
	static int m;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		T=scann.nextInt();
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			K=scann.nextInt();
			map=new int[N][N];
			visit=new boolean[N][N];
			max=0;
			m=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j]=scann.nextInt();
					m=Math.max(m, map[i][j]);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==m) {
						visit[i][j]=true;
						go(i, j, 0, 1);
						visit[i][j]=false;
					}
				}
			}
			System.out.println("#"+t+" "+max);
		}
	}
	
	private static void go(int r, int c, int cut, int cnt) {
		max=Math.max(max, cnt);
		for (int d = 0; d < 4; d++) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(!check(nr,nc)) {
				continue;
			}
			if(visit[nr][nc]) {
				continue;
			}
			if(map[nr][nc]>=map[r][c]) {
				if(cut==0) {
					for (int i = 1; i <= K; i++) {
						if(map[nr][nc]-i<map[r][c]) {
							int temp=map[nr][nc];
							int down=map[nr][nc]-i;
							visit[nr][nc]=true;
							map[nr][nc]=down;
							go(nr,nc,cut+1,cnt+1);
							map[nr][nc]=temp;
							visit[nr][nc]=false;
						}
					}
				}
			}else {
				visit[nr][nc]=true;
				go(nr,nc,cut,cnt+1);
				visit[nr][nc]=false;
			}
		}
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
