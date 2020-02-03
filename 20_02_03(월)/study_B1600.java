import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static int[] dy= {-2,-1,1,2,2,1,-1,-2,0,1,0,-1};
	static int[] dx= {1,2,2,1,-1,-2,-2,-1,1,0,-1,0};
	static int W,H;
	static int K;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K=sc.nextInt();
		
		W=sc.nextInt();
		H=sc.nextInt();
		map = new int[H][W];
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				map[i][j]=sc.nextInt();
			}
		}
		bfs();
		if(ans==Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
		
	}
	static boolean[][][] visited;
	static int ans;
	
	static void bfs() {
		LinkedList<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0, 0, 0, 0));
		visited=new boolean[H][W][K+1];
		visited[0][0][0]=true;
		ans = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Monkey m = q.poll();
			int startidx = 0;
			if(m.y==H-1&&m.x==W-1) {
				ans=m.cnt;
				break;
			}
				
			if(m.remainK>=K)
				startidx=8;
			for(int i=startidx;i<12;i++) {
				int iy = m.y+dy[i];
				int ix = m.x+dx[i];
				if(iy<0||ix<0||iy>=H||ix>=W)
					continue;
				if(i<8&&visited[iy][ix][m.remainK+1])
					continue;
				if(i>=8&&visited[iy][ix][m.remainK])
					continue;
					
				if(map[iy][ix]==1)
					continue;
				if(i<8) {
					visited[iy][ix][m.remainK+1]=true;
					q.add(new Monkey(iy, ix, m.cnt+1, m.remainK+1));
				}else {
					visited[iy][ix][m.remainK]=true;
					q.add(new Monkey(iy,ix,m.cnt+1,m.remainK));
				}
			}
		}
	}
	
	static class Monkey{
		int y;
		int x;
		int cnt;
		int remainK;
		public Monkey() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Monkey(int y, int x, int cnt, int remainK) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.remainK = remainK;
		}
		@Override
		public String toString() {
			return "Monkey [y=" + y + ", x=" + x + ", cnt=" + cnt + ", remainK=" + remainK + "]";
		}
		
	}
}
