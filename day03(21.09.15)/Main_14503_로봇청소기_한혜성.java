package BJ;

import java.io.*;
import java.util.*;

public class Main_14503_로봇청소기_한혜성 {

	static int N,M;//세로, 가로
	static int[][] map;
	static int r,c,d;//북 동 남 서 0~3
	static int[] dx = {-1,0,1, 0};
	static int[] dy = { 0,1,0,-1};
	static int count;
	static boolean room;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		
		str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		d = Integer.parseInt(str[2]);
		
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}//여기까지 이제 입력 다 받음
		count = 0;
		go(r,c,d);
		
		System.out.println(count);
		
		
	}

	public static void go(int x, int y, int dir) {
		room = false; //갈 방이 있는지 없는지 확인 -> 청소할 방 있으면 true이고 없으면 false -> 청소 끝
		int dd = dir;//나중에 후진할 때 필요
		
		//일단 들어왔을 때 청소 안되어있으면 청소하고 카운트올려
		if(map[x][y]==0) {			
			map[x][y] = 2; //청소표시
			count++;
		}
	
		
		
		for (int i = 0; i < 4; i++) {
			int nd = (dir+3)%4;
			int nx = x + dx[nd];
			int ny = y + dy[nd];
			
			if(check(nx,ny) && map[nx][ny] == 0) { //범위 안이고 청소 안되어있으면 청소해
				go(nx,ny,nd);
				room = true;
				break;
			}
			//다시 방향 또 왼쪽으로 돌려
			dir = (dir+3)%4;
		}
		
		if(!room){ // 청소할 방 없으면 뒤로 후진 -> 갈 곳 없으면 끝
			int fd = (dd+2)%4;
			int fx = x + dx[fd];
			int fy = y + dy[fd];
			
			if(check(fx,fy)) {
				if(map[fx][fy] != 1) { //뒤로 갔는데 벽이 아니면 다시 청소할 수 있는지 가봐
					go(fx,fy,dd);	
				}				
			}
		}
		
	}

	public static boolean check(int x, int y) {
		return x>=0 && y >=0 && x < N && y < M;
	}

	
	
}
