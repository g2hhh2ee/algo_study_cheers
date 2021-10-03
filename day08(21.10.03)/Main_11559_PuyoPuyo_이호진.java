package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main_BOJ_11559_PuyoPuyo {

	static class Puyo {
		int r;
		int c;

		public Puyo(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static char[][] map;
	static boolean[][] visited;
	static int N = 12;
	static int M = 6;
	static int res = 0;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	public static List<Puyo> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[N][M];
		visited = new boolean[N][M];
		list = new ArrayList<Puyo>();
		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = ch[j];
			}
		}

		res = 0;

		// 1. 4개 뭉쳐있는지 확인(bfs)
		// 2. 없애기
		// 3. 한칸 밑으로 내리기
		// 이게 한 사이클 > 무한반복
		while (true) {
			boolean isP = true;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = false;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] == false && map[i][j] != '.') {
						// 뭉친거 찾기
						bfs(i, j);
						// 뭉친거 터트리기
						if(list.size() >= 4) {
							isP = false;
							for (int j2 = 0; j2 < list.size(); j2++) {
								Puyo temp = list.get(j2);
								map[temp.r][temp.c] = '.';
							}
						}
						list.clear();
					}
				}
			}
			// 내리기
			fall();
			
			if(isP) {
				break;
			}else {
				res++;
			}
		}
		System.out.println(res);
	}

	private static void bfs(int r, int c) {
		Queue<Puyo> que = new LinkedList<Puyo>();

		visited[r][c] = true;
		char color = map[r][c];
		que.offer(new Puyo(r, c));

		list.add(new Puyo(r, c));

		while (!que.isEmpty()) {
			Puyo node = que.poll();
			int row = node.r;
			int col = node.c;
			for (int i = 0; i < 4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				if (check(nr, nc) && visited[nr][nc] == false && map[nr][nc] == color) {
					list.add(new Puyo(nr, nc));
					que.offer(new Puyo(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}

	}

	public static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	private static void fall() {
		for (int i = 0; i < M; i++) {
			for (int j = N - 1; j > 0; j--) {
				if (map[j][i] == '.') {
					for (int k = j - 1; k >= 0; k--) {
						if (map[k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break;
						}
					}
				}
			}
		}

	}
}
