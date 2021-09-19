package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution_D4_4530_극한의청소작업 {
	static int T;
	static long A;
	static long B;
	static long res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int t = 1; t <= T; t++) {
			//입력
			String[] str = br.readLine().split(" ");
			char[] a = str[0].toCharArray();
			char[] b = str[1].toCharArray();
			
			//로직
			A = change(a);
			B = change(b);
			res = B-A;
			if(A<0 && B>0) {
				res--;
			}
			//출력
			System.out.println("#" + t + " " + res);
		}

	}
	private static long change(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == '-') {
				continue;
			}
			if(arr[i] > '4') {
				arr[i]--;
			}
		}
		return Long.parseLong(String.valueOf(arr),9);
	}

}
