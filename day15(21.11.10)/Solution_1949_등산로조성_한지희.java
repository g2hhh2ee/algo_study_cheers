
import java.util.Scanner;

public class d1110_s1949_등산로조성 {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, K, maxcnt, top;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = in.nextInt();
			K = in.nextInt();	// 최대 공사 가능
			map = new int[N][N];
			visited = new boolean[N][N];
			top = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = in.nextInt();
					top = Math.max(top, map[r][c]);//최대 높이 top
				}
			}
			maxcnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(map[r][c] == top) {// 꼭대기면~
						visited[r][c] = true;
						dfs(r, c, top, 1, 0);
						visited[r][c] = false;
					}
				}
			}
			
			
			System.out.println("#" + tc + " " + maxcnt);
		}
		in.close();
	}
	private static void dfs(int r, int c, int height, int length, int cnt) {
		//visited[r][c] = true;
		maxcnt = Math.max(maxcnt, length);
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if( nr >= 0 && nc >= 0 && nr <N && nc <N && !visited[nr][nc]) {//범위 안이고 방문 전이면~
				if(map[nr][nc]<height) {//다음 좌표가 더 낮을 때	//map[r][c]로 했다가 고생함 ㅂㄷㅂㄷ
					visited[nr][nc] = true;
					dfs(nr,nc, map[nr][nc], length+1, cnt);
					visited[nr][nc] = false;
				}else {//지금 좌표 <= 다음좌표
					if(cnt == 0) {//깎은 적 없음
						if(height > map[nr][nc]-K) {//깎았는데 지금보다 작아진다?
							visited[nr][nc] = true;
							dfs(nr,nc, height-1, length+1, cnt + 1);
							visited[nr][nc] = false;
						}
					}
				}
			}
		}
		
	}
}


