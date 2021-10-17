package ext;

import java.util.ArrayList;
import java.util.Scanner;

public class b17472_다리만들기2_1017 {
	static boolean[][] visited;
	static int[] parents;
	static int[][] map;
	static int N,M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static class Edge{
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = in.nextInt();
			}
		}
		int areaNum = 0;
		ArrayList<int[]> startPos = new ArrayList<int[]>();
		visited = new boolean[N][M];
		
		
		
		in.close();
	}
}
