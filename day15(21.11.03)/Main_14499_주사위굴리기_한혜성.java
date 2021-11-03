package BJ;

import java.io.*;
import java.util.*;
public class Main_14499_주사위굴리기_한혜성 {

	static int N,M; //지도 세로 가로 
	static int x,y; //주사위 위치
	static int K; //명령 횟수
	static int [][] map;
	static int [] dice;//각 명령에 따른 주사위 상태 저장할 예정
	static ArrayList<Integer> result = new ArrayList<>();
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = null;
		token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken()); //지도 세로
		M = Integer.parseInt(token.nextToken()); //지도 가로
		x = Integer.parseInt(token.nextToken()); //주사위 세로좌표
		y = Integer.parseInt(token.nextToken()); //주사위 가로 좌표
		K = Integer.parseInt(token.nextToken()); //명령횟수
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	/*	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}*/
		
		//K입력받을때마다 움직여서 값 내기 -> 결과 그 뭐시기 그 주사위 전개도 저장하기
		//  1동    2서    3북    4남
		dice = new int[6]; //앞  위  뒤  밑  서  동
		Arrays.fill(dice, 0); //일단 초기화
		
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			go(Integer.parseInt(token.nextToken()));
			
		}
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));			
		}
		
		
		
		
	}

	private static void go(int move) {
		//일단 밑면 좌표를 기준으로 움직였을 때 맵의 범위를 벗어나면 걍 나가
		//그게 아니면 일 처리하고 다시 x와 y에 값 저장
		int r = x;
		int c = y;
		if(move == 1) { // 동 서 북 남
			c+=1;
			if(!check(r,c)) return;
			
			x = r;
			y = c;
			int [] change = {0,4,2,5,3,1};
			roll(change,x,y);
			
		}else if(move == 2) {
			c-=1;
			if(!check(r,c)) return;
			x = r;
			y = c;
			int [] change = {0,5,2,4,1,3};
			roll(change,x,y);
		}else if(move == 3) {
			r-=1;
			if(!check(r,c)) return;
			x = r;
			y = c;
			int [] change = {1,2,3,0,4,5};
			roll(change,x,y);
		}else if(move ==4) {
			r+=1;
			if(!check(r,c)) return;
			x = r;
			y = c;
			int [] change = {3,0,1,2,4,5};
			roll(change,x,y);
		}
			
	}

	private static void roll(int[] change, int x, int y) {
		int[] dice2 = new int[6];
		for (int i = 0; i < 6; i++) {
			dice2[i] = dice[i];
		}
		for (int i = 0; i < change.length; i++) {
			dice[i] = dice2[change[i]];
		}//일단 주사위 돌려서 위치 바꿈
		
		//이동한 곳이 0이면 이동칸은 주사위 [3] 값으로 변함
		//이동한 곳이 0이 아니면 주사위[3]의 값이 이동한 곳으로 값 변함 + 이동한 곳은 0이 됨.
		if(map[x][y] == 0) {
			map[x][y] = dice[3];
		}else {
			dice[3] = map[x][y];
			map[x][y] = 0;
		}
		result.add(dice[1]);
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && c >= 0 && r < N && c < M;
	}

}

