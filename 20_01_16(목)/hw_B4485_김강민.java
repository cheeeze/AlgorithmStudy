import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		int[][] room;
		int T = 1;
		int ans;
		int[][] dp;
		while(true) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			room = new int[N][N];
			dp = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					room[i][j]=Integer.parseInt(st.nextToken());
					dp[i][j]=1000000000;
				}
			}
			dp[0][0]=room[0][0];
			PriorityQueue<Pos> pq = new PriorityQueue<>();
			pq.add(new Pos(0, 0, room[0][0]));
			while(!pq.isEmpty()) {
				Pos p=pq.poll();
				
				if(dp[p.y][p.x]<p.cost)
					continue;
				for(int i=0;i<4;i++) {
					int iy = p.y+dy[i];
					int ix = p.x+dx[i];
					if(iy<0||ix<0||iy>=N||ix>=N)	continue;
					if(dp[iy][ix] > dp[p.y][p.x]+room[iy][ix]) {
						dp[iy][ix]=dp[p.y][p.x]+room[iy][ix];
						pq.add(new Pos(iy, ix, dp[iy][ix]));
					}
				}
//				System.out.println(p);
//				for(int i=0;i<N;i++) {
//					for(int j=0;j<N;j++) {
//						System.out.print(dp[i][j]+" ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
			System.out.println("Problem "+T+": "+dp[N-1][N-1]);
			T++;
		}
		
		
		
	}
	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1,0,-1};
	
	static class Pos implements Comparable<Pos>{
		int y;
		int x;
		int cost;
		public Pos(int y, int x, int cost) {
			super();
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + ", cost=" + cost + "]";
		}
		@Override
		public int compareTo(Pos o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
		
		
		
	}
	

}
