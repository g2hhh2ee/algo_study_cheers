package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_BOJ_16236_아기상어3 {
	
	static class Fish {
		int row;
		int col;
		int size;
		int eatCount;
		int distance;

		public Fish(int row, int col, int size, int eatCount, int distance) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.eatCount = eatCount;
			this.distance = distance;
		}
	}
	static PriorityQueue<Fish> pq = new PriorityQueue<Fish>(new Comparator<Fish>() {
		@Override
		public int compare(Fish o1, Fish o2) {
			if (o1.distance == o2.distance) {
				if (o1.row == o2.row) {
					return o1.col - o2.col;
				} else {
					return o1.row - o2.row;
				}
			} else {
				return o1.distance - o2.distance;
			}
		}
	});
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = 0;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		Fish shark = null;
		List<Fish> feeds = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(temp[j]) == 9) {
					shark = new Fish(i, j, 2, 0, 0);
					map[i][j] = 0;
					continue;
				}
				if (Integer.parseInt(temp[j]) == 1) {
					feeds.add(new Fish(i, j, 0, 0, 0));
				}
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		//bfs()
		//함수 안써보고 그냥 메인에서 처리해봐라
		//우선순위 큐에서 컴페어투 오버라이드 할 수 있다
		
		//bfs돌면서 물고기를 찾고 이걸 우선순위큐에 넣는다
		//우선순위큐에서 작업가능하지 않을까
		//굳이 물고기랑 상어를 클래스 나눠야될까
		
		
		if (feeds.isEmpty()) {
			System.out.println(0);
			return;
		}
		
		Queue<Fish> queue = new LinkedList<>();
		queue.offer(shark);
		while (true) {
			boolean[][] chkMap = new boolean[N][N];
			chkMap[queue.peek().row][queue.peek().col] = true;
			while (!queue.isEmpty()) {
				Fish fish = queue.poll();
				int nowRow = fish.row;
				int nowCol = fish.col;
				for (int i = 0; i < 4; i++) {
					int mr = nowRow + dr[i];
					int mc = nowCol + dc[i];
					if (mr < 0 || mr >= N || mc < 0 || mc >= N || map[mr][mc] > fish.size || chkMap[mr][mc]) {
						continue;
					}
					if (map[mr][mc] < fish.size && map[mr][mc] != 0) {
						pq.offer(new Fish(mr, mc, fish.size, fish.eatCount + 1, fish.distance + 1));
					}
					queue.offer(new Fish(mr, mc, fish.size, fish.eatCount, fish.distance + 1));
					chkMap[mr][mc] = true;
				}
			}
			if (pq.isEmpty()) {
				System.out.println(result);
				return;
			}
			Fish fish = pq.poll();
			if (fish.size == fish.eatCount) {
				fish.size++;
				fish.eatCount = 0;
			}
			map[fish.row][fish.col] = 0;
			result += fish.distance;
			queue.offer(new Fish(fish.row, fish.col, fish.size, fish.eatCount, 0));
			pq.clear();
		}
	}


}
