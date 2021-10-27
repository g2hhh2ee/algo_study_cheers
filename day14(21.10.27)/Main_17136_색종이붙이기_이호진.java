package boj;

import java.util.Scanner;

public class Main_BOJ_17136_색종이붙이기 {
	static int N;
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = 10;
		map = new int[N+1][N+1];
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		//일단 백트래킹 문제 같음
		//조건 많이 넣자
		//2중포문으로 돌아야되나
		//dfs로 끝까지 가면서 최적해 구하기
		
		if(cnt==0) {
			System.out.println(0);
			return;
		}
		
		dfs(1);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	private static void dfs(int x) {
		for (int r = x; r <= 10; r++) {
            for (int c = 1; c <= 10; c++) {
                if (map[r][c] == 1) {
                    for (int paperSize=1; paperSize<=5; paperSize++) {
                        if (paper[paperSize] > 0) {
                            if (check(r, c, paperSize)) {
                                for (int i = r; i < r + paperSize; i++) {
                                    for (int j = c; j < c + paperSize; j++) {
                                    	map[i][j] = 0;
                                    }
                                }
                                paper[paperSize]--;
                                
                                dfs(r);
                                for (int i = r; i < r + paperSize; i++) {
                                    for (int j = c; j < c + paperSize; j++) {
                                    	map[i][j] = 1;
                                    }
                                }
                                paper[paperSize]++;
                            }
                        }
                    }
                    if (map[r][c] == 1) return;
                }
            }
        }
        int paperCnt = 25;
        for (int i = 1; i <= 5; i++) {
            paperCnt -= paper[i];
        }

        ans = ans > paperCnt ? paperCnt : ans;
		
	}
	private static boolean check(int r, int c, int paperSize) {
        if(r < 1 || c < 1 || r + paperSize > 11|| c + paperSize > 11) return false;
        
        for (int i = r; i < r + paperSize; i++) {
            for (int j = c; j < c + paperSize; j++) {
                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
	}

}
