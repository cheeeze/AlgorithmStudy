import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_Baek {
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;
		public Node(int x, int y, int cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {

			return this.cost - o.cost;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static int[][] map, dist;
	static PriorityQueue<Node> que; 
	public static void main(String[] args) throws Exception {
		int tc = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) return;
			map = new int[N][N];
			dist = new int[N][N];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			int ans = solve(0,0);
			System.out.println("Problem " + tc + ": " +ans);
			tc++;
		}

	}
	private static int solve(int startX, int startY) {
		que = new PriorityQueue<>();
		que.add(new Node(startX, startY, map[startX][startY]));
		dist[startX][startY] = map[startX][startY];
		while(!que.isEmpty()) {
			Node temp = que.poll();
			int x = temp.x;
			int y = temp.y;
			int cost = temp.cost;

			for(int i = 0; i <4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
				if(map[nx][ny] + dist[x][y] < dist[nx][ny]) {
					dist[nx][ny] = map[nx][ny] + dist[x][y];
					que.add(new Node(nx,ny, dist[nx][ny]));
				}

			}
		}
		return dist[N-1][N-1];

	}

}
