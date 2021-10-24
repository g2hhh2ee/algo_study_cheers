package ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b17281_야구_1024 {
	static boolean visit[];
	static int map[][];
	static int[] order = new int[10];
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][9];
		visit = new boolean[10];
		order[4] = 1;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(ans);

		perm(1);
	}

	private static void perm(int i) {
		if (i > 9) {
			play();
			return;
		}

		if (i == 4) {
			perm(i + 1);
			return;
		}

		for (int j = 2; j <= 9; j++) {

			if (!visit[j]) {
				visit[j] = true;
				order[i] = j;
				perm(i + 1);
				visit[j] = false;
			}
		}
	}

	private static void play() {
		int score = 0;
		
	}

}
