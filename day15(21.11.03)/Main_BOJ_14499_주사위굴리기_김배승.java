import java.util.Scanner;

public class Main_BOJ_14499_주사위굴리기 {
	
	static int N, M, T;
	static int sr,sc;
	static int[][] map;
	static int[][] dice;
	static int[] dr= {0,0,0,-1,1};
	static int[] dc= {0,1,-1,0,0};
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		sr=scann.nextInt();
		sc=scann.nextInt();
		T=scann.nextInt();
		map=new int[N][M];
		dice=new int[4][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
			}
		}
		for (int i = 0; i < T; i++) {
			int d=scann.nextInt();
			int nr=sr+dr[d];
			int nc=sc+dc[d];
			if(!check(nr,nc)) continue;
			if(d==1) {
				dong();
			}else if(d==2){
				seo();
			}else if(d==3) {
				book();
			}else {
				nam();
			}
			if(map[nr][nc]==0) map[nr][nc]=dice[3][1];
			else {
				dice[3][1]=map[nr][nc];
				map[nr][nc]=0;
			}
			System.out.println(dice[1][1]);
			sr=nr; 
			sc=nc;
		}
	}
	private static void nam() {
		int temp=dice[3][1];
		dice[3][1]=dice[2][1];
		dice[2][1]=dice[1][1];
		dice[1][1]=dice[0][1];
		dice[0][1]=temp;
	}
	private static void book() {
		int temp=dice[0][1];
		dice[0][1]=dice[1][1];
		dice[1][1]=dice[2][1];
		dice[2][1]=dice[3][1];
		dice[3][1]=temp;
	}
	private static void seo() {
		int temp=dice[1][2];
		dice[1][2]=dice[3][1];
		dice[3][1]=dice[1][0];
		dice[1][0]=dice[1][1];
		dice[1][1]=temp;
	}
	private static void dong() {
		int temp=dice[1][0];
		dice[1][0]=dice[3][1];
		dice[3][1]=dice[1][2];
		dice[1][2]=dice[1][1];
		dice[1][1]=temp;
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}

}
