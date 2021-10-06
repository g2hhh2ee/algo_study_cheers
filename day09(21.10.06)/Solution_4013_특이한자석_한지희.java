import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석_한지희 {

	static int K;
	static int[][] mag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			mag = new int[5][9];
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 8; j++) {
					mag[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				int[] rot = new int[5];//회전 방향
				rot[num] = dir;
				
				//right
				for (int r = num; r <= 4; r++) {
					if(mag[r][3] == mag[r+1][7]) break;
					else rot[r+1] = -rot[r];
				}
				
				//left
				for (int r = num; r >= 1; r--) {
					if(mag[r][7] == mag[r-1][3]) break;
					else rot[r-1] = -rot[r];
				}
				
				//rotate
				for (int r = 1; r <= 4; r++) {
					if(rot[r] == 0) continue;
					else if(rot[r] == 1) {//clockwise
						int temp = mag[r][8];
						for (int c = 8; c >= 1; c--) {
							mag[r][c] = mag[r][c-1];
						}
						mag[r][1] = temp;
					}
					else if(rot[r] == -1) {//counterclockwise
						int temp = mag[r][1];
						for (int c = 1; c <= 8; c++) {
							mag[r][c] = mag[r][c+1];
						}
						mag[r][8] = temp;
					}
				}
			}
			
			int sum = 0;
			for (int i = 1; i <= 4; i++) {
				if(mag[i][1] == 1) {
					sum += 1<<i;
				}
			}
			System.out.println("#" + tc + " " + sum);
		}

	}


}
