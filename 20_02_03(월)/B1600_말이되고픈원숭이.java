import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class B1600_¸»ÀÌµÇ°íÇÂ¿ø¼þÀÌ {
	static int K,N,M;
	static int[][] map;
	static boolean[][][] check;
	static class Node implements Comparable<Node>{
		int x,y,k;
		int cnt;

		public Node(int x, int y, int k, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", k=" + k + ", cnt=" + cnt + "]";
		}

		@Override
		public int compareTo(Node o) {
			if(this.k==o.k) {
				return this.cnt-o.cnt;
			}
			return this.k-o.k;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K=sc.nextInt(); M=sc.nextInt(); N=sc.nextInt();
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j]=sc.nextInt();
			}
		}
		
		check=new boolean[N][M][K+1];
		/*for(int i=0; i<N; i++) 
			Arrays.fill(check[i], Integer.MAX_VALUE);*/
		
		bfs();

	}
	static int[] dxx= {1,2,2,1,-1,-2,-2,-1};
	static int[] dyy= {-2,-1,1,2,2,1,-1,-2};
	static int[] dx= {1,0,-1,0};
	static int[] dy= {0,1,0,-1};
	private static void bfs() {
		Queue<Node> q=new LinkedList<>();
		PriorityQueue<Node> pq=new PriorityQueue<>();
		check[0][0][0]=true;
		//check[0][0][]=true;
		//q.add(new Node(0,0,0,0));
		pq.add(new Node(0,0,0,0));
		int tx=0, ty=0;
		int ans=Integer.MAX_VALUE;
		loop:while(!pq.isEmpty()) {
			Node tmp=pq.poll();
			
			System.out.println(tmp.toString());
			
			//mal
			for(int i=0; i<8; i++) {
				if(tmp.cnt==K) break;
				
				tx=tmp.x+dxx[i];
				ty=tmp.y+dyy[i];
				if(tx<0||ty<0||tx>=M||ty>=N||check[ty][tx][tmp.cnt+1]) continue;
				if(map[ty][tx]==1) continue;
				
				if(tx==M-1&&ty==N-1) {
					//ans=Math.min(ans, tmp.k+1);
					ans=tmp.k+1;
					break loop;
				}
				
				pq.add(new Node(tx, ty, tmp.k+1, tmp.cnt+1));
				check[ty][tx][tmp.cnt+1]=true;
				
			}
			
			//monkey
			for(int i=0; i<4; i++) {
				tx=tmp.x+dx[i];
				ty=tmp.y+dy[i];
				System.out.println(tx+" "+ty);
				if(tx<0||ty<0||tx>=M||ty>=N||check[ty][tx][tmp.cnt]) continue;
				if(map[ty][tx]==1) continue;
				
				if(tx==M-1&&ty==N-1) {
					//ans=Math.min(ans, tmp.k+1);
					ans=tmp.k+1;
					break loop;
				}
				
				pq.add(new Node(tx, ty, tmp.k+1, tmp.cnt));
				check[ty][tx][tmp.cnt]=true;
			}
			
		}
		if(ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}

}
