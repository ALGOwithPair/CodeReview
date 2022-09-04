import java.io.*;
import java.util.*;

public class Solution_1949_등산로조성 {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int N, K, answer;
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			int topPt = 0;
			
			for(int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int col = 0; col < N; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
					if(topPt < map[row][col]) topPt = map[row][col];
				}
			}
			
			List<int[]> list = new ArrayList<>();
			
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(topPt == map[row][col]) list.add(new int[] {row, col});
				}
			}
			
			answer = 1;
			
			for(int i = 0; i < list.size(); i++) {
				v = new boolean[N][N];
				int startRow = list.get(i)[0];
				int startCol = list.get(i)[1];
				v[startRow][startCol] = true;
				dfs(0, startRow, startCol, false);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	static void dfs(int depth, int row, int col, boolean flag) {
		if(answer < depth + 1) answer = depth + 1;
		
		for(int d = 0; d < 4; d++) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && !v[nr][nc]) {
				if(map[row][col] > map[nr][nc]) {
					v[nr][nc] = true;
					dfs(depth + 1, nr, nc, flag);
					v[nr][nc] = false;
				} else if(map[row][col] <= map[nr][nc] && !flag) {
					if(map[row][col] > map[nr][nc] - K) {
						int[][] mapTmp = new int[N][N];
						for(int r = 0; r < N; r++) {
							for(int c = 0; c < N; c++) {
								mapTmp[r][c] = map[r][c];
							}
						}
						
						map[nr][nc] = map[row][col] - 1;

						//이미 지나온 길을 다시 가야될 필요성이 있는 경우 dfs 라도 다음 처럼 방문 배열을 원래대로 돌려놔야됨.
						v[nr][nc] = true;
						dfs(depth + 1, nr, nc, true);
						v[nr][nc] = false;
						
						map = mapTmp;
					}
				}
			}
		}
	}
}
