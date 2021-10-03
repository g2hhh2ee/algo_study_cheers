//Main_11559_PuyoPuyo
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class b11559_뿌요뿌요 {
	static int R = 12, C = 6;
	static int cnt;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][] visited;
	static ArrayList<Node> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		int cnt = 0;// 4개 이상 모인 거 탐색
		while (true) {
			boolean flag = true;// while문 탈출용
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] != '.') {
						list = new ArrayList<>();
						bfs(map[i][j], i, j);

						if (list.size() >= 4) {
							flag = false; 
							for (int k = 0; k < list.size(); k++) {
								map[list.get(k).r][list.get(k).c] = '.'; 
							}
						}
					}
				}
			}
			fallp(); // 뿌요들을 떨어뜨린다.
			if (flag)
				break;
			else {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	public static void fallp() {
		for (int i = 0; i < R; i++) {
			for (int j = C - 1; j > 0; j--) {
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

	public static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void bfs(char start, int r, int c) {
		Queue<Node> q = new LinkedList<>();
		list.add(new Node(r, c));
		q.offer(new Node(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr >= 0 && nc >= 0 && nr < R && nc < C && visited[nr][nc] == false && map[nr][nc] == start) {
					visited[nr][nc] = true;
					list.add(new Node(nr, nc));
					q.offer(new Node(nr, nc));
				}
			}
		}
	}

}
