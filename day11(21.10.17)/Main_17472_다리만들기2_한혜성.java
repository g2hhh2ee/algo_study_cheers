package BJ;

import java.io.*;
import java.util.*;

public class Main_17472_다리만들기2_한혜성 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] parent;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = null;

		token = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		// 일단 섬 번호 매겨서 나누기
		int inum = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && visited[i][j] == false) {
					island(i, j, inum);
					inum++;
				}
			}
		}

		// 간선의 비용 구하기 (오름차순)
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		for (int i = 1; i < inum; i++) {
			for (int j = i + 1; j < inum; j++) {
				int cost = getBridge(i, j);
				if (cost != 0)
					pq.add(new int[] { i, j, cost });
			}
		}

		// 자기자신 일단 짱으로 만들기
		parent = new int[inum];
		for (int i = 1; i < inum; i++) {
			parent[i] = i;
		}

		int ans = 0;
		// 다리선택 MST
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int x = find(now[0]);
			int y = find(now[1]);
			if (x != y) {
				union(now[0], now[1]);
				ans += now[2];
			}
		}

		// 결과
		boolean flag = true;
		for (int i = 1; i < inum; i++) {
			if (find(i) != 1) {
				flag = false;
				break;
			}
		}
		System.out.println(flag ? ans : -1);

	}

	// 섬 번호
	private static void island(int r, int c, int inum) {
		visited[r][c] = true;
		map[r][c] = inum;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (!check(nr, nc))
				continue;
			if (visited[nr][nc])
				continue;

			if (map[nr][nc] == 1) {
				island(nr, nc, inum);
			}
		}

	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	
	
	private static void union(int i, int j) {
		int x = find(i);
		int y = find(j);

		if (x > y)
			parent[x] = y;
		else
			parent[y] = x;
	}

	private static int find(int i) {
		if (i == parent[i])
			return i;
		return parent[i] = find(parent[i]);
	}
	
	
	

	private static int getBridge(int start, int end) {
		boolean[][][] visit = new boolean[N][M][4];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != start)
					continue;
				for (int k = 0; k < dr.length; k++) {
					int r = i + dr[k];
					int c = j + dc[k];
					if (!check(r, c) || map[r][c] != 0)
						continue;
					q.add(new int[] { r, c, 0, k });
					visit[r][c][k] = true;
				}
			}
		}

		int ret = 0;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			if (map[now[0]][now[1]] == end) {
				if (now[2] == 1)
					continue;
				ret = now[2];
				break;
			}
			int nr = now[0] + dr[now[3]];
			int nc = now[1] + dc[now[3]];

			if (check(nr, nc) && !visit[nr][nc][now[3]] && (map[nr][nc] == 0 || map[nr][nc] == end)) {
				visit[nr][nc][now[3]] = true;
				q.add(new int[] { nr, nc, now[2] + 1, now[3] });
			}
		}

		return ret;
	}

}
