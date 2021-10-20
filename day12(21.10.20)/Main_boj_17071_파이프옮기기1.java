package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_boj_17071_파이프옮기기1 {
	static class pipe{
        int r;
        int c;
        int d; //0: 가로 1:세로 2:대각선

        public pipe(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
	static int[][] map;
    static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N+1][N+1];
		
		//내가 맞게 이해했는지 모르겠음
		//문제에서 1부터 시작이라는데 배열의 크기를 1크게 하는게 맞는건지..
		for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
		//로직
		//bfs로 사방(8방?)
		//끝만 생각하면 될듯
		//한칸한칸 진행
		bfs();
	}
	private static void bfs() {
		Queue<pipe> q = new LinkedList<>();
        q.add(new pipe(1, 2, 0));

        int count = 0;

        while (!q.isEmpty()) {
        	pipe now = q.poll();

            if (now.r == N && now.c == N) { //기저조건
                count++;
                continue;
            }

            if (now.d == 0) {

                if(isPossible(now, 0)){
                    q.add(new pipe(now.r, now.c + 1, 0));
                }

                if(isPossible(now, 2)) {
                    q.add(new pipe(now.r + 1, now.c + 1, 2));
                }

            }else if(now.d == 1){
            
                if(isPossible(now, 1)){
                    q.add(new pipe(now.r+1, now.c, 1));
                }

                if(isPossible(now, 2)) {
                    q.add(new pipe(now.r + 1, now.c + 1, 2));
                }
                
            }else{
            
                if(isPossible(now, 0)){
                    q.add(new pipe(now.r, now.c + 1, 0));
                }
                
                if(isPossible(now, 1)){
                    q.add(new pipe(now.r+1, now.c, 1));
                }
                
                if(isPossible(now, 2)) {
                    q.add(new pipe(now.r + 1, now.c + 1, 2));
                }
                
            }
        }

        if(count==0) {
            System.out.println(0);
        }else {
            System.out.println(count);
        }
    }
	
	public static boolean isPossible(pipe now, int direction){

        switch (direction){
            case 0 :
                return now.c+1<=N && map[now.r][now.c+1] != 1;
            case 1:
                return now.r+1<=N && map[now.r+1][now.c] != 1;
            case 2:
                return now.r+1<=N && now.c+1<=N && map[now.r][now.c + 1] != 1 && map[now.r + 1][now.c + 1] != 1 && map[now.r + 1][now.c] != 1;
        }

        return false;

    }
		
}


