package ch01;

public class HelloNumbers {

	
		  public static void windowPosSum(int[] a, int n) {
		    /** your code here */ 
			  for(int i=0;i<a.length;i++) {
				  if(a[i]<0) continue;
				  if((i+n)>=a.length) {
					  for(int j=i+1;j<a.length;j++) a[i]+=a[j];
				  }
				  else {
					  for(int j=1;j<=n;j++) {
						  a[i]+=a[i+j];
					  }
				  }
			  }
		  }

		  public static void main(String[] args) {
		    int[] a = {1, 2, -3, 4, 5, 4};
		    int n = 3;
		    windowPosSum(a, n);

		    // Should print 4, 8, -3, 13, 9, 4
		    System.out.println(java.util.Arrays.toString(a));
		  }
		
}
