package swea;

import java.util.Scanner;

public class Solution_모의역량_2112_보호필름 {
	static int t, d, w, k;
	static int[][] map;
	static int[] v;
	static int ans;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			d = sc.nextInt();
			w = sc.nextInt();
			k = sc.nextInt();
			map = new int[d][w];
			v = new int[d + 1];
			ans = d;
			for (int i = 0; i < d; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			//일단 입력 받고 체크
			//2개 넣으니까 조합2개 해서 체크
			//
			combi(0, 0);
			
			System.out.println("#"+tc+" "+ans);
		}
	}

	private static void combi(int idx, int cnt) {
		if (cnt > ans)
			return;
		if (idx == d) {
			if (check(map)) {
				if (ans > cnt)
					ans = cnt;
			}

			return;
		}

		v[idx] = 1;// a
		combi(idx + 1, cnt + 1);
		v[idx] = 2;// b
		combi(idx + 1, cnt + 1);
		v[idx] = 0;
		combi(idx + 1, cnt);

	}

	private static boolean check(int[][] map) {
		for (int j = 0; j < w; j++) {
			boolean flag = false;
			int cnt = 1;
			for (int i = 1; i < d; i++) {
				int a = map[i - 1][j];
				int b = map[i][j];
				if (v[i - 1] != 0) {
					a = v[i - 1] == 1 ? 0 : 1;
				}
				if (v[i] != 0) {
					b = v[i] == 1 ? 0 : 1;
				}
				if (a == b) {
					cnt += 1;
				} else {
					cnt = 1;
				}
				if (cnt == k) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return false;
		}

		return true;
	}
}

