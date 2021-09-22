import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			int ans = 1;

			int N = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int div = N / P;
			int mod = N % P;

			for (int i = 0; i < P - mod; i++) {
	            ans  = ans * div;
	        }
			div++;
			
			System.out.println();
		}
		
		in.close();

	}
}
