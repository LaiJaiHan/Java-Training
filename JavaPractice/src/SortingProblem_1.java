class Mergesorter {
		
	void merge(int h, int m, int U[], int V[], int array[]) {
			
			int i=0; int j=0; int k=0;
			
			while( i<h && j<m) {
				
				if(U[i] < V[j]) {
					
					array[k] = U[i];
					i++;
				}
				
				else {
					
				    array[k] = V[j];
					j++;
				
				}
				
				k++;
			}
			
			if(i>=h) {
				
				for(int tem =j; tem<m; tem++) {
					
					array[k] = V[tem];
					k++;
					
				}
				
			}
			else {
				
				for(int tem =i; tem<h; tem++) {
					
					array[k] = U[tem];
					k++;
				
				}
			}
			
		}
		
	void mergesort(int n, int array[]) {
		
		if(n>1) {
			
			int h,m; int U[]; int V[];
			
			if(n%2 == 0 ) {h = n/2; m = n-h;}
			else {h = (n+1)/2; m = n-h;} 
			
			U = new int[h];
			V = new int[m];
			
			for(int i=0; i<h; i++) U[i] = array[i];
		
			int temp = 0;
			
			for(int k = h; k < n; k++) { 
				
				V[temp] = array[k];
				temp ++ ; 
			
			}
			
			mergesort(h, U);
			mergesort(m, V);
			merge(h,m,U,V,array);			
			
		}
		
	}

}

class Separator extends Mergesorter {
	
	private int[] array;
	private int[][] commands;
	
	public Separator() {
		// TODO Auto-generated constructor stub
	}
	
	public Separator (int array[], int[][] commands){
		
		this.array = array;
		this.commands = commands;
	}
	

	int[] separate (int index) {
		
		// index refers to sequence of array
		
		int start = this.commands[index-1][0];
		int end = this.commands[index-1][1];
		
		int array[] = new int [(end-start)+1]; 
		int temp = 0;
		
		for (int i = start-1; i < end; i++) {
			
			array[temp] = this.array[i];
			temp ++;
			
		}
		
		return array;
		
	}
	
	int get_point_value (int index, int[] array) {
		
		int point = this.commands[index-1][2];
		
		mergesort(array.length, array);
		
		int value = array[point-1];
		
		return value;
	}
	
}

public class SortingProblem_1 {
	
	public static void main(String[] args) {
		
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		int[] answer = new int[commands.length];
		
		Separator separator = new Separator(array, commands);
		
		for (int i = 1; i<=commands.length; i++) {
			
			int[] temp_array = separator.separate(i);
			answer[i-1] = separator.get_point_value(i,temp_array);
		}
		
		for (int i =0; i<answer.length; i++) System.out.println(answer[i]);
		

	}

}
