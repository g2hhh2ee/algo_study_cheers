import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_20057_마법사상어와토네이도_김배승 {
	static int N;
	static int[][] map;
	static int[] dr= {0,1,0,-1};
	static int[] dc= {-1,0,1,0};
	static int sum;
	static int d;
	public static void main(String[] args) throws IOException {
		BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(bf.readLine());
		map=new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st=new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		go(N/2,N/2);
		System.out.println(sum);
	}
	private static void go(int sr, int sc) {
		int cnt=1;
		int cr=sr;
		int cc=sc;
		for (int i = 0; i < N; i++) {
			int count=cnt;
			for (int j = 0; j < 2; j++) {
				while(count!=0) {
					int nr=cr+dr[d];
					int nc=cc+dc[d];
					map[nr][nc]+=map[cr][cc];
					map[cr][cc]=0;
					if(d==0) west(nr, nc);
					else if(d==1) south(nr, nc);
					else if(d==2) east(nr,nc);
					else north(nr, nc);
					map[nr][nc]=0;
					if(nr==0 && nc==0) {
						return;
					}
					cr=nr;
					cc=nc;
					count--;
				}
				count=cnt;
				d=(d+1)%4;
			}
			cnt++;
		}
		
	}
	private static void west(int r, int c) {
		if(!check(r,c-2)) sum+=map[r][c]*5/100;
		else map[r][c-2]+=map[r][c]*5/100;
		if(!check(r-1,c-1)) sum+=map[r][c]*10/100;
		else map[r-1][c-1]+=map[r][c]*10/100;
		if(!check(r+1,c-1)) sum+=map[r][c]*10/100;
		else map[r+1][c-1]+=map[r][c]*10/100;
		if(!check(r-2,c)) sum+=map[r][c]*2/100;
		else map[r-2][c]+=map[r][c]*2/100;
		if(!check(r-1,c)) sum+=map[r][c]*7/100;
		else map[r-1][c]+=map[r][c]*7/100;
		if(!check(r+1,c)) sum+=map[r][c]*7/100;
		else map[r+1][c]+=map[r][c]*7/100;
		if(!check(r+2,c)) sum+=map[r][c]*2/100;
		else map[r+2][c]+=map[r][c]*2/100;
		if(!check(r-1,c+1)) sum+=map[r][c]*1/100;
		else map[r-1][c+1]+=map[r][c]*1/100;
		if(!check(r+1,c+1)) sum+=map[r][c]*1/100;
		else map[r+1][c+1]+=map[r][c]*1/100;
		if(!check(r,c-1)) sum+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
		else map[r][c-1]+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
	}
	private static void south(int r, int c) {
		if(!check(r+2,c)) sum+=map[r][c]*5/100;
		else map[r+2][c]+=map[r][c]*5/100;
		if(!check(r+1,c-1)) sum+=map[r][c]*10/100;
		else map[r+1][c-1]+=map[r][c]*10/100;
		if(!check(r+1,c+1)) sum+=map[r][c]*10/100;
		else map[r+1][c+1]+=map[r][c]*10/100;
		if(!check(r,c-2)) sum+=map[r][c]*2/100;
		else map[r][c-2]+=map[r][c]*2/100;
		if(!check(r,c-1)) sum+=map[r][c]*7/100;
		else map[r][c-1]+=map[r][c]*7/100;
		if(!check(r,c+1)) sum+=map[r][c]*7/100;
		else map[r][c+1]+=map[r][c]*7/100;
		if(!check(r,c+2)) sum+=map[r][c]*2/100;
		else map[r][c+2]+=map[r][c]*2/100;
		if(!check(r-1,c-1)) sum+=map[r][c]*1/100;
		else map[r-1][c-1]+=map[r][c]*1/100;
		if(!check(r-1,c+1)) sum+=map[r][c]*1/100;
		else map[r-1][c+1]+=map[r][c]*1/100;
		if(!check(r+1,c)) sum+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
		else map[r+1][c]+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
	}
	private static void east(int r, int c) {
		if(!check(r,c+2)) sum+=map[r][c]*5/100;
		else map[r][c+2]+=map[r][c]*5/100;
		if(!check(r-1,c+1)) sum+=map[r][c]*10/100;
		else map[r-1][c+1]+=map[r][c]*10/100;
		if(!check(r+1,c+1)) sum+=map[r][c]*10/100;
		else map[r+1][c+1]+=map[r][c]*10/100;
		if(!check(r-2,c)) sum+=map[r][c]*2/100;
		else map[r-2][c]+=map[r][c]*2/100;
		if(!check(r-1,c)) sum+=map[r][c]*7/100;
		else map[r-1][c]+=map[r][c]*7/100;
		if(!check(r+1,c)) sum+=map[r][c]*7/100;
		else map[r+1][c]+=map[r][c]*7/100;
		if(!check(r+2,c)) sum+=map[r][c]*2/100;
		else map[r+2][c]+=map[r][c]*2/100;
		if(!check(r-1,c-1)) sum+=map[r][c]*1/100;
		else map[r-1][c-1]+=map[r][c]*1/100;
		if(!check(r+1,c-1)) sum+=map[r][c]*1/100;
		else map[r+1][c-1]+=map[r][c]*1/100;
		if(!check(r,c+1)) sum+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
		else map[r][c+1]+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
		
	}
	private static void north(int r, int c) {
		if(!check(r-2,c)) sum+=map[r][c]*5/100;
		else map[r-2][c]+=map[r][c]*5/100;
		if(!check(r-1,c-1)) sum+=map[r][c]*10/100;
		else map[r-1][c-1]+=map[r][c]*10/100;
		if(!check(r-1,c+1)) sum+=map[r][c]*10/100;
		else map[r-1][c+1]+=map[r][c]*10/100;
		if(!check(r,c-2)) sum+=map[r][c]*2/100;
		else map[r][c-2]+=map[r][c]*2/100;
		if(!check(r,c-1)) sum+=map[r][c]*7/100;
		else map[r][c-1]+=map[r][c]*7/100;
		if(!check(r,c+1)) sum+=map[r][c]*7/100;
		else map[r][c+1]+=map[r][c]*7/100;
		if(!check(r,c+2)) sum+=map[r][c]*2/100;
		else map[r][c+2]+=map[r][c]*2/100;
		if(!check(r+1,c-1)) sum+=map[r][c]*1/100;
		else map[r+1][c-1]+=map[r][c]*1/100;
		if(!check(r+1,c+1)) sum+=map[r][c]*1/100;
		else map[r+1][c+1]+=map[r][c]*1/100;
		if(!check(r-1,c)) sum+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
		else map[r-1][c]+=map[r][c]-map[r][c]*5/100-map[r][c]*10/100-map[r][c]*10/100-map[r][c]*2/100-map[r][c]*2/100
				-map[r][c]*7/100-map[r][c]*7/100-map[r][c]*1/100-map[r][c]*1/100;
	}
	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}

}
