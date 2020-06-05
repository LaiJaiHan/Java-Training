import java.util.*;
public class HIndex {

	public static void main(String[] args) {
	

		
		    	int[] citations = { 1 , 2 , 2 , 2 , 2 , 2 , 10 , 11 , 13 , 14 , 15 };
		    	
		        Arrays.sort(citations);
		        
		        int index = citations.length/2;
		        
		        System.out.println(citations[index]+""+index);
		        
		        int h;
		        int temp;
		        
		        while ( true ) {
		        	
		        	temp = citations.length - index;

		            if ( citations [index] <= temp && citations [index] >= index ) {

		            h = citations [index] ;
		            break;

		            }

		            else if ( citations [index] < temp ) index = index - 1;

		            
		        }
		       
		        System.out.println(citations[index]+""+index);
		        
		    }

		

	}


