package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_test_5658_보물상자비밀번호 {
	static int T = 0;
	static int N = 0;
	static int K = 0;
	static HashSet<Integer> set;
	static char[] box;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1 ; t <= T ; ++t) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			box = br.readLine().toCharArray();
			// 박스길이를 4로 나눈 값은 한 변
			// 포문은 N-1번만 돌아도 충분할 듯
			// 마지막 회전을 똑같이 되니까.
			// 회전하고 리스트에 넣고 -> 이러니까 중복 제거 안됨
			// k번째 추출?
			// 번호 얻고 회전 vs 회전 하고 번호
			// 전자당첨
			
			set = new HashSet<>();
			
			for(int i = 0 ; i < N - 1 ; ++i) {
				getNum();
				spin();
			}

			ArrayList<Integer> list = new ArrayList<>();
			
			for(int i : set) list.add(i);
			
			Collections.sort(list, Collections.reverseOrder());
			
			System.out.println("#" + t + " " + list.get(K - 1));
		}
	}
	
	private static void spin() {
		char temp = box[N - 1];
		
		for(int i = N - 1 ; i > 0 ; i--) {
			box[i] = box[i - 1];
		}
		
		box[0] = temp;
	}
	

	private static void getNum() {
		for(int i = 0 ; i < N ; i += N / 4) {
			String hex = "";

			for(int j = i ; j < i + N / 4 ; j++) {
				hex += box[j];
			}
			
			int ten = Integer.parseInt(hex, 16);
			set.add(ten);
		}
	}
}	