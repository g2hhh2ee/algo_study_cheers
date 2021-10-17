package boj;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main_BOJ_17472_다리만들기3 {
	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 }; // 북동남서
	static int[] dc = { 0, 1, 0, -1 };
	static int V;

	static int[][] krus;
	static int max;

	static PriorityQueue<Node> pq;
	private static int[] P;
	private static int[] R;
	
	static class Node implements Comparable<Node>{
		int s;
		int e;
		int w;
		public Node(int s, int e, int w) {
			super();
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// floodfill
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					cnt++;
					bfs(i, j, cnt + 1);
				}
			}
		}
		V = cnt;
		krus = new int[V][V];
		for (int i = 0; i < V; i++) {
			Arrays.fill(krus[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int g = 2; g < V + 2; g++) {
					if (map[i][j] == g) {
						go(i, j, g);
					}
				}
			}
		}
		pq = new PriorityQueue<>();
		for (int i = 0; i < V; i++) {
			for (int j = i+1; j < V; j++) {
				if(krus[i][j]!=Integer.MAX_VALUE) {
					pq.offer(new Node(i,j,krus[i][j]));
				}
			}
		}
		makeSet();
	}

	private static void makeSet() {
		P = new int[V];
		R = new int[V];
		for (int i = 0; i < V; i++) {
			P[i] = i;
			R[i] = 1;
		}
	}

	private static void go(int r, int c, int k) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int g = 2; g < V + 2; g++) {
					if (map[i][j] == g) {
						int res = distance(r, c, i, j);
						krus[k - 2][g - 2] = Math.min(res, krus[k - 2][g - 2]);
						krus[g - 2][k - 2] = krus[k - 2][g - 2];
					}
				}
			}
		}

	}

	private static int distance(int r1, int c1, int r2, int c2) {
		int tmin = Integer.MAX_VALUE;
		for (int d = 0; d < 4; d++) {
			int dis = distance(r1, c2, r2, c2, d);
			if (dis < 2) {
				dis = Integer.MAX_VALUE;
			}
			tmin = Math.min(tmin, dis);
		}
		return 0;
	}

	private static int distance(int r1, int c1, int r2, int c2, int d) {
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] { r1, c1, 0 });

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			int cnt = cur[2];
			int nr = cr = dr[d];
			int nc = cc = dc[d];
			if (!check(nr, nc)) {
				continue;
			}
			if (nr == r2 && nc == c2) {
				return cnt;
			}
			if (map[nr][nc] == 0) {
				que.offer(new int[] { nr, nc,cnt+1 });
			}

		}
		return d;
	}

	private static void bfs(int r, int c, int g) {
		int[][] v = new int[N][M];
		Queue<int[]> que = new LinkedList<int[]>();
		que.offer(new int[] { r, c });
		v[r][c] = 1;
		map[r][c] = g; 

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			for (int d = 0; d < 4; d++) {
				int nr = cr = dr[d];
				int nc = cc = dc[d];
				if (!check(nr, nc)) {
					continue;
				}
				if (v[nr][nc] == 0 && map[nr][nc] == 1) {
					que.offer(new int[] { nr, nc });
					v[nr][nc] = 1;
					map[nr][nc] = g;
				}
			}
		}

	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
