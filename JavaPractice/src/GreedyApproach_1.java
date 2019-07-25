
public class GreedyApproach_1 {
	
	static int alphabet_hash(char alphabet) {
		
		if (alphabet == 'A') return 0;
		else if (alphabet == 'B' || alphabet == 'Z') return 1; 
		else if (alphabet == 'C' || alphabet == 'Y') return 2; 
		else if (alphabet == 'D' || alphabet == 'X') return 3; 
		else if (alphabet == 'E' || alphabet == 'W') return 4; 
		else if (alphabet == 'F' || alphabet == 'V') return 5;
		else if (alphabet == 'G' || alphabet == 'U') return 6;
		else if (alphabet == 'H' || alphabet == 'T') return 7; 
		else if (alphabet == 'I' || alphabet == 'S') return 8; 
		else if (alphabet == 'J' || alphabet == 'R') return 9; 
		else if (alphabet == 'K' || alphabet == 'Q') return 10; 
		else if (alphabet == 'L' || alphabet == 'P') return 11; 
		else if (alphabet == 'M' || alphabet == 'O') return 12; 
		else return 13; 
		
		
	}
	
	static void initalize_edge(int edge[][], int name_length) {
		
		for(int i = 0; i<edge.length; i++) {
			
			for(int j = 0; j<edge[i].length; j++) {
				
				int max_length = 0;
				
				if(name_length % 2 == 0 ) {
					
					max_length = name_length/2; 
					
					if(j>=i) {
						
						int max_point = i + max_length;
						
						if((j-i)> max_length) 
							edge[i][j] = max_length - (j - max_point) ;
						else
							edge[i][j] = j-i;
		
					}
					
					else {
						
						int max_point = i - max_length;
						
						if((i-j)> max_length) 
							edge[i][j] = max_length - (max_point - j) ;
						else
							edge[i][j] = i-j;
					}
					
				}
					
					
				else {
					
					max_length = name_length/2;
					
					if(j>=i) {
						
						int max_point = (i + max_length);
						
						if((j-i)> max_length) 
							edge[i][j] = max_length - (j - max_point) +1;
						else
							edge[i][j] = j-i;
		
					}
					
					else {
						
						int max_point = (i - max_length);
						
						if((i-j)> max_length) 
							edge[i][j] = max_length - (max_point - j) +1;
						else
							edge[i][j] = i-j;
					}
					
				}
					
				
			}
			
		}
		
		
		
	}
	
	public static void main(String[] args) {
		
		String name = "LEEJAEHAN";
		char[] name_char = name.toCharArray();
		int n = name_char.length;
		
		int[] node_value = new int [n];
		int[][] edge = new int [n][n];
		int[] set_of_edge_value = new int[n-1];
		
		
		// initialize node_value using hash
		for(int i = 0; i<n; i++) 
			node_value[i] = alphabet_hash(name_char[i]);
			
		
		initalize_edge(edge,n);
		
		
		
		// Prim's Algorithm
		int[] distance = new int[n];
		int[][] set = new int[n][2];
		
		int vnear = 0; 
		
		for(int i = 1; i<n; i++) {
			
			distance[i] = edge[0][i];
			
		}
		
		int prev_vnear = 0;

		
		for(int i = 0; i<n-1; i++) {
			
			int max = Integer.MAX_VALUE;
		
			for(int j = 0; j<n; j++) {
				
				if(node_value[j] ==0) distance[j] = max;
				
				if (0<distance[j] && distance[j]<max) {
					
					max = distance[j];
					vnear = j;
					
				}
			
			}
			
			set[i][0] = prev_vnear+1;
			set[i][1] = vnear+1; // record the shortest vertex;
			
			distance[vnear] = 0;
			
			for(int j = 0; j<n; j++) {
				
				if(edge[j][vnear] < distance[j])	
					
					distance[j] = edge[j][vnear];
				
			}
			
			prev_vnear = vnear;
		}
		
		for(int i = 0; i<n; i++)
			System.out.println(set[i][0] + "," + set[i][1]);
	
		
	}

}
