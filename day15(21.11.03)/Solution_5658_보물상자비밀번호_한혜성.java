package SWEA;

import java.io.*;
import java.util.*;

public class Solution_5658_보물상자비밀번호_한혜성 {

	static int T;
	static int N, K;
	static char[] treasure;
	static ArrayList<String> pass;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			pass = new ArrayList<String>();

			StringTokenizer token = new StringTokenizer(br.readLine());

			N = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());

			treasure = new char[N];
			treasure = br.readLine().toCharArray();

//			for (int i = 0; i < N; i++) {
//				System.out.print(treasure[i] + "  ");
//			}System.out.println(); 

			// 4개씩 잘라서 arraylist에 넣기
			// 단 어레이리스트에 이미 들어있다면 넣지 않을 것임
			// N/4번 이를 반복한 뒤
			// 어레이리스트에 있는 애들을 하나씩 꺼내서 16진수로 바꿔주고
			// 소트하여 결과값 출력
			StringBuilder sb = null;
			int cnt = 0;

			while (cnt != (N / 4)) {
				for (int i = cnt; i < N; i = i + (N/4)) {
					sb = new StringBuilder();
					for (int j = i; j < i + (N/4); j++) {
						
						sb.append(treasure[j%N]);
					}
					String check = sb.toString();
					
					int as = pass.size();
					boolean eq = false;
					for (int j = 0; j < as; j++) {
						if(check.equals(pass.get(j))) eq = true;
					}
					if(!eq)
						pass.add(check);
				}
				cnt++;
			}
			
			/*for (int i = 0; i < pass.size(); i++) {
				System.out.print(pass.get(i) +" ");
			}System.out.println();*/
			int S = pass.size();
			long[] result = new long[S];
			for (int i = 0; i < result.length; i++) {
				result[i] = Long.parseLong(pass.get(i), 16);
			}
			Arrays.sort(result);
			System.out.println("#"+t+" " + result[S-K]);
			
		}

	}

}
