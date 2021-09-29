package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1265_달란트2_한혜성 {

	static int T;
	static int N, P;
	static long result; // 곱하기의 맥스값 구하기
	static int dsum;
	static int[] val;
	//static int mount;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			P = Integer.parseInt(str[1]);
			val = new int[P];
			result = Long.MIN_VALUE;
			long ans = 1;
			
			// 0 < mount < P - i
			// dsum = dsum + mount
			// if(i == P-1) => mount = N - dsum;
			// P개 다 고르면 값 곱하기 -> 맥스 설정
			// result => for문으로 val 돌려서 값 곱해 ==> max구하기
			//20-dsum-(P-1-i) 5 12 1 1 1

			for (int mount = 1; mount <= N - P +1; mount++) {
				for (int i = 0; i < P; i++) {
					if (mount > N-P+i+1) {
						continue;
					}
					if (i != P - 1 && dsum > N - 1) {
						continue;
					}
					if (i >= 0 && i < P - 1) {
						val[i] = mount;
						dsum += mount;
					}
					if (i == P - 1) {
						val[i] = N - dsum;

						for (int j = 0; j < P; j++) {
							ans = (long)(val[j] * ans);
						}
						result = Math.max(result, ans);
						ans = 1;
						dsum = 0;
					}

				}
			}
			//System.out.println(Arrays.toString(val));
			System.out.println("#" + t + " " + result);
		}

	}

}
