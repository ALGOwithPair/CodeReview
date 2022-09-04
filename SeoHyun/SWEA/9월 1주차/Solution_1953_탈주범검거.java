package swea;
import java.io.*;
import java.util.*;
public class 모의SW_1953_탈주범검거 {
	static boolean [][] isVisited;
	static int [][] map;
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0, -1};
	static int N, M,R, C, L;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T;tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 세로
			M = Integer.parseInt(st.nextToken()); // 가로
			R = Integer.parseInt(st.nextToken()); // 맨홀 세로
			C = Integer.parseInt(st.nextToken()); // 맨홀 가로
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요시간
		
			map = new int[N][M];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j = 0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			isVisited = new boolean[N][M];

			int res = bfs();
			sb.append("#"+tc+" ").append(res).append("\n");
		}
		System.out.print(sb);
	}
	static int bfs() {
		Queue<int []> q = new ArrayDeque<int[]>();
		q.offer(new int[] {R, C});
		isVisited[R][C] = true;
		int ans = 1;
		int hours = 0;
		while(!q.isEmpty()) {
			if(hours == L-1) break;
			int size = q.size();
			while(size-- > 0) {
				int [] tmp = q.poll();
				int r = tmp[0]; int c = tmp[1];

				boolean goUp = false;
				boolean goDown = false;
				boolean goRight = false;
				boolean goLeft = false;

				switch(map[r][c]) {
				case 1:
					goDown = true;
					goUp = true;
					goRight = true;
					goLeft = true;
					break;
				case 2:
					goDown = true;
					goUp = true;
					break;
				case 3:
					goRight = true;
					goLeft = true;
					break;
				case 4:
					goUp = true;
					goRight = true;
					break;
				case 5:
					goDown = true;
					goRight = true;
					break;
				case 6:
					goDown = true;
					goLeft = true;
					break;
				case 7:
					goUp = true;
					goLeft = true;
					break;
				}
				for(int d = 0; d<4; d++) {
					if(d == 0 && !(goUp)) continue;
					if(d == 1 && !(goRight)) continue;
					if(d == 2 && !(goDown)) continue;
					if(d == 3 && !(goLeft)) continue;
					int nx = r+dx[d];
					int ny = c+dy[d];
					if(nx < 0 || nx >=N || ny< 0 || ny>=M) continue;
					if(map[nx][ny] == 0) continue;
					if(isVisited[nx][ny]) continue;
					
					if((d == 0 && (map[nx][ny] == 1 ||map[nx][ny] == 2 ||map[nx][ny] == 5 ||map[nx][ny] == 6))||
					   (d == 1 && (map[nx][ny] == 1 ||map[nx][ny] == 3 ||map[nx][ny] == 6 ||map[nx][ny] == 7))||	
					   (d == 2 && (map[nx][ny] == 1 ||map[nx][ny] == 2 ||map[nx][ny] == 4 ||map[nx][ny] == 7))||
					   (d == 3 && (map[nx][ny] == 1 ||map[nx][ny] == 3 ||map[nx][ny] == 4 ||map[nx][ny] == 5))) {
						
						isVisited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
						++ans;
					}
				}
			} ++hours;
		}return ans;
	}
}
