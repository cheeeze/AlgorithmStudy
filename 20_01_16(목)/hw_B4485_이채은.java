import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		for(int t=1; ; t++) {
			n=sc.nextInt();
			if(n==0) return;
			map=new int[n][n];
			for(int i=0; i<n; i++)
				for(int j=0; j<n; j++)
					map[i][j]=sc.nextInt();

			Queue<int[]> q=new LinkedList<>();
			int[][] visited=new int[n][n];
			for(int i=0; i<n; i++)
				Arrays.fill(visited[i], Integer.MAX_VALUE);
			
			q.add(new int[] {0,0,map[0][0]});
			visited[0][0]=map[0][0];

			int[] dx= {1,0,-1,0};
			int[] dy= {0,1,0,-1};
			int tx=0, ty=0;
			int ans=Integer.MAX_VALUE;
			while(!q.isEmpty()) {
				int[] tmp=q.poll();

				for(int i=0; i<4; i++) {
					tx=tmp[0]+dx[i];
					ty=tmp[1]+dy[i];
					if(tx<0||tx>=n||ty<0||ty>=n||tmp[2]+map[ty][tx]>=visited[ty][tx]) continue;

					if(tx==n-1&&ty==n-1) {
						ans=Math.min(ans, tmp[2]+map[ty][tx]);
					}
					else {
						q.add(new int[] {tx,ty,tmp[2]+map[ty][tx]});
						visited[ty][tx]=tmp[2]+map[ty][tx];
					}
				}
			}
			System.out.println("Problem "+t+": "+ans);
		}
	}
}
