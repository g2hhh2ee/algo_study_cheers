package BJ;

import java.io.*;
import java.util.*;
// 2인 곳에 바이러스 놓을 수 있음 조합뽑아서 -> bfs 돌리기 ->  time 제일 낮은걸 출력, 퍼뜨릴 수 없으면 -1 출력

public class Main_17142_연구소3_한혜성 {

	static int N;
	static int M; // comb
	static int[][] map;
	static int[][] copy;
	static ArrayList<Choice> ch;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Choice[] indexch;
	static int zerocount;
	static int minmin;
	static boolean findzero;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		ch = new ArrayList<>();
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][N];
		zerocount = 0;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == 2) {
					ch.add(new Choice(i, j, 0));
				}
				if(map[i][j] == 0) {
					zerocount++;
				}
			}
		} //map 구성 완료 + 바이러스 위치 어레이리스트에 넣기

		/*
		 * for (int i = 0; i < N; i++) { 
		 * 		for (int j = 0; j < N; j++) {
		 * 			System.out.print(map[i][j] + " "); 
		 * 		}System.out.println(); 
		 * }
		 */

		/*
		 * for (int i = 0; i < ch.size(); i++) {
		 * 		System.out.println(ch.get(i).toString()); 
		 * }
		 */
	
		// 리스트에 있는 애들 M개씩 골라, 그 다음 bfs 돌려
		minmin = Integer.MAX_VALUE;
		indexch = new Choice[M];
		combi(0,0, new boolean[ch.size()]);

		/*for (int i = 0; i < N; i++) {
			  for (int j = 0; j < N; j++) {
				  if(copy[i][j] == 0) {
					  findzero = true;
				  }
			  }
		  }
		  if(findzero) {
			  minmin = -1;
		  }*/
		  if(minmin == Integer.MAX_VALUE) {
			  minmin = 0;
		  }
		  System.out.println(minmin);
		  
		
	}

	
	

	public static void combi(int start, int cnt, boolean[] v) {
		//일단 M개 고르고 걔네들을 배열에 저장하고 그 아이들을 bfs가서 인덱스로 넣어주기
		if(cnt == M) {
			go_bfs();
			return;
		}
		for (int i = start; i < ch.size(); i++) {
			if(v[i]) continue;
			
			v[i] = true;
			indexch[cnt] = ch.get(i);
			combi(i+1, cnt+1, v);
			v[i] = false;
		}
	}




	public static void go_bfs() {

		Queue<Choice> que = new LinkedList<Choice>();
		boolean[][] chch = new boolean[N][N];
		copy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 2) {
					copy[i][j] = -2;
				}
				if(map[i][j] == 1) {
					copy[i][j] = -1;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {			
			chch[indexch[i].x][indexch[i].y] = true;
			copy[indexch[i].x][indexch[i].y] = 1;
			Choice c = indexch[i];
			que.add(c);
		
		}
		while(!que.isEmpty()) {
			
			Choice c = que.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = c.x + dx[d];
				int ny = c.y + dy[d];
				
				if(!check(nx,ny)) continue;
				if(map[nx][ny] != 0 || chch[nx][ny]) continue;
				
				//연구소 안에 있고, 빈칸인 애들인 경우만 남음
				//0인 애들 만나면 카운트 빼주고, 카운트가 0이면 더이상 안만난 칸 없는 거니까 결과값 나오게 하기
				//0인 애들 만나면 또 다시 큐에 넣어주기
				if(map[nx][ny] == 0) {
					zerocount--;
				}
				if(zerocount == 0) {
					minmin = Math.min(minmin, c.tt+1);
					return;
				}
				copy[nx][ny] = copy[c.x][c.y]+1;
				chch[nx][ny] = true;
				que.add(new Choice(nx,ny, c.tt +1));
				

			
			
			}
			
		}
		
	}




	public static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}

class Choice {
	int x;
	int y;
	int tt;
	public Choice(int x, int y, int tt) {
		super();
		this.x = x;
		this.y = y;
		this.tt = tt;
	}

}
