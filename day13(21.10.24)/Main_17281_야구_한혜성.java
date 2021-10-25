package BJ;

import java.io.*;
import java.util.*;

public class Main_17281_야구_한혜성 {

	static int N;
	static int[][] inning;
	static int result;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		inning = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(token.nextToken());
			}
		}

/*		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(inning[i][j] + " ");
			}
			System.out.println();
		}*/

		//permutation 돌리고 순서 정한 후 (4번째 순서는 1번 타자 필수)
		//각 타자에 따른 능력치에 대한 구장 배열[4]에 현 상황 넣어놓고
		//각 타자가 공을 치고 나서 구장 배열에 대해 구장의 인덱스3에 1이 들어있으면 그만큼 카운트 올림
		//아웃(0)이 나올 때마다 아웃에 대한 카운트 올리고 그것이 3이 되면 이닝 바꾸기 
		//이닝이 올라감에 따라 이닝배열에 있는 값도 바뀌어야함(타자 기준으로 바뀌기 타자의 능력은 한번 정해지면 바뀌지 않음)
		//이를 반복한 후 카운트에 대한 값 비교하여 맥시멈 출력
		
		result = Integer.MIN_VALUE;
		Perm(0, new boolean[9], new int[8]);
		
		
		System.out.println(result);
		
		
		
		
	}

	private static void Perm(int cnt, boolean[] visited, int[] nums) {
	
		if(cnt == 8) {
			game(nums);
			
			return;
		}
		
		for (int i = 1; i <= 8; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			
			nums[cnt] = i;
			Perm(cnt+1, visited, nums);
			visited[i] = false;
			
		}
		
		
	}

	private static void game(int[] nums) {

		//nums에 있는 인덱스를 기준으로 inning에 있는 값들 새로운 배열에 넣기(4번째 타자는 1번 선수여야함)
		int [] player = new int[9];
		
		for (int i = 0; i < player.length; i++) {
			if(i < 3) {
				player[i] = nums[i];
			}else if(i == 3) {
				player[i] = 0;
			}else {
				player[i] = nums[i-1];
			}
		}
		
		//각 인덱스로 순서 다 정해놨음
		//이제 절대적 룰을 지켜 넥스트 레벨
		//player 이닝 값이랑 현재 이닝 카운트 가져와서 와일문으로 돌리기
		int [] baseball = new int[9];
		int out_count = 0;
		int inning_count = 0;
		
		for (int i = 0; i < baseball.length; i++) {
			baseball[i] = inning[0][player[i]];
		}
		int player_no = 0;
		int [] ground = new int[3];
		int score = 0;
		//각 baseball에 이닝별 선수들의 값 넣기 완성
		here : while(inning_count != N) {
			
			if(out_count == 3) {
				out_count = 0;
				inning_count++;
				if(inning_count == N) break here;
				
				for (int i = 0; i < baseball.length; i++) {
					baseball[i] = inning[inning_count][player[i]];
				}
				for (int i = 0; i < ground.length; i++) {
					ground[i] = 0;
				}
			}
			
			//경기 룰 지정
			if(baseball[player_no] == 0) out_count++;
		
			else if(baseball[player_no] == 1) {
				if(ground[2] == 1) {
					score++;
				}
				for (int i = 1; i >= 0; i--) {
					ground[i+1] = ground[i];
				}
				ground[0] = 1;
				
			}else if(baseball[player_no] == 2) {
				if(ground[2] == 1) {
					score++;
				}
				if(ground[1] == 1) {
					score++;
				}
				ground[2] = ground[0];
				ground[1] = 1;
				ground[0] = 0;
				
			}else if(baseball[player_no] == 3) {
				for (int i = 0; i < ground.length; i++) {
					if(ground[i] == 1) {
						score++;
					}
				}
				for (int i = 0; i < ground.length; i++) {
					ground[i] = 0;
				}
				ground[2] = 1;
			}
			else if(baseball[player_no] == 4){
				score++;
				for (int i = 0; i < ground.length; i++) {
					if(ground[i] == 1) {
						score++;
					}
				}
				for (int i = 0; i < ground.length; i++) {
					ground[i] = 0;
				}
				
			}
			
			player_no = (player_no+1) % 9;
			
			
			
			
		}
		
		result = Math.max(score, result);
		
		
		
		
		
		
		
		
	}

	
	
	
}
