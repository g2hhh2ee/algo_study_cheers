package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1265_달란트2_한혜성 {

	static int T;
	static int N, P;
	static long result; // 곱하기의 맥스값 구하기
	static int[] val;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			P = Integer.parseInt(str[1]);
			val = new int[P];
			result = 1;

			//각 그룹끼리의 차이가 적으면 곱했을 때 큰 수 나옴
			int ans = N / P;
			int dif = N % P;
			
			for (int i = 0; i < P; i++) {
				val[i] = ans;
				if(dif != 0) {
					for (int j = 0; j < dif; j++) {
						val[j] = 1 + ans;
					}
				}
			}
			
			for (int i = 0; i < P; i++) {
				result = result * (long)val[i];
			}
			
			System.out.println("#" + t + " " + result);
		}

	}

}
