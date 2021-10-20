import java.util.Scanner;

public class b17070_파이프옮기기1_1020 {
	static int N, ans;
	static int[][] map;
	static int[] dr = {0, 1, 1};//우 하 대
	static int[] dc = {1, 0, 1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		map = new int[N+1][N+1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = in.nextInt();
			}
		}
		
		ans = 0;
		dfs(1,1,0);
	}
	private static void dfs(int r, int c, int d) {
		int nr = r + dr[d];
		int nc = c + dc[d];
		if(r==N && c == N) {
			
			//return;
		}
	}
}
