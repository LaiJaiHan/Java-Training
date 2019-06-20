import java.util.*;


public class BridgeTruckProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Using Queue
		// While ( Queue.weight <= n ) { Enqueue; 
		// if ( Queue.weight > n ) count ++; Dequeue; }
		
		
		int bridge_length = 2; // the length of Queue;
		int bridge_weight = 10; 
		int [] truck_weights = {7,4,5,6};  
		
		
		int total = 0 ;
		
		Stack<Integer> bridge = new Stack<>();
	
		int count = 0;
	
		for ( int i = 0 ; i < truck_weights.length; i++ ) {
			
			total = total + truck_weights[i];	
	
			if ( total <= bridge_weight ) {
				
				if ( bridge.size() <bridge_length ) {
					
					bridge.push( truck_weights[i] );
					count ++; 
					
				}
				else {
					
					bridge.clear(); 
					total = 0;
					
				}
			}
			
			else {
				
				bridge.clear();
				total = 0; 
			}
		}
		
		System.out.println(count);
		

	}

}
