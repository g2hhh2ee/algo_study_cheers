import java.util.Arrays;
import java.util.Scanner;

public class Solution_2112_보호필름_김배승 {

	static int T, D, W, K;
	static int[][] map;
	static int[][] copy;
	static boolean flag;
	static int min;
	public static void main(String[] args) {
		Scanner scann =new Scanner(System.in);
		T=scann.nextInt();
		for (int t = 1; t <= T; t++) {
			D=scann.nextInt();
			W=scann.nextInt();
			K=scann.nextInt();
			map=new int[D][W];
			copy=new int[D][W];
			min=Integer.MAX_VALUE;
			flag=true;
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j]=scann.nextInt();
				}
			}
			copy(map,copy);
			check();
			if(!flag) {
				for (int i = 1; i < D+1; i++) {
					nCr(0,0,i,new int[i]);	
					if(min!=Integer.MAX_VALUE) break;
				}
				System.out.println("#"+t+" "+min);
			}else {
				System.out.println("#"+t+" "+0);
			}
		}
	}
	private static void nCr(int cnt,int start, int N, int[] nums) {
		if(cnt==N) {
			nPr(0,N,new int[N], nums);
			return;
		}
		for (int i = start; i < D; i++) {
			nums[cnt]=i;
			nCr(cnt+1,i+1,N,nums);
		}
	}
	private static void nPr(int cnt, int R, int[] n, int[] nums) {
		if(cnt==R) {
			copy(map, copy);
			for (int i = 0; i < R; i++) {
				Arrays.fill(copy[nums[i]], n[i]);
			}
			check();
			if(flag) min=Math.min(min, R);
			return;
		}
		for (int i = 0; i < 2; i++) {
			n[cnt]=i;
			nPr(cnt+1, R, n, nums);
		}
	}
	private static void print() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(copy[i][j]+" ");
			}
			System.out.println();
		}
	}
	private static void copy(int[][] m, int[][] c) {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				c[i][j]=m[i][j];
			}
		}
	}
	private static void check() {
		for (int i = 0; i < W; i++) {
			int cnt=1;
			for (int j = 1; j < D; j++) {
				if(copy[j][i]==copy[j-1][i]) {
					cnt++;
					if(cnt==K) break;
				}else cnt=1;
			}
			if(cnt!=K) {
				flag=false;
				return;
			}else {
				flag=true;
			}
		}
	}

}
