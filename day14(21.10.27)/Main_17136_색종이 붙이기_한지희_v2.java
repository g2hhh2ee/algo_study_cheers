package ext;

import java.util.Scanner;

public class b17136_색종이붙이기_1027 {
	static int[][] map;
	static int N = 10;
	static int min = Integer.MAX_VALUE;
	static int paper[] = {5, 5, 5, 5, 5};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
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
	
	private static void dfs(int i, int j, int k) {
		
		
	}

	public static boolean check(int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if( !inRange(i,j) || map[r][c]==0) {//범위 밖에 있거나 1이 아니면
					return false;
				}
			}
		}
		return true;
	}

	private static boolean inRange(int i, int j) {
		if(i>=0 && j>=0 && i<N && j<N) {
			return true;
		}
		return false;
	}
}
