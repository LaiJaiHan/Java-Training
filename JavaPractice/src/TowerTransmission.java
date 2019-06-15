
public class TowerTransmission {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [] heights = {6,9,5,7,4};
		int [] returns = {};
		
		for (int i = heights.length; i>=1; i--) {
			
			int tower_num = i;
			
			for(int k = i; k>=1; k--) {
				
				if (heights[k-1] > heights[i-1]) {
					
					returns[i-1] = k-1;
				}
				
			}
			
		}
		for (int i = 0; i<returns.length; i++) System.out.print(returns[i]);

	}

}
