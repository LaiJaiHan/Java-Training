import java.util.Arrays;
import java.util.HashMap;

public class HashTableProblem_1 {
	
	
	static int prefix_hash(String phone_number, int i) {
		
		return (int) (Integer.parseInt(phone_number) / Math.pow(10, phone_number.length() - (i)));	
		
	}
	
	
	public static void main(String[] args) {
		
		String[] phone_book = {"119", "97674223", "1195524421"};
		int[] phone_book_digits = new int[phone_book.length];
 		
		for (int i = 0; i<phone_book.length; i++)
			System.out.println(prefix_hash(phone_book[i],i+1));
		
		for (int i = 0; i < phone_book.length; i++) {
			
			phone_book_digits[i] = phone_book[i].length();
			
		}
		
		Arrays.sort(phone_book_digits);
		
		int prefix_digits = phone_book_digits[0];
		int index = 0;
		boolean answer = true;
		
		for (int j = 0; j<phone_book_digits.length; j++) {
			
			prefix_digits = phone_book_digits[j];
			
			int count = 0;
			
			for (int i = 0; i<phone_book.length; i++) {
			 
				if ( prefix_hash(phone_book[j],prefix_digits) == prefix_hash(phone_book[i],prefix_digits)) 
					count ++;
				
				if (count > 1) {
					
					answer = false; 
					break;
		
				}
				
			}

			if (answer == false) break;
			
		}
		
		
		System.out.println(answer);
		
	}

}
