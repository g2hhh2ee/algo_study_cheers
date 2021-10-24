
package boj;

import java.util.Scanner;

public class Main_boj_17281_야구 {
	static int max;
	static int N;
	static int[][] score;
	static boolean[] v;
	static int[] players;
	public static void main(String[] args) {
		//1번 선수는 4번에 넣고 순열 돌리고
		//기저조건 cnt==9이면 그때 점수 계산
		//max에 점수 넣어서 가장 큰 점수 출력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		score = new int[N+1][10];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 9; j++) {
				score[i][j] = sc.nextInt();
			}
		}
		max = Integer.MIN_VALUE;
		
		v = new boolean[10]; //편의상 10으로 해서 인덱스와 번호 일치
		players = new int[10];
		
		v[4] = true;
		players[4] = 1;
		
		
		perm(2);
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print(score[i][j]);
//			}
//			System.out.println();
//		}
		
		System.out.println(max);
		

	}
	private static void perm(int i) {
		if (i == 10) {
            play();
            return;
        }
 
        for (int j = 1; j <= 9; j++) {
            if (v[i]) {
                continue;
            }
            v[j] = true;
            players[j] = i;
            perm(i + 1);
            v[j] = false;
        }
		
	}
	
	private static void play() {
		int point = 0;
        int startPlayer = 1;
        boolean[] lu;
 
        for (int i = 1; i <= N; i++) {
            int outCnt = 0;
            lu = new boolean[4];
 
            out: while (true) {
                for (int j = startPlayer; j <= 9; j++) {
                	int hitter = score[i][players[j]];
 
                	if(hitter == 0) {
                		outCnt++;
                        break;
                	}else if(hitter == 1) {
                		for (int k = 3; k >= 1; k--) {
                            if (lu[k]) {
                                if (k == 3) {
                                	point++;
                                    lu[k] = false;
                                    continue;
                                }
 
                                lu[k] = false;
                                lu[k + 1] = true;
                            }
                        }
                        lu[1] = true;
                        break;
                	}else if(hitter == 2) {
                		for (int k = 3; k >= 1; k--) {
                            if (lu[k]) {
                                if (k == 3 || k == 2) {
                                	point++; 
                                    lu[k] = false;
                                    continue;
                                }
 
                                lu[k] = false;
                                lu[k + 2] = true;
                            }
                        }
                        lu[2] = true;
                        break;
                	}else if(hitter == 3) {
                		for (int k = 3; k >= 1; k--) {
                            if (lu[k]) {
                            	point++;
                                lu[k] = false;
                            }
                        }
 
                        lu[3] = true;
                        break;
                	}else if(hitter == 4) {
                		for (int k = 1; k <= 3; k++) {
                            if (lu[k]) {
                            	point++;
                                lu[k] = false;
                            }
                        }
                        point++;
                        break;
                	}
                	
                    if (outCnt == 3) {
                        startPlayer = j + 1;
                        if (startPlayer == 10) {
                            startPlayer = 1;
                        }
                        break out;
                    }
                }
                startPlayer = 1;
            }
        }
 
        max = Math.max(max, point);
	}

}
