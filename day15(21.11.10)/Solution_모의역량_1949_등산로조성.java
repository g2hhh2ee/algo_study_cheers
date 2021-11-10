package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_모의역량_1949_등산로조성 {
	static int T;
	static int N;
	static int K;
	static int[][] map;
	static boolean[][] v;
	static int dr[] = {1, -1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	static int max;
	static int distance;
	static int temp;
	static boolean check;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			v = new boolean[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > max) { //max값 구하기
                    	max = map[i][j];
                    }
                }
            }
			
			distance = 0; //최종 거리
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(max == map[i][j]) { //map에 max가 여러개 있을 수 있으니까
						v[i][j] = true; //방문 처리
						temp = 0;
						dfs(i,j,0);
						v[i][j] = false; //왔다가 다시 가야 할 수 있으니까
					}
				}
			}
			System.out.println("#"+tc+" "+(distance+1));
		}
	}
	private static void dfs(int r, int c, int cnt) {
		if(cnt > distance) {
			distance = cnt;
		}
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr >= 0 && nc >= 0 && nr < N && nc < N) { //범위 체크
            	if(map[nr][nc] < map[r][c] && !v[nr][nc]) {//현재 높이보다 작고 방문하지 않았으면 -> 간다
            		v[nr][nc] = true;
            		dfs(nr,nc,cnt+1);
            		v[nr][nc] = false;
            		
            	}else if(!check && map[nr][nc]-map[r][c] < K && !v[nr][nc]) {//공사 할 수 있고 방문하지 않았으면 간다
            		check = true;
            		v[nr][nc] = true;
            		temp = map[nr][nc];
                    map[nr][nc] = map[r][c]-1; //1칸 공사할 수 있다.
            		dfs(nr,nc,cnt+1);
            		map[nr][nc] = temp;
            		check = false;
            		v[nr][nc] = false;
            	}
            }
		}
	}
}
