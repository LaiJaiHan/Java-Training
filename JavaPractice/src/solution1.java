
public class solution1 {

	public static void main(String[] args) {
		 
		 int n = 5;
		
		 int count = 0;
	     int temp_count = 0;
	      
	      for (int i = 2; i<=n; i++){
	          
	         
	          
	          for (int a = 1; a<=i; a++ ){
	              
	              if ( i % a == 0) temp_count ++;
	              
	          }
	          
	          if ( temp_count == 2)  count++;
	          
	          temp_count = 0;
	      }
	      
	      int answer = count;
	      
	      System.out.print( count ); 
	}

}
