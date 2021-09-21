package swea;

import java.util.Scanner;

public class Solution_D5_1265_달란트2 {
	static int talent;
	static int bind;
	static int max;
	static long res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			talent = 0;
			bind = 0;
			res = 1;
			// 입력
			talent = sc.nextInt();
			bind = sc.nextInt();
			// 로직
			int share = 0;
			int remainder = 0;

			share = talent / bind;
			remainder = talent % bind;
			
			for (int i = 0; i < remainder; i++) {
				res = res * (share+1);
			}
			for (int i = 0; i < bind-remainder; i++) {
				res = res * share;
			}
			// 출력
			System.out.println("#" + tc + " " + res);
		}

	}

}
