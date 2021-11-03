package ext;

import java.util.Scanner;

public class d1027_b14499_주사위굴리기_g4 {
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		map = new int[N][M];

		N = in.nextInt();
		M = in.nextInt();
		int r = in.nextInt();
		int c = in.nextInt();
		int K = in.nextInt();


		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}

		Dice curDice = new Dice(0, 1, 2, 3, 4, 5); 
		int[] dice = new int[6]; 

		for(int i = 0; i < K; i++) {
			int k = in.nextInt();
			int top = curDice.top;
			int up = curDice.up;
			int down = curDice.down;
			int left = curDice.left;
			int right = curDice.right;
			int bottom = curDice.bottom;

			Dice nextDice;

			if(k == 1) {
				k = 3;
				nextDice = new Dice(left, up, down, bottom, top, right);
			} else if(k == 2) { 
				k = 1;
				nextDice = new Dice(right, up, down, top, bottom, left);
			} else if(k == 3) { 
				k = 0;
				nextDice = new Dice(up, bottom, top, left, right, down);
			} else { 
				k = 2;
				nextDice = new Dice(down, top, bottom, left, right, up);
			}

			int nr = r + dr[k];
			int nc = c + dc[k];

			if(nr < 0 || nr >= N || nc < 0 || nc >= M) { 
				continue;
			}

			curDice = nextDice;
			r = nr; 
			c = nc;

			if(map[nr][nc] == 0) { 
				map[nr][nc] = dice[nextDice.bottom];
				System.out.println(dice[nextDice.top]);
			} else { 
				dice[nextDice.bottom] = map[nr][nc];
				map[nr][nc] = 0; 
				System.out.println(dice[nextDice.top]);
			}
		}
	}
}

class Dice {
	int top, up, down, left, right, bottom;

	Dice(int top, int up, int down, int left, int right, int bottom) {
		this.top = top;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.bottom = bottom;
	}
}
