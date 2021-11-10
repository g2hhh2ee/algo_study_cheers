package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1949_등산로조성_한혜성 {

	static int T;
	static int N;
	static int K;
	static int [][] map;
	static boolean [][] visited;
	static int high;
	static int []dr = {-1,0,1,0};
	static int []dc = {0,1,0,-1};
	static int result;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			high = Integer.MIN_VALUE;
			result = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					high = Math.max(high, map[i][j]);
				}
			}
			
			/*for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}System.out.println();
			}*/
			
			//가장 큰 값을 찾아서 dfs로 방향 가면서 돌리기
			// - 점점 내려가는 방향으로 가야하는데 
			//   만약 막힌다면 그때 K만큼 깎고 최대로 갈 수 있는 길이 구해놓기
			//   K를 갔던 곳에 쓸 수 있으니 방문체크도 해주기
			//   K는 한번만 사용 가능하니 사용여부 저장하는 변수 하나 만들어서 표시
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == high) {
						visited[i][j] = true;
						go(i,j,high,1,0);
						visited[i][j] = false;
					}
				}
			}
			System.out.println("#" + t +" "+result);
		
		}
	
		
	}

	private static void go(int r, int c, int height, int count, int usedK) {
		
		for (int d = 0; d < 4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!check(nr,nc)) continue; //얜 범위 벗어나면 나가
			if(visited[nr][nc]) continue;//방문했으면 나가
			
			// 점점 내려가야하며 못내려가면 k써서 내려가고 k 여부는 트루로 바꿔줌
			// K도 썼고 더이상 내려가지도 않으면 걍 값 나오게 하기
			
			
			if(map[nr][nc] < height ) { 
				visited[nr][nc] = true;
				go(nr,nc, map[nr][nc], count+1, usedK);
				visited[nr][nc] = false;
			}
			
			else {//산 깎아야하면
				if(usedK==0) { 
					// 아직 안깎았으면 깎아라, 그러고 나서 원래 값보다 작아진다면 다음 고 
					if(map[nr][nc]-K < height) { //최대한 적게 깎아야함
						visited[nr][nc] = true;
						go(nr,nc,height-1, count+1, usedK+1);
						visited[nr][nc] = false;
						
					}
				}
			}
			
			result = Math.max(result, count);					

			
		}
	
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && c >= 0 && r < N && c < N;
	}

}
