import java.io.*;
import java.util.*;
public class 모의SW_등산로조성 {
	static int N,K, max = Integer.MIN_VALUE;
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0, -1};
	static int [][] m;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			ArrayList<ArrayList<int[]>> arr = new ArrayList<>();
			for(int i = 0; i<21; i++) {
				arr.add(new ArrayList<>());
			}
			max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			m = new int[N][N];
			int maxV = 0;
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<N; j++) {
					m[i][j] = Integer.parseInt(st.nextToken());
					arr.get(m[i][j]).add(new int[] {i, j});
					maxV = m[i][j] > maxV ? m[i][j]: maxV;
				}
			}
			
			for(int k = 0; k<=K; k++) {
				for(int i = 0; i<N; i++) {
					for(int j = 0; j<N; j++) {
						if(m[i][j] < k) continue;
						m[i][j] -= k;
						for(int[] tmp : arr.get(maxV)) {
							dfs(tmp[0], tmp[1], 1);
						}
						m[i][j] += k;
					}
				}
			}
		
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	private static void dfs(int i, int j, int l) {

		max = max < l ? l: max;

		for(int d = 0; d<4; d++) {
			int ni = i+dx[d];
			int nj = j+dy[d];
			if(ni >=0 && nj>=0 && ni<N && nj<N && m[i][j] > m[ni][nj]) {
				dfs(ni, nj, l+1);
			}
		}
		
	}
}
