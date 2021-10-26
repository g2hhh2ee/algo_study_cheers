package ext;

import java.util.Arrays;
import java.util.Scanner;

public class b17136_색종이붙이기 {
	static int[][] map;
	static int N = 10;
	static int min = Integer.MAX_VALUE;
	static int paper[] = {0, 5, 5, 5, 5, 5};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		map = new int[12][12];
//		Arrays.fill(map, -1);
		for (int r = 0; r < 12; r++) {
			for (int c = 0; c < 12; c++) {
				map[r][c] = -1;
			}
		}
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = in.nextInt();
			}
		}
		
		dfs(0, 0, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(min);
		}
	}
	private static void dfs(int r, int c, int cnt) {
		if(map[r][c]==1) {
			for (int i = 5; i >= 1; i--) {
				if(paper[i]>0 && checked(r, c, i)) {
					
				}
			}
		}
		
	}
	private static boolean checked(int r, int c, int psize) {
		for (int i = 0; i < r + psize; i++) {
			for (int j = 0; j < j + psize; j++) {
				if(map[i][j]==1 || map[i][j] == 0) {
//					map[i][j] = 
				}else {
					return false;
				}
			}
		}
		return false;
	}
}


/*
for (int r = 0; r < 12; r++) {
for (int c = 0; c < 12; c++) {
	System.out.print(map[r][c]);
}
System.out.println();
}
*/
