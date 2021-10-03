package BJ;

import java.io.*;
import java.util.*;

public class Main_11559_PuyoPuyo_한혜성 {

	static char [][] map;
	static boolean [][] visited;
	static int result;
	static int N,M;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Stack<Puyo> stack;
	
	public static void main(String[] args) throws Exception{

		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		N = 12; 
		M = 6;
		stack = new Stack<Puyo>();
		map = new char [12][6];
		visited = new boolean [12][6];
		
		for (int i = 0; i < N; i++) {
			char [] ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = ch[j];
			}
		}//map 완료
		
		
		// 일단 다 보고
		//방문 안했고 색이 있으면 que에 넣고
		// que에 있는 애들 이제 bfs 돌려
		//같은 색 나오면 카운트 올리고 스택에 넣다가 카운트 4 이상이면 .으로 바꿔
		//카운트 4미만이면 카운트수만큼 스택에서 다시 빼고
		//que에 있는 애들로 다 하고 
		//맵 돌려서 자기가 .이고 위 봤을 때 .이 아니면 바꾸기
		
		find();
		
		/*for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}System.out.println();
		}*/
		System.out.println(result);
	}

	private static void find() {
		while(true) {
			boolean pop = false; //한번이라도 터뜨릴게 있으면 result올리고 아니면 와일 나가
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] != '.' && !visited[i][j]) {
						
						go(i,j);
						
						int ss = stack.size();	
						if(ss < 4) {//4개미만이면 스택에서 그냥 빼
							for (int k = 0; k < ss; k++) {
								stack.pop();
							}
						}
						else {//같은 색 4개 이상이면 맵 바꾸고 스택에서 빼
							pop = true;
							//System.out.println("sdkf");
							for (int k = 0; k < ss; k++) {
								Puyo pu = stack.peek();
								map[pu.r][pu.c] = '.';
								stack.pop();
							}
						}
						
						//색 같은거 일단 바꿨고 3개이하인거 다 뺐음 		
					}
				}
			}
			//돌때마다 방문처리 초기화해줘야 함
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = false;
				}
			}
			down();//빈칸 내리기
			
			if(pop) result++;
			else break;
			
		}
		
	}

	private static void go(int r, int c) {
		//큐 넣고 사방돌려서 색 같으면 스택에 넣고 방문처리해줘
		Queue<Puyo> que = new LinkedList<>();
		char temp = map[r][c];//시작 색깔 같으면 계속 가고 다르면 나와 
		que.offer(new Puyo(r,c));
		visited[r][c] = true;
		stack.push(new Puyo(r,c));
		
		//System.out.println("start"+r+" "+c);
		while(!que.isEmpty()) {
			Puyo p= que.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				
				//범위 넘거나 방문 했던거면 그냥 나가
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				
				
				if(map[nr][nc] == temp) {
					stack.push(new Puyo(nr,nc));
				//	System.out.println(nr+" "+ nc);
					que.offer(new Puyo(nr,nc));
					visited[nr][nc] = true;
				}
				
			}
			
		}
		
		
	}
	
	private static void down() {
		
		//밑부터 볼거임 자신이 .이고 위를 봤을때 .이 아니면 자기 자신 바꾸고 그 자리 .으로 해놔
		for (int i = N-1; i > 0; i--) {//위 봐야하니까 0안봄
			for (int j = 0; j < M; j++) {
				if(map[i][j] == '.' ) {
					/////
					for (int i2 = i-1; i2 >=0; i2--) {
						if(map[i2][j] != '.') {
							map[i][j] = map[i2][j];
							map[i2][j] = '.';
							break;
						}
					}
				
				
				}
			}
		}
		
		
	}


	private static boolean check(int r, int c) {
		return r>= 0 && c>=0 && r<N && c < M;
	}


	static class Puyo{
		int r;
		int c;
		public Puyo(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
	}
}