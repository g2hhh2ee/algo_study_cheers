package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main_BOJ_17142_연구소3 {
	
	static int N, M;
	static int[] v; // 조합에서 바이러스가 선택되는지 여부

	static ArrayList<int[]> virusList = new ArrayList<>(); // 좌표 드가자  r,c,time
	
	static int[][] map;
	static boolean[][] visited; // 실제 확산되는 바이러스가 방문했는지 여부
	static int left;
	
	static int[] dr = {-1,1,0,0}; // 상하좌우
	static int[] dc = {0,0,-1,1};
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {		
		// 2 -> 비활성 바이러스
		// 조합(순서는 상관없으니까)으로 경우의수 파악한다
		// 토마토 문제처럼 한번에 하나가 아니라 한번에 여러개의 바이러스가 동시에 확산된다.
		// 조합이랑 탐색은 확실히 구분해서 문제를 풀자! 다원's의 조언!
		// 그럼 큐를 쓰면 되는건가? 어레이리스트쓸까
		// MATH.min으로 최소 시간 구한다.

		// 초기값 설정
		Scanner scann = new Scanner(System.in);
		N = scann.nextInt();
		map = new int[N][N];
		M = scann.nextInt();
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = scann.nextInt();
				if (map[i][j] == 2) { // 바이러스 넣자
					virusList.add(new int[] {i,j,1});
				} else if (map[i][j] == 0) {
					left++;
				}
			}
		}
		
		
		if(left == 0) { // 그럴일은 없겠지만 처음부터 0이 없으면 바로 끝
			System.out.println(0);
			return;
		}
		
		v = new int[M]; // 방문한 바이러스 저장할 배열(조합을 위한)
		combination(0, 0);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		// 출력
		System.out.println(min);
		
	}

	private static void combination(int cnt, int cur) {
		// 기저조건(M개면 끝)
		if(cnt == M) {
			BFS();// M개 다 찾았으면 주변 탐색
			return;
		}
		
		for (int i = cur; i < virusList.size(); i++) {
			v[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	private static void BFS() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N][N];
		
		for (int i = 0; i < M; i++) {
			q.add(virusList.get(v[i]));
			visited[virusList.get(v[i])[0]][virusList.get(v[i])[1]] = true;
		}
		
		int time = 0;
		int count = 0;
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			
			for (int k = 0; k < 4; k++) {
				int nx = arr[0] + dr[k];
				int ny = arr[1] + dc[k];
				
				
				time = arr[2];
				
				// 범위 벗어나거나 이미 갔음
				if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || visited[nx][ny]) continue;
				
				// 확산 가능
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					count++;
					q.add(new int[] {nx,ny,arr[2]+1});
				}
				
				// 그냥 지나가기
				if(map[nx][ny] == 2) {
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny,arr[2]+1});
				}
				
			}
			
			if(count == left) { //빈칸 다 넣었다
				time++;
				break;
			}
		};
	
		if(count != left) return; // 다 못채우면 그냥 끝내기
		
		min = Math.min(min, time-1);
	}

}