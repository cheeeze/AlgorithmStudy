import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class B16987_계란으로계란치기 {
	static int N;
	static ArrayList<Node> list;
	static class Node {
		int inner, weight;

		public Node(int inner, int weight) {
			super();
			this.inner = inner;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [inner=" + inner + ", weight=" + weight + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		list=new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			list.add(new Node(sc.nextInt(), sc.nextInt()));
		}
		
		
		boolean[] visited=new boolean[N];
		boolean[] isbreak=new boolean[N];
		dfs(0, visited, isbreak);
		System.out.println(ans);
	}
	static int ans;
	static int ccc;
	private static void dfs(int idx, boolean[] visited, boolean[] isbreak) {
		//ccc++;
		//if(ccc>10) return;
		//System.out.println("idx:"+idx+" visited:"+Arrays.toString(visited)+" isbreak:"+Arrays.toString(isbreak));
		if(idx==N) {
			//System.out.println("끝까지왔음");
			int cnt=0;
			for(int i=0; i<N; i++) {
				if(isbreak[i]) cnt++; 
			}
			ans=Math.max(ans, cnt);
			return;
		}
		
		if(isbreak[idx]) {
			dfs(idx+1, visited, isbreak); return;
		}
		
		boolean flag = false;
		for(int i=0; i<N; i++) {
			//System.out.println("i:"+i);
			if(i==idx) continue;
			
			if(!isbreak[i]) {
				flag=true;
				/*System.out.println(i+"번 친다");
				System.out.println("치기전");
				System.out.println(list.get(idx).inner+"  "+list.get(idx).weight);
				System.out.println(list.get(i).inner+"  "+list.get(i).weight);*/
				list.get(idx).inner-=list.get(i).weight;
				list.get(i).inner-=list.get(idx).weight;
				/*System.out.println("치고난후");
				System.out.println(list.get(idx).inner+"  "+list.get(idx).weight);
				System.out.println(list.get(i).inner+"  "+list.get(i).weight);*/
				if(list.get(i).inner <= 0) isbreak[i]=true;
				if(list.get(idx).inner <= 0) isbreak[idx]=true;
				

				dfs(idx+1,visited,isbreak);
				
				list.get(idx).inner+=list.get(i).weight;
				list.get(i).inner+=list.get(idx).weight;
				if(list.get(i).inner > 0) isbreak[i]=false;
				if(list.get(idx).inner > 0) isbreak[idx]=false;
			}
		}
		if(!flag) {
			int cnt=0;
			for(int i=0; i<N; i++) {
				if(isbreak[i]) cnt++; 
			}
			ans=Math.max(ans, cnt);
			return;
		}
		
	}

}
