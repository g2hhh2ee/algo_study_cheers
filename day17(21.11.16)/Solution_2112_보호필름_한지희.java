package ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class d1116_s2112_보호필름 {
	static int T, D, W, K, med;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			med = Integer.MAX_VALUE;
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0,0);
			
			System.out.println("#" + tc + " " + med);
		}
	}
	
	private static void dfs(int r, int cnt) {
		if (cnt >= med) 
			return;
		
		if (r == D) {
			loop: for (int i = 0; i < W; i++) {
				int same = 1;
				for (int j = 0; j < D - 1; j++) {
					if (map[j][i] == map[j + 1][i]) {
						same++;
					} else {
						same = 1;
					}

					if (same >= K) {
						continue loop;
					}
				}
				return;
			}
			med = Math.min(med, cnt);
			return;
		}

		int[] tmp = map[r].clone();

		dfs(r + 1, cnt);

		Arrays.fill(map[r], 0);
		dfs(r + 1, cnt + 1);

		Arrays.fill(map[r], 1);
		dfs(r + 1, cnt + 1);

		map[r] = tmp;
	}
}
