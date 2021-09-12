import java.util.*;
import java.io.*;

public class dustnrh {
    
// 비활성 바이러스 넣기
// 바이러스 개수에서 M개 고르는 콤비
	// 조합 다 고르고 바이러스 -> q에 넣어 bfs로 퍼뜨려
	//바이러스 다 퍼졌나 확인
	//안 퍼진 곳은 -1 리턴

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Virus> viruses = new ArrayList<>();
    static Virus[] active;
    static int min = Integer.MAX_VALUE;
    static int cnt = 0;
    static class Virus {
        int r, c, time;
        Virus(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        active = new Virus[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {//0 빈칸 1 벽 2바이러스
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    cnt++;
                } else if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0));
                }
            }
        }

        if (cnt == 0) {	//빈칸 0 이면 퍼뜨릴 곳 없다!
            System.out.println(0);
        } else {
            combi(0, 0);
            System.out.println(min == Integer.MAX_VALUE ? -1 : min);
        }
    }

    // 콤비 : 위치 N개에서 M개 뽑기 (조합)
    static void combi(int start, int count) {
        if (count == M) {	//M개 활성화
            bfs(cnt);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            active[count] = viruses.get(i);
            combi(i + 1, count + 1);
        }
    }

    //바이러스 퍼뜨리기
    static void bfs(int count) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] infected = new boolean[N][N];	//감염여부

        for (int i = 0; i < M; i++) {
            Virus virus = active[i];
            infected[virus.r][virus.c] = true;
            q.add(virus);
        }

        while (!q.isEmpty()) {
            Virus virus = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = virus.r + dr[d];
                int nc = virus.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) 
                	continue;
                if (infected[nr][nc] || map[nr][nc] == 1) 
                	continue;

                if (map[nr][nc] == 0) {
                    count--;
                }

                if (count == 0) {
                    min = Math.min(min, virus.time + 1);
                    return;
                }

                infected[nr][nc] = true;
                q.add(new Virus(nr, nc, virus.time + 1));
            }
        }
    }
}
