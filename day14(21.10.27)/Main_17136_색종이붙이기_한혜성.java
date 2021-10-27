package BJ;

import java.io.*;
import java.util.*;

public class Main_17136_색종이붙이기_한혜성 {

	static int N;
	static int [][] map;
	static int ans = Integer.MAX_VALUE;
	static int papier[]; //색종이 각 5장씩
	
	public static void main(String[] args) throws Exception{

		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		
		N = 10;
		map = new int[N][N];
		papier = new int[6];
		Arrays.fill(papier, 5);
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}//입력 완료
		
		
		go(0, 0);
		
		if(ans==Integer.MAX_VALUE) {
			ans = -1;
		}
		
		System.out.println(ans);
	
	}

	static void go(int index, int cnt) {

		if (index == 100) {
			ans = Math.min(cnt, ans);
			return;
		}
        
		if(ans <= cnt)
			return;

		int r = index / 10;
		int c = index % 10;
       
		if (map[r][c] == 1) {
			//큰종이부터
			for (int i = 5; i > 0; i--) {

				if (papier[i] >0 && check(r,c,i)) {
                	//색종이 수 -1, 다음 탐색시 걸리지 않게 0으로 바꿈, 계속 탐색
					papier[i]--;
					fill(r,c,i,0);
					go(index + 1, cnt + 1);
                    //탐색 후 되돌려줌
					fill(r,c,i,1);
					papier[i] += 1;
				}
			}
		} else
			go(index+1, cnt);
//		go(index++, cnt);

	}
	static void fill(int r,int c,int size,int num) {
		
		for(int i=r;i<r+size;i++) {
			for(int j=c;j<c+size;j++) {
				map[i][j]=num;
			}
		}
		
	}
	static boolean check(int r, int c,int size) {
		//범위 체크
		if( r+size>10 || c+size>10) 
			return false;
		
		for(int i=r;i<r+size;i++) {
			for(int j=c;j<c+size;j++) {
				if(map[i][j]!=1)
					return false;
			}
		}
		return true;
	}


}
