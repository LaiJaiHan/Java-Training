
public class TowerTransmission {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [] heights = {6,9,5,7,4};
		int [] returns = new int[heights.length];
		
		
		for (int i = 0; i<returns.length; i++) returns[i] = 0;
		
		for (int i = heights.length-1; i>=0; i--) {
			
			int n = i-1;
			
			for(int k = n; k>0; k--) {
				
				if (heights[k] > heights[i]) {
					
					returns[i] = k+1;
					
					break;
				}
				
			}
			
		}
		
		for (int i = 0; i<returns.length; i++) System.out.print(returns[i]);

	}

}
