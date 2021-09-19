package SWEA;

// 18,620 kb  103 ms

import java.io.*;
import java.util.*;

public class Solution_4530_극한의청소작업_한혜성 {

	static int T;
	static String A;
	static String B;
	static long floorA;
	static long floorB;
	static long result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");

			A = token.nextToken();
			B = token.nextToken();
			long checkA = Long.parseLong(A); // 음수인지 확인하기 위함
			long checkB = Long.parseLong(B);

			// 9진수로 바꿔서 40번째 싹다 버리기 -> 1의 자리가 3이하일때 냅두고 5이상일때 1씩 더 빼주기
			floorA = go_nine(A);
			floorB = go_nine(B);

			if (checkA < 0 && checkB > 0) {// A와 B의 부호 다르면 각 0과의 거리를 더하고 1빼줘(0층은 없으니까)
				result = floorA + floorB - 1;
			} else {// 둘다 양수거나 둘다 음수일 때 그냥 빼고 절대값 씌워줘
				result = Math.abs(floorB - floorA);
			}
			System.out.println("#" + t + " " + result);
		}

	}

	public static long go_nine(String N) {
		long x = 0; // 9진수 변환할 때 제곱 수
		long ans = 0; // 9진수 변환해서 나올 값

		int last = 0;// 음수면 포문 거꾸로 돌면서 charAt(1)까지만 돌거임

		if (N.charAt(0) == '-') {// N이 음수이면 그 다음자리부터 숫자 처리
			last = 1;
		}
		for (int i = N.length() - 1; i >= last; i--) {
			int num = N.charAt(i) - '0';

			if (num >= 5) {
				num = num - 1;
			}
			//num + 9^x
			ans = ans + num * (long) Math.pow(9, x);
			x++; //자릿수 하나 올라갔으니까 하나 올려줘
		}

		return ans;
	}
}
