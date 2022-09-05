import java.io.*;
import java.util.*;

public class Solution_5658_보물상자비밀번호 {
	
	static int N, K;
	static char[] chInput;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			chInput = br.readLine().toCharArray();
			Set<Integer> setNum = new HashSet<>();
			
			for(int i = 0; i < N /4; i++) {
				int arrIdx = 0;
				char[] chArr1 = new char[N/4];
				for(int j = 0; j < N/4; j++) {
					chArr1[j] = chInput[arrIdx++];
				}
				setNum.add(changeTen(chArr1));
				
				char[] chArr2 = new char[N/4];
				for(int j = 0; j < N/4; j++) {
					chArr2[j] = chInput[arrIdx++];
				}
				setNum.add(changeTen(chArr2));
				
				char[] chArr3 = new char[N/4];
				for(int j = 0; j < N/4; j++) {
					chArr3[j] = chInput[arrIdx++];
				}
				setNum.add(changeTen(chArr3));
				
				char[] chArr4 = new char[N/4];
				for(int j = 0; j < N/4; j++) {
					chArr4[j] = chInput[arrIdx++];
				}
				setNum.add(changeTen(chArr4));
				
				rotation();
			}
			
			List<Integer> list = new ArrayList<>();
			Iterator<Integer> iter = setNum.iterator();
			while(iter.hasNext()) {
				list.add(iter.next());
			}
			
			Collections.sort(list, Collections.reverseOrder());
			
			sb.append("#").append(tc).append(" ").append(list.get(K - 1)).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void rotation() {
		char tmp = chInput[N - 1];
		for(int i = N - 1; i >= 1; i--) {
			chInput[i] = chInput[i - 1];
		}
		chInput[0] = tmp;
	}
	
	static int changeTen(char[] inArr) {
		//들어온 캐릭터 배열을 십진수 화 해서 리턴
		int ret = 0;
		int cnt = 0;
		for(int i = inArr.length - 1; i >= 0; i--) {
			char cur = inArr[i];
			int result = 0;
			
			if(cur >= '0' && cur <= '9') {
				result = cur - '0';
			} else {
				switch(cur) {
				case 'A' : result = 10; break;
				case 'B' : result = 11; break;
				case 'C' : result = 12; break;
				case 'D' : result = 13; break;
				case 'E' : result = 14; break;
				case 'F' : result = 15; break;
				}
			}
			
			ret += Math.pow(16, cnt++) * result;
		}
		
		return ret;
	}
}
