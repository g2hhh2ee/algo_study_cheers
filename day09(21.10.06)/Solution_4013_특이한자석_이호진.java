package swea;

import java.util.Scanner;

public class Solution_test_4013_특이한자석2 {
	private static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testcase = sc.nextInt();
		int cir, num, dir;
		for (int t = 1; t <= testcase; t++) {
			cir = sc.nextInt();
			arr = new int[5][9];
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 8; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < cir; i++) {
				num = sc.nextInt();
				dir = sc.nextInt();

				if (num == 1) {
					if (arr[num][3] != arr[num + 1][7]) {
						if (arr[num + 1][3] != arr[num + 2][7]) {
							if (arr[num + 2][3] != arr[num + 3][7]) {
								rotate(num + 3, -dir);
							}
							rotate(num + 2, dir);
						}
						rotate(num + 1, -dir);
					}
					rotate(num, dir);
				}
				if (num == 4) {
					if (arr[num][7] != arr[num - 1][3]) {
						if (arr[num - 1][7] != arr[num - 2][3]) {
							if (arr[num - 2][7] != arr[num - 3][3]) {
								rotate(num - 3, -dir);
							}
							rotate(num - 2, dir);
						}
						rotate(num - 1, -dir);
					}
					rotate(num, dir);
				}
				if (num == 2) {
					if (arr[num][7] != arr[num - 1][3]) {
						rotate(num - 1, -dir);
					}
					if (arr[num][3] != arr[num + 1][7]) {
						if (arr[num + 1][3] != arr[num + 2][7]) {
							rotate(num + 2, dir);
						}
						rotate(num + 1, -dir);
					}
					rotate(num, dir);
				}

				if (num == 3) {
					if (arr[num][3] != arr[num + 1][7]) {
						rotate(num + 1, -dir);
					}
					if (arr[num][7] != arr[num - 1][3]) {
						if (arr[num - 1][7] != arr[num - 2][3]) {
							rotate(num - 2, dir);
						}
						rotate(num - 1, -dir);
					}
					rotate(num, dir);
				}
			}
			int sum = 0;
			sum += arr[1][1];
			sum += arr[2][1] * 2;
			sum += arr[3][1] * 4;
			sum += arr[4][1] * 8;
			System.out.println("#" + t + " " + sum);
		}
	}

	private static void rotate(int num, int dir) {
		int[] temp = new int[9];
		if (dir == 1) {
			temp[1] = arr[num][8];
			for (int i = 1; i < 8; i++) {
				temp[i + 1] = arr[num][i];
			}
			for (int i = 1; i < temp.length; i++) {
				arr[num][i] = temp[i];
			}
		} else {
			temp[8] = arr[num][1];
			for (int i = 1; i < 8; i++) {
				temp[i] = arr[num][i + 1];
			}
			for (int i = 1; i < temp.length; i++) {
				arr[num][i] = temp[i];
			}
		}
	}
}
