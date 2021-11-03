import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution_SW_5658_보물상자비밀번호 {

	static int T, N, K;
	static String num;
	static PriorityQueue<Integer> pq;
	public static void main(String[] args) {
		Scanner scann=new Scanner(System.in);
		T=scann.nextInt();
		for (int t = 1; t <= T; t++) {
			N=scann.nextInt();
			K=scann.nextInt();
			num=scann.next();
			pq=new PriorityQueue<>();
			for (int i = 0; i < N/4-1; i++) {
				num+=num.charAt(i);
			}
			int a=N/4-1;
			int b=0;
			for (int i = 0; i < N/4; i++) {
				for (int j = i; j < N+i; j++) {
					int c=num.charAt(j)-'0';
					if(c>9) 
						b+=(c-7)*Math.pow(16, a--);
					else b+=c*Math.pow(16, a--);
					if(a<0) {
						if(!pq.contains(b)) pq.add(b);
						a=N/4-1;
						b=0;
					}
				}
			}
			ArrayList<Integer> nums=new ArrayList<>();
			while(!pq.isEmpty())
				nums.add(0, pq.poll());
			System.out.println("#"+t+" "+nums.get(K-1));
		}
	}

}
