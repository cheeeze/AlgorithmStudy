import java.util.Scanner;

/**
 * 
 * @author Kangmin
 * @category B16987 dfs
 *
 */
public class Main16987 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N=sc.nextInt();
		arrEgg=new Egg[N];
		for(int i=0;i<N;i++) {
			arrEgg[i]=new Egg(sc.nextInt(), sc.nextInt());
		}
		ans = 0;
		for(int i=1;i<N;i++) {
			hitEgg(0, i, arrEgg);
		}
		System.out.println(ans);
		
	}
	
	
	static int ans;
	static void hitEgg(int start, int target, Egg[] arr) {
		if(start>=arr.length) {
			int cnt = 0;
			for(int i=0;i<arr.length;i++) {
				if(arr[i].s<=0)
					cnt++;
			}
			ans=Math.max(ans, cnt);
			return;
		}
		Egg[] newArr = new Egg[arr.length];
		Egg e1 = arr[start];
		Egg e2 = arr[target];
		if(e1.s>0&&e2.s>0) {			
			for(int i=0;i<arr.length;i++) {
				newArr[i] = new Egg(arr[i].s, arr[i].w);
			}
			e1=newArr[start];
			e2=newArr[target];
			e1.s-=e2.w;
			e2.s-=e1.w;
		}else {
			newArr=arr;
		}
		
		for(int i=0;i<arr.length;i++) {
			if(i==start+1)
				continue;
			hitEgg(start+1, i, newArr);
		}
		
		
	}
	
	
	static Egg[] arrEgg;
	static class Egg{
		int s;
		int w;
		public Egg(int s, int w) {
			super();
			this.s = s;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Egg [s=" + s + ", w=" + w + "]";
		}
		
		
	}

}
