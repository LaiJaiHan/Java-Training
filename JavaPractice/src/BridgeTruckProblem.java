import java.util.*;


public class BridgeTruckProblem {
	
	class Truck {
		
		int truck_time;
		int truck_weight;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Using Queue
		// While ( Queue.weight <= n ) { Enqueue; 
		// if ( Queue.weight > n ) count ++; Dequeue; }
		
		
		int bridge_length = 2; // the length of Queue;
		int bridge_weight = 10; 
		int [] truck_weights = {7,4,5,6};
		

		Queue<Truck> bridge = new LinkedList<>();
		
		Truck [] trucks = new Truck[truck_weights.length]; 
		
		for (int i = 0 ; i<trucks.length; i++) {
			
			trucks[i].truck_weight = truck_weights[i];
			trucks[i].truck_time = bridge_length;
			
		}
	
		int total = 0;
		
		while ( true ) {
			
			if ( total <= bridge_weight )

		}
		
		System.out.println(count);
		System.out.println(total);
		System.out.println(bridge.size());

	}

}
