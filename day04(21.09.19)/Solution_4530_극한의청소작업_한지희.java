import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_4530_극한의청소작업_한지희 {
	
	static long cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String[] s = in.readLine().split(" ");
			long a = c(s[0].toCharArray());
			long b = c(s[1].toCharArray());
			cnt = b - a;
			if (a < 0 && b > 0) cnt--;

			System.out.println("#" + tc + " " + cnt);

		}
		in.close();
	}

	public static long c(char[] floor) {
		for (int i = 0; i < floor.length; i++) {
			if (floor[i] == '-') //-부터 시작
				continue;//ㄱ ㄱ
			if (floor[i] > '3') // 3보다 큰 수들은 1 뺀다
				floor[i]--;
			
		}
		return Long.parseLong(String.valueOf(floor), 9);
	}

}
