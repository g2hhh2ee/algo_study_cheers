package ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class d1110_b20057_마법사상어와토네이도 {
	static int N, map[][], result;
	static int dr[] = {0,1,0,-1};
    static int dc[] = {-1,0,1,0};
    static int curr, curc, sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		curr = N/2;
		curc = N/2;
		torn(1,0,0,0,curr,curc);
		System.out.println();
	}
	private static void torn(int i, int j, int k, int l, int curr2, int curc2) {
		// TODO Auto-generated method stub
		
	}
	
}
