package BJ;

import java.io.*;
import java.util.*;

public class Main_20057_마법사상어와토네이도_한혜성 {

	static int N;
	static int [][] map;
	static boolean [][] visited; //방문 여부
	static int dr[] = {0,1,0,-1}; //왼 아 오 위
	static int dc[] = {-1,0,1,0}; //왼 아 오 위
	static int result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}System.out.println();
//		}
		
		//일단 가운데부터 시작해서 값 넘겨주고 이동하고 반복
		//go에서 맵에 있는 애들 움직이고나면 staub로 가게한 후(이동 한 뒤 좌표와 방향 넘기기)
		//staub에서 모래먼지 날리고 맵 안에 있는 애들은 값 업데이트하고
		//밖으로 나간 애들은 result에 누적해주기
		//0,0까지 가면  result 출력
		
		//만약 시간초과나 뭐 그런거 나면
		//현재 자신의 값이 0이고 그 다음 값도 0이면 계산할거 없이 바로 다음으로 넘어가기
		
		result = 0;
		go(N/2, N/2, 0);
		
		
		
		
	}

	private static void go(int r, int c, int d) {
		
		visited[r][c] = true;
		if(r==0 && c==0) {
			System.out.println(result);			
			return;
		}
		
		int nr = r+dr[d];
		int nc = c+dc[d];
		
		if(!check(nr,nc)) {
			d=(d+1)%4;
			go(r,c,d);
			return;
		}//범위 벗어나면 방향 전환 
		
		else if(check(nr,nc) && visited[nr][nc]) {
			d = (d+3)%4;
			go(r,c,d);
			return;
		}//한 칸씩 이동하는데 다음 칸이 이미 방문한 곳이라면 이전 방향 그대로 
		
		if(map[r][c] == 0 && map[nr][nc] == 0) {
			d=(d+1)%4;
			go(nr,nc,d);
			return;
		}
		
		staub(r,c,nr,nc,d);
		
		d=(d+1)%4;
		go(nr,nc,d);
		
		
		
	}

	private static void staub(int xr, int xc, int r, int c, int d) {
		//먼지 날리는 좌표 중심은 y임 이제 주인공은 y
		int sand = map[r][c]; //y의 값 받고 이제 먼지 뿌려
		int alpha = map[r][c]; //y값 다 나눠주고 남은 값 주기
		
		map[r][c] = 0;
		
		if(sand >= 10) { //10퍼센트 먼지가기
			int ten = sand/10;
			int qr = r+dr[d] +dr[(d+1)%4];
			int qc = c+dc[d] +dc[(d+1)%4];
			move(qr,qc, ten);
			alpha -= ten;
			
			int wr = r+dr[d] +dr[(d+3)%4];
			int wc = c+dc[d] +dc[(d+3)%4];
			move(wr,wc, ten);
			alpha -= ten;			 			
		}
		
		if(sand >= 15) { //7퍼센트 먼지가기
			int seven = sand*7/100;
			int qr = r+dr[(d+1)%4];
			int qc = c+dc[(d+1)%4];
			move(qr,qc, seven);
			alpha -= seven;
			int wr = r+dr[(d+3)%4];
			int wc = c+dc[(d+3)%4];
			move(wr,wc, seven);
			alpha -= seven;			 			
		}
		
		if(sand >= 20) { //5퍼센트 먼지가기
			int twenty = sand*5/100;
			int qr = r+dr[d]*2;
			int qc = c+dc[d]*2;
			move(qr,qc, twenty);
			alpha -= twenty;			 			
		}
		
		if(sand >= 50) { //2퍼센트 먼지가기
			int fifty = sand*2/100;
			int qr = r+dr[(d+1)%4]*2;
			int qc = c+dc[(d+1)%4]*2;
			move(qr,qc, fifty);
			alpha -= fifty;
			
			int wr = r+dr[(d+3)%4]*2;
			int wc = c+dc[(d+3)%4]*2;
			move(wr,wc, fifty);
			alpha -= fifty;			 			
		}
		if(sand >= 100) { //1퍼센트 먼지가기
			int hund = sand/100;
			int qr = r+dr[(d+1)%4] +dr[(d+2)%4];
			int qc = c+dc[(d+1)%4] +dc[(d+2)%4];
			move(qr,qc, hund);
			alpha -= hund;
			
			int wr = r+dr[(d+2)%4] +dr[(d+3)%4];
			int wc = c+dc[(d+2)%4] +dc[(d+3)%4];
			move(wr,wc, hund);
			alpha -= hund;			 			
		}
		
		//다 나눠주고 알파값 넣어주기
		int alphar =  r+dr[d];
		int alphac =  c+dc[d];

		move(alphar, alphac, alpha);
		
	}


	private static void move(int r, int c, int mount) {
		if(check(r,c)) map[r][c] += mount;
		else result += mount;
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}

}
