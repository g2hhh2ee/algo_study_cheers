package BJ;
/*
 * 메모리 141880
 * 시간 812
 */
 
import java.io.*;
import java.util.*;

public class Main_7576_토마토_한혜성 {

	static int M, N;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		} // map구성 완료

		go_bfs();
		
		int max = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					result = true;
					
				}else {
					max = Math.max(max, map[i][j]);					
				}
			}
		}
		if(result) {
			System.out.println(-1);
		}else {
			System.out.println(max-1);
		}
		
		/*for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}*/
		
		// 큐 다 돌고나면 0이 하나라도 맵에 있으면 -1 나오고 그게 아니라면 가장 큰 값 출력
	}

	public static void go_bfs() {
		Queue<tomato> que = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) {
					que.add(new tomato(i,j));
				}
			}
		}//시작 토마토 넣었음 이제 얘네들 큐 돌려서 다른 애들까지 갈 예정
		
		while(!que.isEmpty()) {
			tomato t = que.poll();//뽑아서 사방탐색 하고 범위 안벗어나고 0이면 값 하나 올리고 큐에 넣어줘
			for (int d = 0; d < 4; d++) {
				int nx = t.x + dx[d];
				int ny = t.y + dy[d];
				
				if(!check(nx,ny)) {
					continue;
				}
				if(map[nx][ny] != 0) {
					continue;
				}//다 나감
				
				map[nx][ny] = map[t.x][t.y] +1;
				que.add(new tomato(nx, ny));
				
			}
		}
		
		
	}

	public static boolean check(int x, int y) {
		return x>=0 && y>=0 && x < N && y < M;
	}

}
class tomato {
	int x;
	int y;
	
	public tomato(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
