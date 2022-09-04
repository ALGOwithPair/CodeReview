import java.io.*;
import java.util.*;

public class Solution_1953_탈주범검거 {
	
	static int N, M, R, C, L;
	static int answer;
	static boolean[][] v;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			//1시간 뒤 부터 입장
			v = new boolean[N][M];
			map = new int[N][M];
			
			for(int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for(int col = 0; col < M; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs(R, C);
			
			answer = 0;
			
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < M; col++) {
					if(v[row][col]) answer++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	static void bfs(int row, int col) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[row][col] = true;
		q.offer(new int[] {row, col, 1});
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			row = rc[0];
			col = rc[1];
			int depth = rc[2];
			
			if(depth == L) break;
			
			List<Integer> possible = new ArrayList<>();
			
			if(map[row][col] == 1) {
				possible.add(0);
				possible.add(1);
				possible.add(2);
				possible.add(3);
			} else if(map[row][col] == 2) {
				possible.add(0);
				possible.add(2);
			} else if(map[row][col] == 3) {
				possible.add(1);
				possible.add(3);
			} else if(map[row][col] == 4) {
				possible.add(0);
				possible.add(1);
			} else if(map[row][col] == 5) {
				possible.add(1);
				possible.add(2);
			} else if(map[row][col] == 6) {
				possible.add(2);
				possible.add(3);
			} else if(map[row][col] == 7) {
				possible.add(0);
				possible.add(3);
			}
			
			for(int d = 0; d < possible.size(); d++) {
				int index = possible.get(d);
				
				int nr = row + dr[index];
				int nc = col + dc[index];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && !v[nr][nc]) {
					if(index == 0) { //현재 터널 통로의 상단
						if(map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6) {
							v[nr][nc] = true;
							q.offer(new int[] {nr, nc, depth + 1});
						}
					} else if(index == 1) { //현재 터널 통로의 우측
						if(map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7) {
							v[nr][nc] = true;
							q.offer(new int[] {nr, nc, depth + 1});
						}	
					} else if(index == 2) { //현재 터널 통로의 하단
						if(map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7) {
							v[nr][nc] = true;
							q.offer(new int[] {nr, nc, depth + 1});
						}	
					} else if(index == 3) { //현재 터널 통로의 좌측
						if(map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5) {
							v[nr][nc] = true;
							q.offer(new int[] {nr, nc, depth + 1});
						}
					}
				}
			}
		}
	}
	
}
