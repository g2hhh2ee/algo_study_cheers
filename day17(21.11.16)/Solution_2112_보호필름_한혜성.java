package SWEA;

import java.io.*;
import java.util.*;

public class Solution_2112_보호필름_한혜성 {

	static int T;
	static int D, W, K;
	static int [][] map;	
	static int result;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = null;
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(token.nextToken());
			W = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());
		
			map = new int[D][W];
			result = Integer.MAX_VALUE;
			
			for (int i = 0; i < D; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
		
			/*for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j] + " ");
				}System.out.println();
			}*/
		
			dfs(0,0); 
			
			System.out.println("#" + t + " " + result);
		
			
		}
		
		
	}

	
	private static void dfs(int idx, int inject) {
		// 주입횟수가 현재 최저투입횟수보다 크면 확인해볼 필요가 없음.
		if(inject >= result) return;
		
		// 가장 아래까지 row(D)갔다면
		if(idx >= D) {
			//검사에 통과하는지 확인 
			if(check_Quality()) {
				result = Math.min(result, inject);
			}
			return;
		}
		
		// 아무 약품도 주입하지 않고 pass
		dfs(idx+1, inject);
		
		// 원본(초기)값 저장하기 
		int[] tArr = new int[W];
		for(int i=0; i<W; i++)
			tArr[i] = map[idx][i];
		
		// 현재 idx(row,D)에 0을 주입하고 재귀.
		for(int i=0; i<W; i++)
			map[idx][i] = 0;
		dfs(idx+1, inject+1);
		
		// 현재 idx에(row,D)에 1을 주입하고 재귀.
		for(int i=0; i<W; i++)
			map[idx][i] = 1;
		dfs(idx+1, inject+1);
		
		// 원본(초기)값으로 되돌리기.
		for(int i=0; i<W; i++)
			map[idx][i] = tArr[i];
	}
	
	static boolean check_Quality() {
		for (int i = 0; i < W; i++) {
			int cnt = 1;
			int flag = 0;
			for (int j = 0; j < D - 1; j++) {
				if (map[j][i] == map[j + 1][i]) {
					cnt++;
				} else
					cnt = 1;
				// 해당 컬럼은 K를 만족. 다음 컬럼으로 이동.
				if (cnt >= K) {
					flag = 1;
					break;
				}
			} 
			// 단 하나의 컬럼에서도 K미만이면 안됨.
			if (flag == 0)
				return false;
		} 
		return true;
	}
}


