package ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class d1110_b20057_마법사상어와토네이도 {
	static int N, map[][], result;
	static int dx[] = {0,1,0,-1};
    static int dy[] = {-1,0,1,0};
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
		torn(1,0,0,0,N/2,N/2);
		System.out.println();
	}
	private static void torn(int cnt, int cntnum, int d, int dirnum, int x, int y) {
		if(x==0 && y==0) return;
		
	}
}
