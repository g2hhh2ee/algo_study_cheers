import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class d1027_s5658_보물상자비밀번호 {
	static int T, K, N;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		T = in.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = in.nextInt(); 
			int K = in.nextInt(); 
			String s = in.next();
			String t = s + s.substring(0, N / 4 - 1);
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				String hex = t.substring(i, i + N / 4);
				int num = Integer.parseInt(hex, 16);
				if (!list.contains(num))
					list.add(num);
			}
			Collections.sort(list, Collections.reverseOrder());
			System.out.println("#" + tc + " " + list.get(K - 1));
		}
	}
}
