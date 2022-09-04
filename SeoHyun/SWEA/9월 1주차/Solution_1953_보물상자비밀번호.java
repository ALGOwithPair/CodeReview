package swea;
import java.io.*;
import java.util.*;
public class 모의SW_보물상자비밀번호 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			char [] arr = new char[N];
			Set<Integer> num = new TreeSet<>((o1, o2) -> Integer.compare(o2, o1));
			
			char [] c = br.readLine().toCharArray();
			for(int i = 0; i<N; i++) {
				arr[i] = c[i];
			}
			int turn = N/4;
			while(true) {
				int isSame = 0;
				int i = 0;
				while(i<N) {
					int ex = turn-1;
					int tmp = 0;
					for(int d = i; d<i+turn; d++) {
						if(0<=arr[d]-'A' && arr[d]-'A'<= 5 ) {
							tmp += Math.pow(16, ex--)*(arr[d]-'A'+10);
						}
						else
							tmp += Math.pow(16, ex--)*(arr[d]-'0');
					}
					if(!num.contains(tmp)) {
						num.add(tmp);
					}
					else
						isSame +=1;
					
					i+=turn;
				}
				if(isSame == 4) break;
				
				char last = arr[N-1];
				for(int idx = N-1; idx>=1; idx--) {
					arr[idx] = arr[idx-1];
				}arr[0] = last;

			}
//
//			for(int i: num) {
//				System.out.println(i);
//			}
			int cnt = 0;
			for(int x: num) {
				cnt++;
				if(cnt == K) {
					sb.append("#"+tc+" ").append(x).append("\n");
					break;
				}	
			}
		}
		System.out.print(sb.toString());
	}

}
