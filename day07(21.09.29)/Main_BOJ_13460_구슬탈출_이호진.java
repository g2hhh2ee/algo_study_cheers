package boj;

import java.io.*;
import java.util.*;

class Ball {
    int x;
    int y;
    int count;

    public Ball(int x,int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class Main_BOJ_13460_구슬탈출{

    static int N,M;
    static char[][] map;
    static Ball r_ball, b_ball;
    static boolean[][][][] visited;
    static int result = -1;
    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map= new char[N][M];
        visited = new boolean[N][M][N][M];

        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == 'R') {
                    r_ball = new Ball(i, j,0);
                }
                else if(map[i][j] == 'B') {
                    b_ball = new Ball(i, j,0);
                }
            }
        }
        bfs(r_ball, b_ball);
        System.out.println(result);
    }
    
    static void bfs(Ball r_ball, Ball b_ball) {
        Queue<Ball> redQ = new LinkedList<>();
        Queue<Ball> blueQ = new LinkedList<>();

        redQ.offer(r_ball);
        blueQ.offer(b_ball);

        // 빨간 구슬과 파란구슬의 위치 방문 처리
        visited[r_ball.x][r_ball.y][b_ball.x][b_ball.y]= true;

        while (!redQ.isEmpty() && !blueQ.isEmpty()) {
            Ball nr_ball = redQ.poll();
            Ball nb_ball = blueQ.poll();

            if(nr_ball.count > 10) {
                result = -1;
                return;
            }

            // 파란색이 빠져나간 경우
            if(map[nb_ball.x][nb_ball.y] == 'O') {
                continue;
            }

            // 빨간 색이 빠져나가고 파란색은 빠져 나가지않은 경우
            if(map[nr_ball.x][nr_ball.y] == 'O') {
                result = nr_ball.count;
                return;
            }

            //상하좌우로 기울이기
            for(int i = 0; i < 4; i++) {

                // 기울였을때 파란 구슬이 도달하는 지점으로 이동
                int bx = nb_ball.x;
                int by = nb_ball.y;
                while(true) {
                    bx += dx[i];
                    by += dy[i];
                    // 빠져나간 경우
                    if(map[bx][by] == 'O') break;
                    // 벽을 만난 경우
                    else if(map[bx][by] == '#'){
                        bx -= dx[i];
                        by -= dy[i];
                        break;
                    }
                }

                /// 기울였을때 빨간 구슬이 도달하는 지점으로 이동
                int rx = nr_ball.x;
                int ry = nr_ball.y;
                while(true) {
                    rx += dx[i];
                    ry += dy[i];
                    // 빠져나간 경우
                    if(map[rx][ry] == 'O') break;
                        // 벽을 만난 경우
                    else if(map[rx][ry] == '#'){
                        rx -= dx[i];
                        ry -= dy[i];
                        break;
                    }
                }

                if(bx == rx && by == ry && map[rx][ry] != 'O') {
                    // 이동한 거리가 더 긴 쪽이 덜 이동하게 처리
                    int r_dis = Math.abs(nr_ball.x - rx) + Math.abs(nr_ball.y - ry);
                    int b_dis = Math.abs(nb_ball.x - bx) + Math.abs(nb_ball.y - by);

                    // 빨간 구슬 이동
                    if(r_dis > b_dis) {
                        rx -= dx[i];
                        ry -= dy[i];
                    }
                    // 파란 구슬 이동
                    else {
                        bx -= dx[i];
                        by -= dy[i];
                    }
                }

                // 기울였을때 구슬들이 도달하는 지점을 방문 처리 후 큐에 추가 - 두 구슬 중 이전과 하나라도 다르다면 다른결과가 있을수 있음
                if(!visited[rx][ry][bx][by]) {
                    // 방문처리
                    visited[rx][ry][bx][by] = true;
                    // 두 구슬을 큐에 추가
                    redQ.offer(new Ball(rx, ry, nr_ball.count + 1));
                    blueQ.offer(new Ball(bx, by, nb_ball.count + 1));
                }
            }

        }
    }
}
