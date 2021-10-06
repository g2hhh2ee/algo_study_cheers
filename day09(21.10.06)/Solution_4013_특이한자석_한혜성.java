package SWEA;

import java.io.*;
import java.util.*;

public class Solution_4013_특이한자석_한혜성 {

	static int T;
	static int K;
	static int[][] map;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer token = null;
			StringBuilder sb = new StringBuilder();
			K = Integer.parseInt(br.readLine());

			map = new int[4][8];

			for (int i = 0; i < 4; i++) {
				token = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			} // map완료

			for (int i = 0; i < K; i++) {
				token = new StringTokenizer(br.readLine(), " ");
				int num = Integer.parseInt(token.nextToken()) - 1;
				int turn = Integer.parseInt(token.nextToken());
				visited = new boolean[5];
				Arrays.fill(visited, false);
				go(num, turn);

			}
			/*for (int k = 0; k < 4; k++) {
				for (int j = 0; j < 8; j++) {
					System.out.print(map[k][j] + " ");
				}
				System.out.println();
			}
*/
			for (int i = 3; i >= 0; i--) {
				sb.append(map[i][0]);
			}
			String str = sb.toString();
			System.out.println("#" + t + " " + Integer.parseInt(str, 2));

		}

	}

	private static void go(int num, int turn) {
		visited[num] = true;
		int nturn = 0;
		if (turn == 1) { // 시계 방향
			// 회전 1 7->0, 0->1, 1->2, 2->3....으로 스왑
			int temp = map[num][7];
			for (int i = 7; i > 0; i--) {
				map[num][i] = map[num][i - 1];
			}
			map[num][0] = temp;
			nturn = -1;
		} else if (turn == -1) { // 반시계 방향
			/*
			 * 회전 -1 1->0, 2->1, 3->2, ...0->7 로 스왑
			 */
			int temp = map[num][0];
			for (int i = 0; i < 7; i++) {
				map[num][i] = map[num][i + 1];
			}
			map[num][7] = temp;
			nturn = 1;
		}
		magnetCheck(num, nturn);

	}

	private static void magnetCheck(int num, int turn) {
		/*
		 * 0,2 -> 1,6 1,2 -> 2,6 2,2 -> 3,6
		 */
		if (num - 1 >= 0 && !visited[num-1]) {
			if (map[num - 1][2] != map[num][6]) {

				if (turn == 1) {
					go(num - 1, -1);
				} else
					go(num - 1, 1);

			}

		}
		
		if (num + 1 <= 3 && !visited[num+1]) {
			if (map[num][2] != map[num + 1][6]) {

				if (turn == 1)
					go(num + 1, -1);
				else
					go(num + 1, 1);

			}
			
		}

		
	}

}
